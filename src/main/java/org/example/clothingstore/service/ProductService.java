package org.example.clothingstore.service;

import org.example.clothingstore.dto.ProductDTO;
import org.example.clothingstore.entities.Product;

import java.util.List;

public interface ProductService {

    void addProduct(ProductDTO productDto);
    List<Product> findAll();
    Product findByProductName(String productName);
}
