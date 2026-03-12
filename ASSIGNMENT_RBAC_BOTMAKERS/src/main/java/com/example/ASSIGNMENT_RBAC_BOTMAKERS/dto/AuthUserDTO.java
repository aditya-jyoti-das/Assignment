package com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserDTO {
    @Column(nullable = false, unique = true)
    @NotBlank(message = "UserName is required")
    @JsonProperty("userName")
    private String userName;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "password must be at least 8 characters")
    @JsonProperty("password")
    private String password;

}
