package org.example.clothingstore.controllers;

import org.example.clothingstore.services.ClothingCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clothing-category")
public class ClothingCategoryController {

    private final ClothingCategoryService clothingCategoryService;

    @Autowired
    public ClothingCategoryController(ClothingCategoryService clothingCategoryService) {
        this.clothingCategoryService = clothingCategoryService;
    }

}
