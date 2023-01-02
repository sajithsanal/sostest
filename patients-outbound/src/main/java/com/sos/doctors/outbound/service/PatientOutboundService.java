package com.sos.doctors.outbound.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sos.common.config.DbContextHolder;
import com.sos.common.config.DbType;
import com.sos.common.exception.SOSException;
import com.sos.patients.common.dto.PatientDetailsDTO;
import com.sos.patients.common.entity.PatientsEntity;
import com.sos.patients.common.repository.PatientsRepository;
import com.sos.patients.common.util.PatientUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientOutboundService {

    Logger logger = LoggerFactory.getLogger(PatientOutboundService.class);

    @Autowired
    private PatientsRepository patientsRepository;


    public List<PatientDetailsDTO> findAllPatients() {

        DbContextHolder.setDbType(DbType.REPLICA);

        List<PatientsEntity> patientsEntityList = patientsRepository.findAll();
        logger.info("Total " + patientsEntityList.size() + " patients present in the system");
        List<PatientDetailsDTO> response = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        patientsEntityList.forEach(doc -> {
            response.add(modelMapper.map(doc, PatientDetailsDTO.class));
        });
        DbContextHolder.clearDbType();

        return response;

    }


    public PatientDetailsDTO findPatient(Long id) {

        try {
            DbContextHolder.setDbType(DbType.REPLICA);

            logger.info("Received request for doctor with id " + id);

            Optional<PatientsEntity> optionalDoctorsEntity = patientsRepository.findById(id);
            if (optionalDoctorsEntity.isPresent()) {
                logger.info("Found patient with id " + id);
                PatientsEntity entity = optionalDoctorsEntity.get();
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map(entity, PatientDetailsDTO.class);

            }
            logger.info("Unable to find patient with id " + id);
            throw new SOSException("Unable to find a patient with id " + id);
        } finally {
            DbContextHolder.clearDbType();
        }


    }

    public PatientDetailsDTO findPatient(String name) throws JsonProcessingException {

        try {
            DbContextHolder.setDbType(DbType.REPLICA);

            logger.info("Received request for doctor with name " + name);

            Optional<PatientsEntity> optionalDoctorsEntity = patientsRepository.findByFullNameIgnoreCase(name);
            if (optionalDoctorsEntity.isPresent()) {
                logger.info("Found doctor with name " + name);
                PatientsEntity entity = optionalDoctorsEntity.get();
                ModelMapper modelMapper = new ModelMapper();
                PatientDetailsDTO patientDetailsDTO = modelMapper.map(entity, PatientDetailsDTO.class);
                patientDetailsDTO.setDoctorName(PatientUtil.getDoctorName(entity.getDoctorId()));

            }
            logger.info("Unable to find doctor with name " + name);
            throw new SOSException("Unable to find a doctor with name " + name);
        } finally {
            DbContextHolder.clearDbType();
        }


    }



}
