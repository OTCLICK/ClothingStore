package org.example.clothingstore.services.impl;

import jakarta.validation.ConstraintViolation;
import org.example.clothingstore.dto.OrderDTO;
import org.example.clothingstore.entities.Order;
import org.example.clothingstore.repositories.OrderRepository;
import org.example.clothingstore.services.DiscountCouponService;
import org.example.clothingstore.services.OrderService;
import org.example.clothingstore.services.UserService;
import org.example.clothingstore.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    public final OrderRepository orderRepository;
    public final ValidationUtil validationUtil;
    public final ModelMapper modelMapper;
    public final UserService userService;
    public final DiscountCouponService discountCouponService;

    public OrderServiceImpl(OrderRepository orderRepository, ValidationUtil validationUtil, ModelMapper modelMapper,
                            UserService userService, DiscountCouponService discountCouponService) {
        this.orderRepository = orderRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.discountCouponService = discountCouponService;
    }

    @Override
    public void addOrder(OrderDTO orderDto) {
        if (!this.validationUtil.isValid(orderDto)) {
            this.validationUtil.violations(orderDto).stream().map(ConstraintViolation::getMessage).
                    forEach(System.out::println);
            throw new IllegalArgumentException("Illegal arguments while saving order");
        }
        Order order = this.modelMapper.map(orderDto, Order.class);
        order.setUser(userService.findByUsername(orderDto.getUser().getUsername()));
        order.setDiscountCoupon(discountCouponService.findById(orderDto.getDiscountCoupon().getId()));
    }

}
