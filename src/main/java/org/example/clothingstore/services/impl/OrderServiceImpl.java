package org.example.clothingstore.services.impl;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import org.example.clothingstore.dto.OrderDTO;
import org.example.clothingstore.dto.ProductDTO;
import org.example.clothingstore.entities.Order;
import org.example.clothingstore.entities.OrderStatusEnum;
import org.example.clothingstore.entities.Product;
import org.example.clothingstore.entities.User;
import org.example.clothingstore.repositories.OrderRepository;
import org.example.clothingstore.services.DiscountCouponService;
import org.example.clothingstore.services.OrderService;
import org.example.clothingstore.services.UserService;
import org.example.clothingstore.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@EnableCaching
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final DiscountCouponService discountCouponService;

    public OrderServiceImpl(OrderRepository orderRepository, ValidationUtil validationUtil, ModelMapper modelMapper,
                            UserService userService, DiscountCouponService discountCouponService) {
        this.orderRepository = orderRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.discountCouponService = discountCouponService;
    }

    @Override
    @CacheEvict(cacheNames = "orders", allEntries = true)
    public void addOrder(OrderDTO orderDto) {
        if (!this.validationUtil.isValid(orderDto)) {
            this.validationUtil.violations(orderDto).stream().map(ConstraintViolation::getMessage).
                    forEach(System.out::println);
            throw new IllegalArgumentException("Illegal arguments while saving order");
        }
        Order order = this.modelMapper.map(orderDto, Order.class);
        orderRepository.save(order);
    }

    @Override
//    @Cacheable("orders")
    public Page<OrderDTO> getOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Order> couponsPage = orderRepository.findAll(pageable);
        return couponsPage.map(o -> new OrderDTO(o.getId(), o.getUser(), o.getDiscountCoupon(),
                o.getDate(), o.getOrderAmount(), o.getOrderStatus(), o.getQuantityOfProducts()));
    }

    @Override
//    @Transactional
    @Cacheable("orders")
    public OrderDTO getOrder(String id) {
        try {
            var order = orderRepository.findById(id);
            return new OrderDTO(order.getId(), order.getUser(), order.getDiscountCoupon(), order.getDate(),
                    order.getOrderAmount(), order.getOrderStatus(), order.getQuantityOfProducts());
        } catch (RuntimeException e) {
            System.out.println("Заказ не найден");
            return null;
        }
    }

    @Override
    @CacheEvict(cacheNames = "orders", allEntries = true)
    public String createOrder(String username, float discountPercentage, Date date, float orderAmount,
                              String orderStatus, int quantityOfProducts) {
        Order newOrder = new Order(userService.findByUsername(username),
                discountCouponService.findByDiscountPercentage(discountPercentage), date, orderAmount,
                OrderStatusEnum.valueOf(orderStatus), quantityOfProducts);
        orderRepository.save(newOrder);
        return newOrder.getId();
    }

    @Override
    @Cacheable("orders")
    public List<Order> getOrdersByStatus(OrderStatusEnum status) {
        return orderRepository.findByStatus(status);
    }

    @Override
//    @Cacheable("orders")
    public Order getOrderById(String id) {
        return orderRepository.findById(id);
    }

    @Override
    @CacheEvict(cacheNames = "orders", allEntries = true)
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public User getUserByOrderId(String orderId) {
        return orderRepository.findUserByOrderId(orderId);
    }
}



