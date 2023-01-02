package com.sos.doctors.inbound.service;

import com.sos.common.config.DbContextHolder;
import com.sos.common.config.DbType;
import com.sos.common.constants.SOSConstants;
import com.sos.common.exception.SOSException;
import com.sos.patients.common.dto.PatientDetailsDTO;
import com.sos.patients.common.dto.PatientResponse;
import com.sos.patients.common.entity.PatientsEntity;
import com.sos.patients.common.repository.PatientsRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class PatientInboundService {


    Logger logger = LoggerFactory.getLogger(PatientInboundService.class);

    @Autowired
    private PatientsRepository patientsRepository;

    @Transactional
    public PatientResponse createPatient(PatientDetailsDTO request) {

        DbContextHolder.setDbType(DbType.MASTER);
        validateCreationRequest(request);
        logger.info("Received creation request for patient " + request.getFullName());
        ModelMapper modelMapper = new ModelMapper();
        request.setId(null);
        PatientsEntity entity = modelMapper.map(request, PatientsEntity.class);
        patientsRepository.save(entity);
        DbContextHolder.clearDbType();

        return getResponse(SOSConstants.SUCCESS, "Successfully created patient " + request.getFullName() + " with id " + entity.getId());

    }

    @Transactional
    public PatientResponse updatePatient(PatientDetailsDTO request) {

        PatientResponse response;
        DbContextHolder.setDbType(DbType.MASTER);


        validateUpdateRequest(request);
        logger.info("Received update request for patient " + request.getId());


        Optional<PatientsEntity> optionalDoctorsEntity = patientsRepository.findById(request.getId());
        if (optionalDoctorsEntity.isPresent()) {
            PatientsEntity entity = optionalDoctorsEntity.get();
            if (StringUtils.hasLength(request.getFullName())) {
                entity.setFullName(request.getFullName());
            }
            if (StringUtils.hasLength(request.getContactNumber())) {
                entity.setContactNumber(request.getContactNumber());
            }

            if (StringUtils.hasLength(request.getAddress())) {
                entity.setAddress(request.getAddress());
            }

            if (request.getDoctorId() != null) {
                entity.setDoctorId(request.getDoctorId());
            }

            if (request.getAge() != null) {
                entity.setAge(request.getAge());
            }

            patientsRepository.save(entity);
            response = getResponse(SOSConstants.SUCCESS, "Updated  the patient details successfully");


        } else {
            response = getResponse(SOSConstants.ERROR, "Unable to find the patient " + request.getFullName() + " with id " + request.getId());

        }
        DbContextHolder.clearDbType();


        return response;

    }

    @Transactional
    public PatientResponse deletePatient(Long id) {

        PatientResponse response;
        DbContextHolder.setDbType(DbType.MASTER);

        logger.info("Received delete request for doctor " + id);


        Optional<PatientsEntity> optionalDoctorsEntity = patientsRepository.findById(id);
        if (optionalDoctorsEntity.isPresent()) {
            PatientsEntity entity = optionalDoctorsEntity.get();
            patientsRepository.delete(entity);
            response = getResponse(SOSConstants.SUCCESS, "Deleted the doctor details successfully");
        } else {
            response = getResponse(SOSConstants.ERROR, "Unable to find the doctor  with id " + id);

        }
        DbContextHolder.clearDbType();


        return response;

    }

    private void validateCreationRequest(PatientDetailsDTO request) throws SOSException {

        if (request == null) {
            throw new SOSException("Request cannot be empty");
        }

        if (!StringUtils.hasLength(request.getFullName())) {
            throw new SOSException("Full Name cannot be empty");
        }

        if (!StringUtils.hasLength(request.getAddress())) {
            throw new SOSException("Address  cannot be empty");
        }

        if (!StringUtils.hasLength(request.getContactNumber())) {
            throw new SOSException("Contact Number  cannot be empty");
        }

        if (request.getAge() == null) {
            throw new SOSException("Age  cannot be empty");
        }

        if (request.getAge() != null && (request.getAge() < 0 || request.getAge() > 120)) {
            throw new SOSException("Age is not valid");
        }

        if (request.getDoctorId() == null) {
            throw new SOSException("Doctor Id  cannot be empty");
        }

        validateDoctorId(request);



    }

    private void validateDoctorId(PatientDetailsDTO request){
        if (request.getDoctorId() != null) {
            RestTemplate restTemplate = new RestTemplate();
            String doctorOutboundUrl = System.getenv().get("DOCTOR_OUTBOUND_URL");
            ResponseEntity<String> response = restTemplate.getForEntity(doctorOutboundUrl + "/doctor/" + request.getDoctorId(), String.class);
            String responseBody = response.getBody();
            if(responseBody.contains("ERROR")){
                throw new SOSException("Doctor Id  cannot be empty");
            }

        }

    }

    private void validateUpdateRequest(PatientDetailsDTO request) throws SOSException {

        if (request == null) {
            throw new SOSException("Request cannot be empty");
        }

        if (request.getId() == null) {
            throw new SOSException("Id cannot be empty");
        }

        if (request.getAge() != null && (request.getAge() < 0 || request.getAge() > 120)) {
            throw new SOSException("Age is not valid");
        }
        validateDoctorId(request);


    }


    private PatientResponse getResponse(SOSConstants status, String message) {

        PatientResponse response = new PatientResponse();
        response.setStatus(String.valueOf(status));
        response.setMessage(message);

        return response;
    }
}
