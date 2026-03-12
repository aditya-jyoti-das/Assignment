package com.example.ASSIGNMENT_RBAC_BOTMAKERS.controller;


import com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto.ApiResponse;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto.ProductDTO;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
public class AuthAdminController {


    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> addProduct(@Valid @RequestBody ProductDTO productDTO){
        ApiResponse<String> stringApiResponse = this.productService.addProduct(productDTO);
        return new ResponseEntity<>(stringApiResponse, HttpStatus.OK);
    }
}
