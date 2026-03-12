package com.example.ASSIGNMENT_RBAC_BOTMAKERS.services;

import com.example.ASSIGNMENT_RBAC_BOTMAKERS.config.mapper.ProductMapper;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.config.productdb.entity.Product;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.config.productdb.repository.ProductRepo;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto.ApiResponse;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductMapper productMapper;


    public ApiResponse<List<ProductDTO>> getProducts(){
        List<Product> productsFromRepo = this.productRepo.getProductsFromRepo();
        List<ProductDTO> list = productsFromRepo.stream().map(x -> productMapper.toProductDTO(x)).toList();
        return new ApiResponse<>("success","successfully fetched",list);
    }

    public ApiResponse<String> addProduct(ProductDTO productDTO){
        Product product = productMapper.toProduct(productDTO);
        Boolean successfullyAdded=this.productRepo.addAProduct(product);
        if(successfullyAdded){
            return new ApiResponse<>("success","successfullyAdded","addedSuccessfully");
        }else{
            throw new RuntimeException("could not saved data try again");
        }
    }

}
