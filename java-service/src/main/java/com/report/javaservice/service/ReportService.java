package com.report.javaservice.service;

public interface ReportService<T> {
    byte[] generate(T data);
}
