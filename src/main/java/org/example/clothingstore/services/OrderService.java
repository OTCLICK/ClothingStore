package org.example.clothingstore.services;

import org.example.clothingstore.dto.DiscountCouponDTO;
import org.example.clothingstore.dto.OrderDTO;
import org.example.clothingstore.dto.ProductDTO;
import org.example.clothingstore.entities.Order;
import org.example.clothingstore.entities.OrderStatusEnum;
import org.example.clothingstore.entities.User;
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

    User getUserByOrderId(String orderId);

    void updateOrderAmount(String id, float orderAmount);

    void updateQuantityOfProducts(String id);

    void addProductToOrder(String id, ProductDTO productDTO);

    void updateOrderStatus(String id);

    void updateDiscountCoupon(String id, DiscountCouponDTO discountCouponDTO);

//    public void addProductToOrder(OrderDTO orderDTO, ProductDTO productDTO);



}
