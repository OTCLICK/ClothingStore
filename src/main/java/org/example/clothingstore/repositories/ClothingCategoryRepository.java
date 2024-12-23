package org.example.clothingstore.repositories;

import org.example.clothingstore.entities.ClothingCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothingCategoryRepository {

    void save(ClothingCategory clothingCategory);
    List<ClothingCategory> findAll();
    ClothingCategory findByCategoryName(String categoryName);
    void saveAll(List<ClothingCategory> clothingCategories);
    ClothingCategory findById(String id);

}
