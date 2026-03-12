package com.example.ASSIGNMENT_RBAC_BOTMAKERS.config.productdb.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class Product {

    @JsonProperty("name")
    @NotBlank(message = "Password is required")
    private String name;

    @JsonProperty("price")
    @NotNull(message = "Password is required")
    @DecimalMin(value = "0.0",message = "price should be greater than 0.0")
    private Double price;

    @JsonProperty("imageUrl")
    @NotBlank(message = "Password is required")
    private String imageUrl;

    @JsonProperty("rating")
    @DecimalMin(value = "0.0",message = "rating should be greater than 0.0")
    private Double rating;

    @JsonProperty("currency")
    @NotBlank(message = "currency can't be empty! put INR")
    private String currency;


}
