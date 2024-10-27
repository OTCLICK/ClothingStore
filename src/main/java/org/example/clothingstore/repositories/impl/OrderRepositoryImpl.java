package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.clothingstore.entities.Order;
import org.example.clothingstore.repositories.BaseCRepository;
import org.example.clothingstore.repositories.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl extends BaseCRepository<Order> implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public OrderRepositoryImpl() {
        super(Order.class);
    }

}
