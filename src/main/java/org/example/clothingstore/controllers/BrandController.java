package org.example.clothingstore.controllers;

import org.example.clothingstore.dto.BrandDTO;
import org.example.clothingstore.entities.Brand;
import org.example.clothingstore.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/add/{brandName}")
    public void createBrand(@PathVariable String brandName) {
        brandService.addBrand(brandName);
    }

}
