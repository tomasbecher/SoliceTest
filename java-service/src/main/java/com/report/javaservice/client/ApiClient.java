package com.report.javaservice.client;

public interface ApiClient<T>  {
    T getById(Long id, String cookie, String csrfToken);
}
