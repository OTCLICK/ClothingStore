package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.clothingstore.entities.DiscountCoupon;
import org.example.clothingstore.entities.Order;
import org.example.clothingstore.entities.Product;
import org.example.clothingstore.repositories.BaseCRepository;
import org.example.clothingstore.repositories.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl extends BaseCRepository<Order> implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public OrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        long total = em.createQuery("SELECT COUNT(o) FROM Order o", Long.class).getSingleResult();
        List<Order> results = em.createQuery("SELECT o FROM Order o", Order.class)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(results, pageable, total);
    }

    @Override
    public void saveAll(List<Order> orders) {
        for (Order order : orders) {
            em.persist(order);
        }
    }

    @Override
    public Order findById(String id) {
        return em.find(Order.class, id);
    }
}
