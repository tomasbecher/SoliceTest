# Java PDF Report Microservice

## Overview

This project is a standalone **Java Spring Boot microservice** responsible for generating **PDF reports** for students.

The service consumes an existing **Node.js backend API**, retrieves student data in JSON format, and generates a **downloadable PDF report** using that information.

---

## Tech Stack

- Java 21
- Spring Boot
- RestTemplate (HTTP client)
- OpenPDF (PDF generation)
- Lombok
- Maven

---

## Architecture

- **Controller**  
  Exposes endpoints for PDF generation.

- **API Client**  
  Responsible for calling the external Node.js backend and forwarding authentication headers.

- **DTOs**  
  Represent external API contracts.

- **Domain Models**  
  Internal business representation of data.

- **Mapper**  
  Converts DTOs to domain models.

- **ReportService**  
  Generic interface for report generation.

- **PDF Implementation**  
  OpenPDF-based implementation for student reports.

---

## Authentication Handling

This service **does not authenticate users**.

It forwards the following headers received from the client to the Node.js backend:

- `Cookie`
- `X-CSRF-Token`

Authentication and authorization remain centralized in the backend API.

---

## API Endpoint

### Generate Student PDF Report

**Endpoint**

`GET /reports/students/{id}`

**Example (local)**

http://localhost:8080/reports/students/2


**Required Headers**

- `Cookie`: `<session cookie>`
- `X-CSRF-Token`: `<csrf token>`

**Response**

- **200 OK**
- **Content-Type:** `application/pdf`
- The PDF file is returned as a downloadable attachment
