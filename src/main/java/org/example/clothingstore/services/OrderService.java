package org.example.clothingstore.services;

import org.example.clothingstore.dto.DiscountCouponDTO;
import org.example.clothingstore.dto.OrderDTO;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface OrderService {

    void addOrder(OrderDTO orderDto);

    Page<OrderDTO> getOrders(int page, int size);

    OrderDTO getOrder(String id);

    String createOrder(String username,
                       float discountPercentage,
                       Date date,
                       float orderAmount,
                       String orderStatus,
                       int quantityOfProducts);


}
