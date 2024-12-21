package org.example.clothingstore.repositories;

import org.example.clothingstore.entities.Brand;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository {

    void save(Brand brand);
//    Brand findById(Class<Brand> brandClass, int id);
    Brand findByBrandName(String brandName);
    void saveAll(List<Brand> brands);
    Brand findById(String id);

}
