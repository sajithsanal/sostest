package com.sos.patients.common.repository;

import com.sos.patients.common.entity.PatientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientsRepository extends JpaRepository<PatientsEntity, Long> {

    Optional<PatientsEntity> findByFullNameIgnoreCase(String name);


}
