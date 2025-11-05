package com.floodguard.server.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class PrevisaoService {

    private static final String API_URL = "https://api-uluk.onrender.com/predict?lat=%s&lon=%s";

    public Object buscarPrevisao(double lat, double lon) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(API_URL, lat, lon);

        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
        return response.getBody();
    }
}