package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.clothingstore.entities.*;
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

    @Override
    public List<Order> findByStatus(OrderStatusEnum status) {
        return em.createQuery("SELECT o FROM Order o WHERE o.orderStatus = :status", Order.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Override
    @Transactional
    public void updateOrderStatus(String orderId, OrderStatusEnum newStatus) {
        Order order = em.find(Order.class, orderId);
        if (order != null) {
            order.setOrderStatus(newStatus);
            em.merge(order);
        }
        em.persist(order);
    }

    @Override
    @Transactional
    public void update(Order order) {
        em.merge(order);
    }

    @Override
    public User findUserByOrderId(String orderId) {
        return em.createQuery("SELECT u FROM User u JOIN Order o ON u.id = o.user.id WHERE o.id = :orderId", User.class)
                .setParameter("orderId", orderId)
                .getSingleResult();
    }
}
