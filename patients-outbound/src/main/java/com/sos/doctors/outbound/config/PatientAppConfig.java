package com.sos.doctors.outbound.config;

import com.sos.common.config.MySQLDBConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.sos.patients.common.repository")
@EntityScan("com.sos.patients.common.entity")
public class PatientAppConfig extends MySQLDBConfig{
}
