package org.example.clothingstore.controllers;

import org.example.clothingstore.entities.SeasonEnum;
import org.example.clothingstore.services.ClothingCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clothing-category")
public class ClothingCategoryController {

    private final ClothingCategoryService clothingCategoryService;

    @Autowired
    public ClothingCategoryController(ClothingCategoryService clothingCategoryService) {
        this.clothingCategoryService = clothingCategoryService;
    }

    @PostMapping("/add")
    public void createClothingCategory(@RequestBody String categoryName, SeasonEnum season) {
        clothingCategoryService.addClothingCategory(categoryName, season);
    }

}
