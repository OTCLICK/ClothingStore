package org.example.clothingstore.service;

import org.example.clothingstore.entities.Product;

import java.util.List;

public interface ProductService {

    void addProduct(Product product);
    List<Product> findAll();
    Product findByProductName(String productName);
}
