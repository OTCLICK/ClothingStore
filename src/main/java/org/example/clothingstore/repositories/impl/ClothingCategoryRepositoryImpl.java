package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.clothingstore.entities.ClothingCategory;
import org.example.clothingstore.repositories.BaseCRRepository;
import org.example.clothingstore.repositories.ClothingCategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClothingCategoryRepositoryImpl extends BaseCRRepository<ClothingCategory> implements ClothingCategoryRepository {

    @PersistenceContext
    private EntityManager em;

    public ClothingCategoryRepositoryImpl() {
        super(ClothingCategory.class);
    }

    @Override
    public List<ClothingCategory> findAll() {
        return em.createQuery("SELECT cc FROM ClothingCategory cc", ClothingCategory.class).getResultList();
    }

    @Override
    public ClothingCategory findByCategoryName(String categoryName) {
        return em.createQuery("SELECT cc FROM ClothingCategory cc WHERE cc.categoryName = :categoryName",
                        ClothingCategory.class).setParameter("categoryName", categoryName).getSingleResult();
    }

    @Override
    public void saveAll(List<ClothingCategory> clothingCategories) {
        for (ClothingCategory cc : clothingCategories) {
            em.persist(cc);
        }
    }

    @Override
    public ClothingCategory findById(String id) {
        return em.find(ClothingCategory.class, id);
    }
}
