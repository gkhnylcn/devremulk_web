package com.devremulk.web.dto;

public class AuthLoginResponse {
    private String message;

    public AuthLoginResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
