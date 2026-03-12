package com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto;

import com.example.ASSIGNMENT_RBAC_BOTMAKERS.entity.CustomUserDetails;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.userdb.entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoggingResponseDTO {

    @JsonProperty("jwtToken")
    private String token;

    @JsonProperty("expiringIn")
    private Date date;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("role")
    private Role role;

    public LoggingResponseDTO(String jwtToken,Date expiringDate,CustomUserDetails customUserDetails){
        this.token=jwtToken;
        this.date=expiringDate;
        this.userId=customUserDetails.getUser().getId();
        this.userName=customUserDetails.getUser().getUserName();
        this.email=customUserDetails.getUser().getEmail();
        this.role=customUserDetails.getUser().getRole();
    }

    @Override
    public String toString() {
        return "LoggingResponseDTO{" +
                "token='" + token + '\'' +
                ", date=" + date +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
