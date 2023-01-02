package com.sos.doctors.outbound.controller;

import com.sos.common.constants.SOSConstants;
import com.sos.common.exception.SOSException;
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
public class PatientOutboundControllerAdvice {

    Logger logger = LoggerFactory.getLogger(PatientOutboundControllerAdvice.class);


    @ExceptionHandler(SOSException.class)
    public ResponseEntity<Object> handleValidationException(SOSException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", String.valueOf(SOSConstants.ERROR));
        body.put("message", ex.getMessage());
        logger.error(" Error --> " + ex.getMessage(), ex);

        return new ResponseEntity<>(body, HttpStatus.OK);

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
