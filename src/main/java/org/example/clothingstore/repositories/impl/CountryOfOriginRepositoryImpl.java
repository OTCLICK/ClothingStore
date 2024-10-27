package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.clothingstore.entities.CountryOfOrigin;
import org.example.clothingstore.repositories.BaseCURepository;
import org.example.clothingstore.repositories.CountryOfOriginRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CountryOfOriginRepositoryImpl extends BaseCURepository<CountryOfOrigin> implements CountryOfOriginRepository {

    @PersistenceContext
    private EntityManager em;

    public CountryOfOriginRepositoryImpl() {
        super(CountryOfOrigin.class);
    }

}
