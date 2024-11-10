package org.example.clothingstore.services.impl;

import jakarta.validation.ConstraintViolation;
import org.example.clothingstore.dto.QuantityInStockDTO;
import org.example.clothingstore.entities.QuantityInStock;
import org.example.clothingstore.repositories.QuantityInStockRepository;
import org.example.clothingstore.services.ProductService;
import org.example.clothingstore.services.QuantityInStockService;
import org.example.clothingstore.utils.ValidationUtil;
import org.modelmapper.ModelMapper;

import java.util.List;

public class QuantityInStockServiceImpl implements QuantityInStockService {

    public final QuantityInStockRepository quantityInStockRepository;
    public final ValidationUtil validationUtil;
    public final ModelMapper modelMapper;
    public final ProductService productService;

    public QuantityInStockServiceImpl(QuantityInStockRepository quantityInStockRepository, ValidationUtil validationUtil,
                                      ModelMapper modelMapper, ProductService productService) {
        this.quantityInStockRepository = quantityInStockRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.productService = productService;
    }

    @Override
    public void addQuantityInStock(QuantityInStockDTO quantityInStockDto) {
        if (!this.validationUtil.isValid(quantityInStockDto)) {
            this.validationUtil.violations(quantityInStockDto).stream().map(ConstraintViolation::getMessage).
                    forEach(System.out::println);
            throw new IllegalArgumentException("Illegal arguments while saving quantity in stock");
        }
        QuantityInStock quantityInStock = this.modelMapper.map(quantityInStockDto, QuantityInStock.class);
        quantityInStock.setProduct(productService.findByProductName(quantityInStockDto.getProduct().getProductName()));
    }

    @Override
    public List<QuantityInStock> findByQuantityOfProducts(int quantityOfProducts) {
        return this.quantityInStockRepository.findByQuantityOfProduct(quantityOfProducts);
    }
}
