package org.example.clothingstore.repositories;

import org.example.clothingstore.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {

    void save(Product product);

    void update(Product product);

    Product findById(String id);

    @Query("SELECT p FROM Product p")
    Page<Product> findAll(Pageable pageable);

    Product findByProductName(String productName);

    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :productName, '%'))")
    Page<Product> findByProductNameContainingIgnoreCase(String productName, Pageable pageable);

    void deleteById(String id);

    void saveAll(List<Product> products);

}
