package org.example.clothingstore.repositories;

import org.example.clothingstore.entities.ClothingCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothingCategoryRepository {

    void save(ClothingCategory clothingCategory);
//    ClothingCategory findById(Class<ClothingCategory> clothingCategoryClass, int id);
    List<ClothingCategory> findAll();
    ClothingCategory findByCategoryName(String categoryName);

}
