package org.example.clothingstore.repositories;

import org.example.clothingstore.entities.DiscountCoupon;
import org.example.clothingstore.entities.Order;
import org.example.clothingstore.entities.OrderStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {

    void save(Order order);
    Page<Order> findAll(Pageable pageable);
    void saveAll(List<Order> orders);
    Order findById(String id);

    List<Order> findByStatus(OrderStatusEnum status);

    void updateOrderStatus(String orderId, OrderStatusEnum newStatus);

    void update(Order order);


}
