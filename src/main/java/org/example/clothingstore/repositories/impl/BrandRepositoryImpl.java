package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.clothingstore.entities.Brand;
import org.example.clothingstore.entities.ClothingCategory;
import org.example.clothingstore.repositories.BaseCRRepository;
import org.example.clothingstore.repositories.BrandRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrandRepositoryImpl extends BaseCRRepository<Brand> implements BrandRepository {

    @PersistenceContext
    private EntityManager em;

    public BrandRepositoryImpl() {
        super(Brand.class);
    }

    @Override
    public Brand findByBrandName(String brandName) {
        return em.createQuery("SELECT b FROM Brand b WHERE b.brandName = :brandName", Brand.class).
                setParameter("brandName", brandName).getSingleResult();
    }

    @Override
    public void saveAll(List<Brand> brands) {
        for (Brand brand : brands) {
            em.persist(brand);
        }
    }

    @Override
    public Brand findById(String id) {
        return em.find(Brand.class, id);
    }
}
