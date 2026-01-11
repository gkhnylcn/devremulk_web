package com.devremulk.web.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private final String message;
    private final String path;
    private final LocalDateTime timestamp;

    public ErrorResponse(String message, String path, LocalDateTime timestamp) {
        this.message = message;
        this.path = path;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
