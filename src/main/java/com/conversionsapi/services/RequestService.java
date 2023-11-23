package com.conversionsapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RestTemplate restTemplate;

    public Float convert(String realCurr, String convertedCurr) throws Exception {
        String url = "https://economia.awesomeapi.com.br/last/";
        ResponseEntity<Map> response = restTemplate.getForEntity(url + realCurr + "-" + convertedCurr, Map.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception(String.valueOf(Objects.requireNonNull(response.getBody()).get("message")));
        }

        Map conversion = (Map) Objects.requireNonNull(response.getBody()).get(realCurr + convertedCurr);
        return Float.parseFloat((String) conversion.get("bid"));
    }

}
