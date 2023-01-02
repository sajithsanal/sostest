package com.sos.doctors.outbound.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sos.doctors.outbound.service.PatientOutboundService;
import com.sos.patients.common.dto.PatientDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientOutboundController {


    @Autowired
    private PatientOutboundService patientOutboundService;

    @GetMapping(value = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<PatientDetailsDTO> findAllPatients() {

        return patientOutboundService.findAllPatients();

    }


    @GetMapping(value = "/patient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PatientDetailsDTO findPatient(@PathVariable("id") Long patientId) {

        return patientOutboundService.findPatient(patientId);

    }

    @GetMapping(value = "/patient", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PatientDetailsDTO getPatient(@RequestParam("name") String doctorName) throws JsonProcessingException {

        return patientOutboundService.findPatient(doctorName);

    }

}
