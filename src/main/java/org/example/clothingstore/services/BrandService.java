package org.example.clothingstore.services;

import org.example.clothingstore.entities.Brand;

public interface BrandService {

    void addBrand(String brandName);
    Brand findByBrandName(String brandName);
}
