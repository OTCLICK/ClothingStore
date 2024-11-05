package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.clothingstore.entities.Brand;
import org.example.clothingstore.entities.Product;
import org.example.clothingstore.repositories.BaseCRURepository;
import org.example.clothingstore.repositories.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl extends BaseCRURepository<Product> implements ProductRepository {

    @PersistenceContext
    private EntityManager em;

    public ProductRepositoryImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    @Override
    public Product findByProductName(String productName) {
        return em.createQuery("SELECT p FROM Product p WHERE p.productName = :productName", Product.class).
                setParameter("productName", productName).getSingleResult();
    }
}
