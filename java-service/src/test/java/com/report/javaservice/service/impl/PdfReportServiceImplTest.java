package com.report.javaservice.service.impl;

import com.report.javaservice.exceptions.PdfGenerationException;
import com.report.javaservice.service.domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openpdf.text.pdf.PdfReader;
import org.openpdf.text.pdf.parser.PdfTextExtractor;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PdfReportServiceImplTest {

    private PdfReportServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new PdfReportServiceImpl();
    }

    @Test
    void generate_shouldCreatePdfWithExpectedContent_whenStudentIsValid() throws Exception {
        // Arrange
        Student student = Student.builder()
                .id(1L)
                .name("Tomás Becher")
                .email("tomas@example.com")
                .birthDate(LocalDate.of(1997, 3, 12))
                .systemAccess(true)
                .className("Math")
                .fatherName("Juan")
                .motherName("Maria")
                .guardianName("Pepe")
                .relationOfGuardian("Uncle")
                .currentAddress("Street 1")
                .admissionDate(LocalDate.of(2025, 12, 1))
                .build();

        // Act
        byte[] pdfBytes = service.generate(student);

        // Assert
        assertNotNull(pdfBytes);
        assertTrue(pdfBytes.length > 0);

        PdfReader reader = new PdfReader(pdfBytes);
        PdfTextExtractor extractor = new PdfTextExtractor(reader);
        String text = extractor.getTextFromPage(1);

        assertTrue(text.contains("Student Report"));
        assertTrue(text.contains("Basic Information"));
        assertTrue(text.contains("Name: Tomás Becher"));
        assertTrue(text.contains("System Access: Yes"));
        assertTrue(text.contains("Academic Information"));
        assertTrue(text.contains("Parents & Guardian Information"));
        assertTrue(text.contains("Address Information"));
        assertTrue(text.contains("Admission Information"));
    }

    @Test
    void generate_shouldShowNo_whenSystemAccessIsFalse() throws Exception {
        // Arrange
        Student student = Student.builder()
                .id(2L)
                .name("Test User")
                .systemAccess(false)
                .build();

        // Act
        byte[] pdfBytes = service.generate(student);

        // Assert
        PdfReader reader = new PdfReader(pdfBytes);
        PdfTextExtractor extractor = new PdfTextExtractor(reader);
        String text = extractor.getTextFromPage(1);

        assertTrue(text.contains("System Access: No"));
    }

    @Test
    void generate_shouldThrowPdfGenerationException_whenDocumentFails() {
        // Arrange
        Student student = null;

        // Act & Assert
        PdfGenerationException exception = assertThrows(
                PdfGenerationException.class,
                () -> service.generate(student)
        );

        assertEquals("Error generating student PDF", exception.getMessage());
    }
}