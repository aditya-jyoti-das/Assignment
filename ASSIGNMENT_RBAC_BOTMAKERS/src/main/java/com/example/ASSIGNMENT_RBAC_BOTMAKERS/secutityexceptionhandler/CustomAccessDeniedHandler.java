package com.example.ASSIGNMENT_RBAC_BOTMAKERS.secutityexceptionhandler;

import com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        if (!response.isCommitted()) {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

            ErrorResponse errorResponse = new ErrorResponse(
                    accessDeniedException.getMessage() + "::" + accessDeniedException.getCause() + ":: Unauthorized access-2 - please login",
                    request.getRequestURI(),
                    null
            );
            System.out.println("custom access denied handler");
            try {
                objectMapper.writeValue(response.getOutputStream(), errorResponse);
            } catch (Exception e) {
                e.printStackTrace(); // or log
            }
        }
    }
}
