package com.sos.doctors.inbound.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sos.doctors.inbound.service.PatientInboundService;
import com.sos.patients.common.dto.PatientDetailsDTO;
import com.sos.patients.common.dto.PatientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientInboundController {


    @Autowired
    private PatientInboundService patientInboundService;

    @PostMapping(value = "/patient", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PatientResponse createDoctor(@RequestBody PatientDetailsDTO request) throws JsonProcessingException {

        return patientInboundService.createPatient(request);

    }


    @PutMapping(value = "/patient", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PatientResponse updateDoctor(@RequestBody PatientDetailsDTO request) throws JsonProcessingException {

        return patientInboundService.updatePatient(request);

    }

    @DeleteMapping(value = "/patient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PatientResponse deleteDoctor(@PathVariable("id") Long patientId) {

        return patientInboundService.deletePatient(patientId);

    }


}
