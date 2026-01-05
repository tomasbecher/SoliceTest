package com.report.javaservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record StudentDto(
        Long id,
        String name,
        String email,
        Boolean systemAccess,
        String phone,
        String gender,
        LocalDate dob,

        @JsonProperty("class")
        String className,

        String section,
        Integer roll,
        String fatherName,
        String fatherPhone,
        String motherName,
        String motherPhone,
        String guardianName,
        String guardianPhone,
        String relationOfGuardian,
        String currentAddress,
        String permanentAddress,
        LocalDate admissionDate,
        String reporterName
) {}
