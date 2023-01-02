package com.sos.doctors.common.repository;

import com.sos.doctors.common.entity.DoctorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorsRepository extends JpaRepository<DoctorsEntity, Long> {

    Optional<DoctorsEntity> findByFullNameIgnoreCase(String fullName);

}
