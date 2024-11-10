package org.example.clothingstore.services.impl;

import jakarta.validation.ConstraintViolation;
import org.example.clothingstore.dto.BrandDTO;
import org.example.clothingstore.entities.Brand;
import org.example.clothingstore.repositories.BrandRepository;
import org.example.clothingstore.services.BrandService;
import org.example.clothingstore.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addBrand(String brandName) {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setBrandName(brandName);
        if (!this.validationUtil.isValid(brandDTO)) {
            this.validationUtil.violations(brandDTO).stream().map(ConstraintViolation::getMessage).
                    forEach(System.out::println);
        }
        else {
            try {
                this.brandRepository.save(modelMapper.map(brandDTO, Brand.class));
            } catch (Exception e) {
                System.out.println("Error while saving brand: " + e.getMessage());
            }
        }
    }

    @Override
    public Brand findByBrandName(String brandName) {
        return this.brandRepository.findByBrandName(brandName);
    }
}
