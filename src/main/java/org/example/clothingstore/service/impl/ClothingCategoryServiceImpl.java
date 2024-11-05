package org.example.clothingstore.service.impl;

import jakarta.validation.ConstraintViolation;
import org.example.clothingstore.dto.BrandDTO;
import org.example.clothingstore.dto.ClothingCategoryDTO;
import org.example.clothingstore.entities.Brand;
import org.example.clothingstore.entities.ClothingCategory;
import org.example.clothingstore.entities.SeasonEnum;
import org.example.clothingstore.repositories.BrandRepository;
import org.example.clothingstore.repositories.ClothingCategoryRepository;
import org.example.clothingstore.service.ClothingCategoryService;
import org.example.clothingstore.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothingCategoryServiceImpl implements ClothingCategoryService {

    private final ClothingCategoryRepository clothingCategoryRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public ClothingCategoryServiceImpl(ClothingCategoryRepository clothingCategoryRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.clothingCategoryRepository = clothingCategoryRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addClothingCategory(String categoryName, SeasonEnum season) {
        ClothingCategoryDTO clothingCategoryDto = new ClothingCategoryDTO();
        clothingCategoryDto.setCategoryName(categoryName);
        clothingCategoryDto.setSeason(season);
        if (!this.validationUtil.isValid(clothingCategoryDto)) {
            this.validationUtil.violations(clothingCategoryDto).stream().map(ConstraintViolation::getMessage).
                    forEach(System.out::println);
        }
        else {
            try {
                this.clothingCategoryRepository.save(modelMapper.map(clothingCategoryDto, ClothingCategory.class));
            } catch (Exception e) {
                System.out.println("Error while saving clothing category: " + e.getMessage());
            }
        }
    }

    @Override
    public List<ClothingCategory> findAll() {
        return this.clothingCategoryRepository.findAll();
    }

    @Override
    public ClothingCategory findByCategoryName(String categoryName) {
        return this.clothingCategoryRepository.findByCategoryName(categoryName);
    }
}
