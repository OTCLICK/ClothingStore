package org.example.clothingstore.services.impl;

import jakarta.validation.ConstraintViolation;
import org.example.clothingstore.dto.CountryOfOriginDTO;
import org.example.clothingstore.entities.CountryOfOrigin;
import org.example.clothingstore.repositories.CountryOfOriginRepository;
import org.example.clothingstore.services.BrandService;
import org.example.clothingstore.services.CountryOfOriginService;
import org.example.clothingstore.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryOfOriginServiceImpl implements CountryOfOriginService {

    private final CountryOfOriginRepository countryOfOriginRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final BrandService brandService;

    @Autowired
    public CountryOfOriginServiceImpl(CountryOfOriginRepository countryOfOriginRepository, ValidationUtil validationUtil,
                                      ModelMapper modelMapper, BrandService brandService) {
        this.countryOfOriginRepository = countryOfOriginRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.brandService = brandService;
    }

    @Override
    public void addCountryOfOrigin(CountryOfOriginDTO countryOfOriginDto) {
        if (!this.validationUtil.isValid(countryOfOriginDto)) {
            this.validationUtil.violations(countryOfOriginDto).stream().map(ConstraintViolation::getMessage).
                    forEach(System.out::println);
            throw new IllegalArgumentException("Illegal arguments while saving country of origin");
        }
        CountryOfOrigin countryOfOrigin = this.modelMapper.map(countryOfOriginDto, CountryOfOrigin.class);
        countryOfOrigin.setBrand(brandService.findByBrandName(countryOfOriginDto.getBrand().getBrandName()));
    }
}
