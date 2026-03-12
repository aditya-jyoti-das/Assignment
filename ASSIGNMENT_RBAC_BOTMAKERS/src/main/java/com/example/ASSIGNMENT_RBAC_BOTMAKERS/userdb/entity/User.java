package com.example.ASSIGNMENT_RBAC_BOTMAKERS.userdb.entity;

import com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto.RegisterUserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_auth")
public class User {

    private static final Logger log = LoggerFactory.getLogger(User.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "username")
    @NotBlank(message = "UserName is required")
    private String userName;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "password must be at least 8 characters")
    private String password;

    @Column(nullable = false)
    @Email
    @NotBlank(message = "email is required")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority",length = 40, nullable = false)
    @NotNull(message = "Please specify role between USER/ADMIN")
    private Role role;

    public User(String userName, String password, String email, Role role ) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", authority=" + role +
                '}';
    }

    public User(RegisterUserDTO registerUserDTO){
        this.userName = registerUserDTO.getUserName();
        this.password = registerUserDTO.getPassword();
        this.email = registerUserDTO.getEmail();
        this.role = registerUserDTO.getRole();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        else if (obj == null) {
            return false;
        } else if ((obj instanceof User that)) {
            return (this.userName.equals(that.userName)
                    && this.password.equals(that.password)
            );
        } else {
            return false;

        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                this.userName,
                this.password
        );
    }
}
