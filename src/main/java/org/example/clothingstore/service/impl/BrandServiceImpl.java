package org.example.clothingstore.service.impl;

import jakarta.validation.ConstraintViolation;
import org.example.clothingstore.dto.BrandDTO;
import org.example.clothingstore.entities.Brand;
import org.example.clothingstore.repositories.BrandRepository;
import org.example.clothingstore.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.ValidationUtils;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ValidationUtils validationUtils;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ValidationUtils validationUtils, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.validationUtils = validationUtils;
        this.modelMapper = modelMapper;
    }

//    @Override
//    public void addBrand(String brandName) {
//        BrandDTO brandDTO = new BrandDTO();
//        brandDTO.setBrandName(brandName);
//        if (!this.)
//    }
//
//    @Override
//    public Brand findByBrandName(String brandName) {
//        return null;
//    }
}
