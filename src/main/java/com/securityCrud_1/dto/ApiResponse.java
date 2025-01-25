package com.securityCrud_1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

    private String status;
    private String message;
    private T data;

    // Constructor, getters y setters
    public ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
