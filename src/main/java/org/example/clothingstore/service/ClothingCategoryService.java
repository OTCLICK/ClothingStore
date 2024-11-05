package org.example.clothingstore.service;

import org.example.clothingstore.entities.ClothingCategory;

import java.util.List;

public interface ClothingCategoryService {

    void addClothingCategory(ClothingCategory clothingCategory);
    List<ClothingCategory> findAll();
    ClothingCategory findByCategoryName(String categoryName);
}
