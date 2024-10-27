package org.example.clothingstore.repositories;

import org.example.clothingstore.entities.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {

    void save(Order order);

}
