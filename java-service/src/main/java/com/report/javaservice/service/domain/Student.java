package com.report.javaservice.service.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Student {
    private Long id;
    private String name;
    private String email;
    private Boolean systemAccess;
    private String phone;
    private String gender;
    private LocalDate birthDate;
    private String className;
    private String section;
    private Integer roll;
    private String fatherName;
    private String fatherPhone;
    private String motherName;
    private String motherPhone;
    private String guardianName;
    private String guardianPhone;
    private String relationOfGuardian;
    private String currentAddress;
    private String permanentAddress;
    private LocalDate admissionDate;
    private String reporterName;
}