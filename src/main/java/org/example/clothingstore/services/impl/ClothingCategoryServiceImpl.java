package org.example.clothingstore.services.impl;

import jakarta.validation.ConstraintViolation;
import org.example.clothingstore.dto.ClothingCategoryDTO;
import org.example.clothingstore.entities.ClothingCategory;
import org.example.clothingstore.entities.SeasonEnum;
import org.example.clothingstore.repositories.ClothingCategoryRepository;
import org.example.clothingstore.services.ClothingCategoryService;
import org.example.clothingstore.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableCaching
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
    @CacheEvict(cacheNames = "clothingCategories", allEntries = true)
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
    @Cacheable("clothingCategories")
    public List<ClothingCategory> findAll() {
        return this.clothingCategoryRepository.findAll();
    }

    @Override
    @Cacheable("clothingCategories")
    public ClothingCategory findByCategoryName(String categoryName) {
        return clothingCategoryRepository.findByCategoryName(categoryName);
    }
}
