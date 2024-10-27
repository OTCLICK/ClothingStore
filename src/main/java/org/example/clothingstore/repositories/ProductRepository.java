package org.example.clothingstore.repositories;

import org.example.clothingstore.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {

    void save(Product product);
    void update(Product product);
    Product findById(Class<Product> productClass, int id);
    List<Product> findAll();

}
