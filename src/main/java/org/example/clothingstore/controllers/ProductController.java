package org.example.clothingstore.controllers;

import org.example.clothingstore.dto.ProductDTO;
import org.example.clothingstore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public void createProduct(@RequestBody ProductDTO newProduct) {
        productService.addProduct(newProduct);
    }
}
