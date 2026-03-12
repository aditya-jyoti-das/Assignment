package com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto;

import com.example.ASSIGNMENT_RBAC_BOTMAKERS.userdb.entity.Role;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.userdb.entity.User;
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
public class RegisterUserResponseDTO {

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("email")
    private String email;

    @JsonProperty( "role")
    private Role role;

    public RegisterUserResponseDTO(User user){
        this.userName=user.getUserName();
        this.email=user.getEmail();
        this.role=user.getRole();
        this.userId=user.getId();
    }


}
