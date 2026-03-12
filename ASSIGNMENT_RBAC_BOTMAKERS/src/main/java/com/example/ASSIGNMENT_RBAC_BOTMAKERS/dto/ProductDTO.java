package com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @JsonProperty("name")
    @NotBlank(message = "name is required")
    private String name;

    @JsonProperty("price")
    @NotNull(message = "price is required")
    @DecimalMin(value = "0.0",message = "price should be greater than 0.0")
    private Double price;

    @JsonProperty("imageUrl")
    @NotBlank(message = "imageUrl is required")
    private String imageUrl;

    @JsonProperty("rating")
    @DecimalMin(value = "0.0",message = "rating should be greater than 0.0")
    private Double rating;

    @JsonProperty("currency")
    @NotBlank(message = "currency can't be empty! put INR")
    private String currency;
}
