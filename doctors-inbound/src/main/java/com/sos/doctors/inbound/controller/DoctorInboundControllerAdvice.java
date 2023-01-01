package com.sos.doctors.inbound.controller;

import com.sos.common.constants.SOSConstants;
import com.sos.common.exception.SOSException;
import com.sos.doctors.common.dto.DoctorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class DoctorInboundControllerAdvice {

    Logger logger = LoggerFactory.getLogger(DoctorInboundControllerAdvice.class);


    @ExceptionHandler(SOSException.class)
    public DoctorResponse handleValidationException(SOSException ex, WebRequest request) {

        DoctorResponse response = new DoctorResponse();
        response.setStatus(String.valueOf(SOSConstants.ERROR));
        response.setMessage(ex.getMessage());
        logger.error("Validation Error --> " + ex.getMessage());
        return response;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleRuntimeException(Exception ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", String.valueOf(SOSConstants.ERROR));
        body.put("message", "There is an issue with application. Please contact support");
        logger.error("Application Error --> " + ex.getMessage(), ex);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
