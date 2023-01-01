package com.sos.doctors.common.repository;

import com.sos.doctors.common.entity.DoctorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorsRepository extends JpaRepository<DoctorsEntity, Long> {

}
