package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.clothingstore.entities.Brand;
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

}
