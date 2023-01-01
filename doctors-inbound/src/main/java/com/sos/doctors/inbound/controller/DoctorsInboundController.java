package com.sos.doctors.inbound.controller;

import com.sos.doctors.common.dto.DoctorDetailsDTO;
import com.sos.doctors.common.dto.DoctorResponse;
import com.sos.doctors.inbound.service.DoctorInboundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class DoctorsInboundController {



    @Autowired
    private DoctorInboundService doctorInboundService;

    @PostMapping(value = "/doctor", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DoctorResponse createDoctor(@RequestBody DoctorDetailsDTO request) {

        return doctorInboundService.createDoctor(request);

    }


    @PutMapping(value = "/doctor", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DoctorResponse updateDoctor(@RequestBody DoctorDetailsDTO request) {

        return doctorInboundService.updateDoctor(request);

    }

    @DeleteMapping(value = "/doctor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DoctorResponse deleteDoctor(@PathVariable("id") Long doctorId) {

        return doctorInboundService.deleteDoctor(doctorId);

    }


}

