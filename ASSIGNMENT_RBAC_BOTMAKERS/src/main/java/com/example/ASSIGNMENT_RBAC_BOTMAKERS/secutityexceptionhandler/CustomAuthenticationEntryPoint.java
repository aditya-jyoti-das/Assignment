package com.example.ASSIGNMENT_RBAC_BOTMAKERS.secutityexceptionhandler;

import com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (!response.isCommitted()) {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            ErrorResponse errorResponse = new ErrorResponse(
                    authException.getMessage()  + ":: Unauthorized access-1 - please login again",
                    request.getRequestURI(),
                    null);
            try {
                objectMapper.writeValue(response.getOutputStream(), errorResponse);
                log.info("CustomAuthenticationEntryPoint is invoked");
            } catch (Exception e) {
                e.printStackTrace(); // or log
            }
        }else{
            log.info("response is already commited");
        }
    }
}
