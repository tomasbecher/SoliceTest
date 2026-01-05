package com.report.javaservice.controller.mapper;

import com.report.javaservice.controller.dto.StudentDto;
import com.report.javaservice.service.domain.Student;

public class StudentMapper {

    public static Student toModel(StudentDto dto) {
        return Student.builder()
                .id(dto.id())
                .name(dto.name())
                .email(dto.email())
                .systemAccess(dto.systemAccess())
                .phone(dto.phone())
                .gender(dto.gender())
                .birthDate(dto.dob())
                .className(dto.className())
                .section(dto.section())
                .roll(dto.roll())
                .fatherName(dto.fatherName())
                .fatherPhone(dto.fatherPhone())
                .motherName(dto.motherName())
                .motherPhone(dto.motherPhone())
                .guardianName(dto.guardianName())
                .guardianPhone(dto.guardianPhone())
                .relationOfGuardian(dto.relationOfGuardian())
                .currentAddress(dto.currentAddress())
                .permanentAddress(dto.permanentAddress())
                .admissionDate(dto.admissionDate())
                .reporterName(dto.reporterName())
                .build();
    }
}
