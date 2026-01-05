package com.report.javaservice.client.impl;

import com.report.javaservice.client.ApiClient;
import com.report.javaservice.controller.dto.StudentDto;
import com.report.javaservice.exceptions.ExternalServiceException;
import com.report.javaservice.exceptions.ExternalServiceUnauthorizedException;
import com.report.javaservice.exceptions.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
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
        try {
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

        } catch (HttpClientErrorException.Unauthorized e) {
            log.warn("Unauthorized error calling API");
            throw new ExternalServiceUnauthorizedException("API client unauthorized");

        } catch (HttpClientErrorException.NotFound e) {
            log.warn("Student not found: {}", id);
            throw new StudentNotFoundException(id);

        } catch (HttpClientErrorException e) {
            log.error("Client error calling API", e);
            throw new ExternalServiceException("API client error");

        } catch (Exception e) {
            log.error("Unexpected error calling API", e);
            throw new ExternalServiceException("Unexpected error");
        }
    }
}
