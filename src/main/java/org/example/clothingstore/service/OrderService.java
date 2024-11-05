package org.example.clothingstore.service;

import org.example.clothingstore.dto.OrderDTO;
import org.example.clothingstore.entities.Order;

public interface OrderService {

    void addOrder(OrderDTO orderDto);
}
