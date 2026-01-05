package com.report.javaservice.service.impl;

import com.report.javaservice.exceptions.PdfGenerationException;
import com.report.javaservice.service.ReportService;
import com.report.javaservice.service.domain.Student;
import org.openpdf.text.*;
import org.openpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfReportServiceImpl implements ReportService<Student> {

    private static final Font TITLE_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
    private static final Font SECTION_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);

    @Override
    public byte[] generate(Student student) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (Document document = new Document()) {
            PdfWriter.getInstance(document, out);
            document.open();

            Paragraph paragraph = new Paragraph("Student Report", TITLE_FONT);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            addSection(document, "Basic Information");
            document.add(new Paragraph("ID: " + student.getId()));
            document.add(new Paragraph("Name: " + student.getName()));
            document.add(new Paragraph("Email: " + student.getEmail()));
            document.add(new Paragraph("Phone: " + student.getPhone()));
            document.add(new Paragraph("Gender: " + student.getGender()));
            document.add(new Paragraph("Date of Birth: " + student.getBirthDate()));
            document.add(new Paragraph("System Access: " + getSystemAccessLabel(student.getSystemAccess())));

            addSection(document, "Academic Information");
            document.add(new Paragraph("Class: " + student.getClassName()));
            document.add(new Paragraph("Section: " + student.getSection()));
            document.add(new Paragraph("Roll: " + student.getRoll()));

            addSection(document, "Parents & Guardian Information");
            document.add(new Paragraph("Father Name: " + student.getFatherName()));
            document.add(new Paragraph("Father Phone: " + student.getFatherPhone()));
            document.add(new Paragraph("Mother Name: " + student.getMotherName()));
            document.add(new Paragraph("Mother Phone: " + student.getMotherPhone()));

            document.add(new Paragraph("Guardian Name: " + student.getGuardianName()));
            document.add(new Paragraph("Guardian Phone: " + student.getGuardianPhone()));
            document.add(new Paragraph("Relation of Guardian: " + student.getRelationOfGuardian()));

            addSection(document, "Address Information");
            document.add(new Paragraph("Current Address: " + student.getCurrentAddress()));
            document.add(new Paragraph("Permanent Address: " + student.getPermanentAddress()));

            addSection(document, "Admission Information");
            document.add(new Paragraph("Admission Date: " + student.getAdmissionDate()));
            document.add(new Paragraph("Reporter Name: " + student.getReporterName()));

        } catch (Exception e) {
            throw new PdfGenerationException("Error generating student PDF", e);
        }

        return out.toByteArray();
    }

    private String getSystemAccessLabel(Boolean hasAccess) {
        return Boolean.TRUE.equals(hasAccess) ? "Yes" : "No";
    }

    private void addSection(Document document, String title) {
        document.add(new Paragraph(" "));
        Paragraph paragraph = new Paragraph(title, SECTION_FONT);
        paragraph.setAlignment(Element.ALIGN_LEFT);

        document.add(paragraph);
    }

}
