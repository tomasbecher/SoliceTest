package com.report.javaservice.client.impl;

import com.report.javaservice.client.ApiClient;
import com.report.javaservice.controller.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Component
public class StudentApiClient implements ApiClient<StudentDto> {

    @Value("${school-backend.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    @Override
    public StudentDto getById(Long id, String cookie, String csrfToken) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Cookie", cookie);
            headers.set("X-CSRF-Token", csrfToken);

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<StudentDto> response =
                    restTemplate.exchange(
                            baseUrl + "/students/{id}",
                            HttpMethod.GET,
                            entity,
                            StudentDto.class,
                            id
                    );
            log.info("API Response: {}", response);
            return response.getBody();
    }
}
