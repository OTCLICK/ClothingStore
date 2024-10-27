package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.clothingstore.entities.ClothingCategory;
import org.example.clothingstore.repositories.BaseCRRepository;
import org.example.clothingstore.repositories.ClothingCategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClothingCategoryRepositoryimpl extends BaseCRRepository<ClothingCategory> implements ClothingCategoryRepository {

    @PersistenceContext
    private EntityManager em;

    public ClothingCategoryRepositoryimpl() {
        super(ClothingCategory.class);
    }

    @Override
    public List<ClothingCategory> findAll() {
        return em.createQuery("SELECT cc FROM ClothingCategory cc", ClothingCategory.class).getResultList();
    }
}
