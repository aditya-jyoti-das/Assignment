package com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
public class ErrorResponse {
    private String status;
    private String message;
    private String path;
    private String timestamp;
    private List<FieldErrorDetail> errors;

    public ErrorResponse(String message, String path, List<FieldErrorDetail> errors) {
        this.status = "error";
        this.message = message;
        this.path = path;
        this.errors = errors;
        this.timestamp = Instant.now().toString();
    }

    public ErrorResponse() {
    }

    @Setter
    @Getter
    public static class FieldErrorDetail {
        private String field;
        private String error;

        public FieldErrorDetail(String field, String error) {
            this.field = field;
            this.error = error;
        }

        @Override
        public String toString() {
            return "FieldErrorDetail{" +
                    "field='" + field + '\'' +
                    ", error='" + error + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                ", timestamp=" + timestamp +
                ", errors=" + errors +
                '}';
    }
}