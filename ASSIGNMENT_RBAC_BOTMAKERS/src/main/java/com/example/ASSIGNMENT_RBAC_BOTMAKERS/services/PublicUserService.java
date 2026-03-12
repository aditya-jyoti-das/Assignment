package com.example.ASSIGNMENT_RBAC_BOTMAKERS.services;

import com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto.*;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.entity.CustomUserDetails;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.userdb.entity.User;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.userdb.repository.UserRepository;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PublicUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public ApiResponse<RegisterUserResponseDTO> registerNewUser(RegisterUserDTO registerUserDTO) {
        try {
            User user = new User(registerUserDTO);
            user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
            User save = this.userRepository.save(user);
            RegisterUserResponseDTO registerUserResponseDTO = new RegisterUserResponseDTO(save);
            return new ApiResponse<>("success", "Successfully Registered", registerUserResponseDTO);
        } catch (RuntimeException e) {
            throw e;
        }
    }


    public ApiResponse<LoggingResponseDTO> loggingIn(AuthUserDTO authUserDTO) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authUserDTO.getUserName(), authUserDTO.getPassword()));
            CustomUserDetails userDetails = (CustomUserDetails) customUserDetailService.loadUserByUsername(authUserDTO.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            Date expiringDate = jwtUtil.extractExpiration(jwt);
            LoggingResponseDTO loggingResponseDTO = new LoggingResponseDTO(jwt, expiringDate, userDetails);
            return new ApiResponse<>("success", "Login Successfully", loggingResponseDTO);
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException(ex.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
