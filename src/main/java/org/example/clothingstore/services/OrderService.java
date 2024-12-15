package org.example.clothingstore.services;

import org.example.clothingstore.dto.DiscountCouponDTO;
import org.example.clothingstore.dto.OrderDTO;
import org.example.clothingstore.dto.ProductDTO;
import org.example.clothingstore.entities.Order;
import org.example.clothingstore.entities.OrderStatusEnum;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

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

    List<Order> getOrdersByStatus(OrderStatusEnum status);

    Order getOrderById(String id);

    void save(Order order);

//    public void addProductToOrder(OrderDTO orderDTO, ProductDTO productDTO);



}
