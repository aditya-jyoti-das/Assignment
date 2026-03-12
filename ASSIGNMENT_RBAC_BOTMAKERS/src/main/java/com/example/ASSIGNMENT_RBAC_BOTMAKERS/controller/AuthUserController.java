package com.example.ASSIGNMENT_RBAC_BOTMAKERS.controller;

import com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto.ApiResponse;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto.ProductDTO;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
@Slf4j
public class AuthUserController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getProducts(){
        ApiResponse<List<ProductDTO>> products = productService.getProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
