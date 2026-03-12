package com.example.ASSIGNMENT_RBAC_BOTMAKERS.config.productdb.repository;

import com.example.ASSIGNMENT_RBAC_BOTMAKERS.config.productdb.entity.Product;
import com.example.ASSIGNMENT_RBAC_BOTMAKERS.dto.ProductDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepo {

    private static final List<Product> products = new ArrayList<Product>();

    public ProductRepo() {
        products.add(new Product(
                "Saucony Men's Kinvara 13 Running Shoe",
                57.79,
                "https://m.media-amazon.com/images/I/71QeGmahUnL._AC_UX500_.jpg",
                4.6,
                "USD"
        ));
        products.add(new Product(
                "Kishigo Premium Reflective Safety Vest",
                28.50,
                "https://m.media-amazon.com/images/I/41PUenVYhML._SY445_SX342_QL70_FMwebp_.jpg",
                4.6,
                "USD"
        ));
        products.add(new Product(
                "TWINSLUXES Solar Post Cap Lights",
                33.99,
                "https://m.media-amazon.com/images/I/71kCgl9yzzL.__AC_SY445_SX342_QL70_FMwebp_.jpg",
                4.2,
                "USD"
        ));

        products.add(new Product(
                "Accutire Digital Tire Pressure Gauge",
                18.00,
                "https://m.media-amazon.com/images/I/41UbPFLOVkL._SY445_SX342_QL70_FMwebp_.jpg",
                4.4,
                "USD"
        ));
    }

    public List<Product> getProductsFromRepo() {
        return products;
    }


    public Boolean addAProduct(Product product) {
        try {
            products.add(product);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
