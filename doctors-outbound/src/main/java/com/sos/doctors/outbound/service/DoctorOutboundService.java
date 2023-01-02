package com.sos.doctors.outbound.service;

import com.sos.common.config.DbContextHolder;
import com.sos.common.config.DbType;
import com.sos.common.constants.SOSConstants;
import com.sos.common.exception.SOSException;
import com.sos.doctors.common.dto.DoctorDetailsDTO;
import com.sos.doctors.common.entity.DoctorsEntity;
import com.sos.doctors.common.repository.DoctorsRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorOutboundService {

    Logger logger = LoggerFactory.getLogger(DoctorOutboundService.class);

    @Autowired
    private DoctorsRepository doctorsRepository;


    public List<DoctorDetailsDTO> findAllDoctors() {

        DbContextHolder.setDbType(DbType.REPLICA);

        List<DoctorsEntity> doctorsEntityList = doctorsRepository.findAll();
        logger.info("Total " + doctorsEntityList.size() + " doctors present in the system");
        List<DoctorDetailsDTO> response = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        doctorsEntityList.forEach(doc -> {
            response.add(modelMapper.map(doc, DoctorDetailsDTO.class));
        });
        DbContextHolder.clearDbType();

        return response;

    }


    public DoctorDetailsDTO findDoctor(Long id) {

        try {
            DbContextHolder.setDbType(DbType.REPLICA);

            logger.info("Received request for doctor with id " + id);

            Optional<DoctorsEntity> optionalDoctorsEntity = doctorsRepository.findById(id);
            if (optionalDoctorsEntity.isPresent()) {
                logger.info("Found doctor with id " + id);
                DoctorsEntity entity = optionalDoctorsEntity.get();
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map(entity, DoctorDetailsDTO.class);

            }
            logger.info("Unable to find doctor with id " + id);
            throw new SOSException("Unable to find a doctor with id " + id);
        } finally {
            DbContextHolder.clearDbType();
        }


    }

    public DoctorDetailsDTO findDoctor(String name) {

        try {
            DbContextHolder.setDbType(DbType.REPLICA);

            logger.info("Received request for doctor with name " + name);

            Optional<DoctorsEntity> optionalDoctorsEntity = doctorsRepository.findByFullNameIgnoreCase(name);
            if (optionalDoctorsEntity.isPresent()) {
                logger.info("Found doctor with name " + name);
                DoctorsEntity entity = optionalDoctorsEntity.get();
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map(entity, DoctorDetailsDTO.class);

            }
            logger.info("Unable to find doctor with name " + name);
            throw new SOSException("Unable to find a doctor with name " + name);
        } finally {
            DbContextHolder.clearDbType();
        }


    }


}
