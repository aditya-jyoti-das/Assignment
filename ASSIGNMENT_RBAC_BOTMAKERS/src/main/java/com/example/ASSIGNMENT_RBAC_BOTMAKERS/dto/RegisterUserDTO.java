package com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto;


import com.example.ASSIGNMENT_RBAC_BOTMAKERS.userdb.entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDTO {

    @NotBlank(message = "UserName is required")
    @JsonProperty("userName")
    private String userName;


    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "password must be at least 8 characters")
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "EMAIL IS REQUIRED")
    @Email
    @JsonProperty("email")
    private String email;

    @NotNull(message = "CHOOSE ROLE BETWEEN USER/ADMIN")
    @Enumerated(EnumType.STRING)
    @JsonProperty(namespace = "role")
    private Role role;

}
