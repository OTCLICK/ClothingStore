package org.example.clothingstore.services;

import org.example.clothingstore.entities.ClothingCategory;
import org.example.clothingstore.entities.SeasonEnum;

import java.util.List;

public interface ClothingCategoryService {

    void addClothingCategory(String categoryName, SeasonEnum season);
    List<ClothingCategory> findAll();
    ClothingCategory findByCategoryName(String categoryName);
}
