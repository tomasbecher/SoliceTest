package com.report.javaservice.controller;

import com.report.javaservice.client.impl.StudentApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/reports/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentApiClient studentApiClient;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> generate(@PathVariable Long id,
                                           @RequestHeader("Cookie") String cookie,
                                           @RequestHeader("X-CSRF-Token") String csrfToken) {
        var dto = studentApiClient.getById(id,cookie,csrfToken);
        // TODO Call PDF report service

        byte[] pdf = null;

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=student-" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
