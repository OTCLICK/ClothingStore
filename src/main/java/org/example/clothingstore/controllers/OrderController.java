package org.example.clothingstore.controllers;

import org.example.clothingstore.dto.CountryOfOriginDTO;
import org.example.clothingstore.dto.OrderDTO;
import org.example.clothingstore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public void createOrder(@RequestBody OrderDTO newOrder) {
        orderService.addOrder(newOrder);
    }
}
