package org.example.clothingstore.service;

import org.example.clothingstore.dto.ClothingCategoryDTO;
import org.example.clothingstore.entities.ClothingCategory;
import org.example.clothingstore.entities.SeasonEnum;

import java.util.List;

public interface ClothingCategoryService {

    void addClothingCategory(String categoryName, SeasonEnum season);
    List<ClothingCategory> findAll();
    ClothingCategory findByCategoryName(String categoryName);
}
