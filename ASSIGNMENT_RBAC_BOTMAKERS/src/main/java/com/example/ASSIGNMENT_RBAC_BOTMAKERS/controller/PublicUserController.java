package com.example.ASSIGNMENT_RBAC_BOTMAKERS.controller;

import com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto.*;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.services.PublicUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/public")
@Slf4j
public class PublicUserController {

    @Autowired
    private PublicUserService publicUserService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterUserResponseDTO>> userRegister(@Valid @RequestBody RegisterUserDTO registerUserDTO){
        ApiResponse<RegisterUserResponseDTO> apiResponse=this.publicUserService.registerNewUser(registerUserDTO);
        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoggingResponseDTO>> login( @Valid @RequestBody AuthUserDTO authUserDTO){
        ApiResponse<LoggingResponseDTO> apiResponse=this.publicUserService.loggingIn(authUserDTO);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
