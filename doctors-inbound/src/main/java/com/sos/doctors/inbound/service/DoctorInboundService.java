package com.sos.doctors.inbound.service;

import com.sos.common.config.DbContextHolder;
import com.sos.common.config.DbType;
import com.sos.common.constants.SOSConstants;
import com.sos.common.exception.SOSException;
import com.sos.doctors.common.dto.DoctorDetailsDTO;
import com.sos.doctors.common.dto.DoctorResponse;
import com.sos.doctors.common.entity.DoctorsEntity;
import com.sos.doctors.common.repository.DoctorsRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class DoctorInboundService {

    Logger logger = LoggerFactory.getLogger(DoctorInboundService.class);

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Transactional
    public DoctorResponse createDoctor(DoctorDetailsDTO request) {

        DbContextHolder.setDbType(DbType.MASTER);
        validateCreationRequest(request);
        logger.info("Received creation request for doctor " + request.getFullName());
        ModelMapper modelMapper = new ModelMapper();
        request.setId(null);
        DoctorsEntity entity = modelMapper.map(request, DoctorsEntity.class);
        doctorsRepository.save(entity);
        DbContextHolder.clearDbType();

        return getResponse(SOSConstants.SUCCESS, "Successfully created doctor " + request.getFullName() + " with id " + entity.getId());

    }

    @Transactional
    public DoctorResponse updateDoctor(DoctorDetailsDTO request) {

        DoctorResponse response;
        DbContextHolder.setDbType(DbType.MASTER);


        validateUpdateRequest(request);
        logger.info("Received update request for doctor " + request.getId());


        Optional<DoctorsEntity> optionalDoctorsEntity = doctorsRepository.findById(request.getId());
        if (optionalDoctorsEntity.isPresent()) {
            DoctorsEntity entity = optionalDoctorsEntity.get();
            if (StringUtils.hasLength(request.getFullName())) {
                entity.setFullName(request.getFullName());
            }
            if (StringUtils.hasLength(request.getContactNumber())) {
                entity.setContactNumber(request.getContactNumber());
            }

            if (StringUtils.hasLength(request.getSpecialization())) {
                entity.setSpecialization(request.getSpecialization());
            }

            doctorsRepository.save(entity);
            response = getResponse(SOSConstants.SUCCESS, "Updated  the doctor details successfully");


        } else {
            response = getResponse(SOSConstants.ERROR, "Unable to find the doctor " + request.getFullName() + " with id " + request.getId());

        }
        DbContextHolder.clearDbType();


        return response;

    }

    @Transactional
    public DoctorResponse deleteDoctor(Long id) {

        DoctorResponse response;
        DbContextHolder.setDbType(DbType.MASTER);

        logger.info("Received delete request for doctor " + id);


        Optional<DoctorsEntity> optionalDoctorsEntity = doctorsRepository.findById(id);
        if (optionalDoctorsEntity.isPresent()) {
            DoctorsEntity entity = optionalDoctorsEntity.get();
            doctorsRepository.delete(entity);
            response = getResponse(SOSConstants.SUCCESS, "Deleted the doctor details successfully");
        } else {
            response = getResponse(SOSConstants.ERROR, "Unable to find the doctor  with id " + id);

        }
        DbContextHolder.clearDbType();


        return response;

    }

    private void validateCreationRequest(DoctorDetailsDTO request) throws SOSException {

        if (request == null) {
            throw new SOSException("Request cannot be empty");
        }

        if (!StringUtils.hasLength(request.getFullName())) {
            throw new SOSException("Full Name cannot be empty");
        }

        if (!StringUtils.hasLength(request.getSpecialization())) {
            throw new SOSException("Specialization  cannot be empty");
        }

        if (!StringUtils.hasLength(request.getContactNumber())) {
            throw new SOSException("Contact Number  cannot be empty");
        }

    }

    private void validateUpdateRequest(DoctorDetailsDTO request) throws SOSException {

        if (request == null) {
            throw new SOSException("Request cannot be empty");
        }

        if (request.getId() == null) {
            throw new SOSException("Id cannot be empty");
        }


    }


    private DoctorResponse getResponse(SOSConstants status, String message) {

        DoctorResponse response = new DoctorResponse();
        response.setStatus(String.valueOf(status));
        response.setMessage(message);

        return response;
    }


}
