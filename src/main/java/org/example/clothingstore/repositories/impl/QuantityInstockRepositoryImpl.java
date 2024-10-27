package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.clothingstore.entities.QuantityInStock;
import org.example.clothingstore.repositories.BaseCURepository;
import org.example.clothingstore.repositories.QuantityInStockRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuantityInstockRepositoryImpl extends BaseCURepository<QuantityInStock> implements QuantityInStockRepository {

    @PersistenceContext
    private EntityManager em;

    public QuantityInstockRepositoryImpl() {
        super(QuantityInStock.class);
    }

    @Override
    public List<QuantityInStock> findByQuantityOfProduct(int quantityOfProduct) {
        return em.createQuery("SELECT qis FROM QuantityInStock qis WHERE qis.quantityOfProduct = :quantityOfProduct",
                QuantityInStock.class).setParameter("quantityOfProduct", quantityOfProduct).getResultList();
    }
}
