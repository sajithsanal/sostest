package com.sos.patients.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class PatientUtil {

    static Logger logger = LoggerFactory.getLogger(PatientUtil.class);


    public static Long getDoctorId(String doctorName) throws JsonProcessingException {

        Long doctorId = null;
        RestTemplate restTemplate = new RestTemplate();
        String doctorOutboundUrl = System.getenv().get("DOCTOR_OUTBOUND_URL");
        String finalUrl = doctorOutboundUrl + "/doctor?name={name}";
        logger.info("final URL is " + finalUrl);
        ResponseEntity<String> response = restTemplate.getForEntity(finalUrl, String.class, doctorName);
        String responseBody = response.getBody();
        logger.info("Response  " + responseBody);
        if (StringUtils.hasLength(responseBody) && !responseBody.contains("ERROR")) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, HashMap.class);
            doctorId = Long.valueOf(String.valueOf(responseMap.get("id")));
        }

        return doctorId;


    }

    public static String getDoctorName(Long doctorId) throws JsonProcessingException {

        String doctorName = "";
        RestTemplate restTemplate = new RestTemplate();
        String doctorOutboundUrl = System.getenv().get("DOCTOR_OUTBOUND_URL");
        String finalUrl = doctorOutboundUrl + "/doctor/" + doctorId;
        logger.info("final URL is " + finalUrl);
        ResponseEntity<String> response = restTemplate.getForEntity(finalUrl, String.class);
        String responseBody = response.getBody();
        logger.info("Response  getDoctorName --> " + responseBody);
        if (StringUtils.hasLength(responseBody) && !responseBody.contains("ERROR")) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, HashMap.class);
            doctorName = (String) responseMap.get("fullName");
        }

        return doctorName;


    }


}
