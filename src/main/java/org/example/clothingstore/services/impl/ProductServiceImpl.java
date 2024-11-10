package org.example.clothingstore.services.impl;

import jakarta.validation.ConstraintViolation;
import org.example.clothingstore.dto.ProductDTO;
import org.example.clothingstore.entities.Product;
import org.example.clothingstore.repositories.ProductRepository;
import org.example.clothingstore.services.BrandService;
import org.example.clothingstore.services.ClothingCategoryService;
import org.example.clothingstore.services.ProductService;
import org.example.clothingstore.utils.ValidationUtil;
import org.modelmapper.ModelMapper;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    public final ProductRepository productRepository;
    public final ValidationUtil validationUtil;
    public final ModelMapper modelMapper;
    public final ClothingCategoryService clothingCategoryService;
    public final BrandService brandService;

    public ProductServiceImpl(ProductRepository productRepository, ValidationUtil validationUtil, ModelMapper modelMapper,
                              ClothingCategoryService clothingCategoryService, BrandService brandService) {
        this.productRepository = productRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.clothingCategoryService = clothingCategoryService;
        this.brandService = brandService;
    }

    @Override
    public void addProduct(ProductDTO productDto) {
        if (!this.validationUtil.isValid(productDto)) {
            this.validationUtil.violations(productDto).stream().map(ConstraintViolation::getMessage).
                    forEach(System.out::println);
            throw new IllegalArgumentException("Illegal arguments while saving product");
        }
        Product product = this.modelMapper.map(productDto, Product.class);
        product.setClothingCategory(clothingCategoryService.findByCategoryName(productDto.getClothingCategory().
                getCategoryName()));
        product.setBrand(brandService.findByBrandName(productDto.getBrand().getBrandName()));
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findByProductName(String productName) {
        return this.productRepository.findByProductName(productName);
    }
}
