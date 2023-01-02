package com.sos.doctors.outbound.controller;

import com.sos.doctors.common.dto.DoctorDetailsDTO;
import com.sos.doctors.common.dto.DoctorResponse;
import com.sos.doctors.outbound.service.DoctorOutboundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorOutboundController {



    @Autowired
    private DoctorOutboundService doctorOutboundService;

    @GetMapping(value = "/doctors", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<DoctorDetailsDTO> findAllDoctors() {

        return doctorOutboundService.findAllDoctors();

    }


    @GetMapping(value = "/doctor/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DoctorDetailsDTO findDoctor(@PathVariable("id") Long doctorId) {

        return doctorOutboundService.findDoctor(doctorId);

    }

    @GetMapping(value = "/doctor", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DoctorDetailsDTO getDoctor(@RequestParam("name") String doctorName) {

        return doctorOutboundService.findDoctor(doctorName);

    }
}
