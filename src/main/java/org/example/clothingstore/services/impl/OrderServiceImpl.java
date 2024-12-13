package org.example.clothingstore.services.impl;

import jakarta.validation.ConstraintViolation;
import org.example.clothingstore.dto.OrderDTO;
import org.example.clothingstore.entities.Order;
import org.example.clothingstore.entities.OrderStatusEnum;
import org.example.clothingstore.entities.User;
import org.example.clothingstore.repositories.OrderRepository;
import org.example.clothingstore.services.DiscountCouponService;
import org.example.clothingstore.services.OrderService;
import org.example.clothingstore.services.UserService;
import org.example.clothingstore.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

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
//        order.setUser(userService.findByUsername(orderDto.getUser().getUsername()));
//        order.setDiscountCoupon(discountCouponService.findById(orderDto.getDiscountCoupon().getId()));
        orderRepository.save(order);
    }

    @Override
    public Page<OrderDTO> getOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Order> couponsPage = orderRepository.findAll(pageable);
        return couponsPage.map(o -> new OrderDTO(o.getId(), o.getUser(), o.getDiscountCoupon(),
                o.getDate(), o.getOrderAmount(), o.getOrderStatus(), o.getQuantityOfProducts()));
    }

    @Override
    public OrderDTO getOrder(String id) {
        try {
            var order = orderRepository.findById(id);
            return new OrderDTO(order.getId(), order.getUser(), order.getDiscountCoupon(), order.getDate(),
                    order.getOrderAmount(), order.getOrderStatus(), order.getQuantityOfProducts());
        }
        catch (RuntimeException e) {
            System.out.println("Заказ не найден");
            return null;
        }
    }

    @Override
    public String createOrder(String username, float discountPercentage, Date date, float orderAmount,
                              String orderStatus, int quantityOfProducts) {
        Order newOrder = new Order(userService.findByUsername(username),
                discountCouponService.findByDiscountPercentage(discountPercentage), date, orderAmount,
                OrderStatusEnum.valueOf(orderStatus), quantityOfProducts);
        orderRepository.save(newOrder);
        return newOrder.getId();
    }

}
