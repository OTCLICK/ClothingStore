package org.example.clothingstore.service.impl;

import jakarta.validation.ConstraintViolation;
import org.example.clothingstore.dto.CountryOfOriginDTO;
import org.example.clothingstore.entities.CountryOfOrigin;
import org.example.clothingstore.repositories.CountryOfOriginRepository;
import org.example.clothingstore.service.BrandService;
import org.example.clothingstore.service.CountryOfOriginService;
import org.example.clothingstore.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryOfOriginServiceImpl implements CountryOfOriginService {

    public final CountryOfOriginRepository countryOfOriginRepository;
    public final ValidationUtil validationUtil;
    public final ModelMapper modelMapper;
    public final BrandService brandService;

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
