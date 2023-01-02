package com.sos.patients.common.repository;

import com.sos.patients.common.entity.PatientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientsRepository extends JpaRepository<PatientsEntity, Long> {


}
