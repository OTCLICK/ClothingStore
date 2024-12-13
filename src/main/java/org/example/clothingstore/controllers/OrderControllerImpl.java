package org.example.clothingstore.controllers;

import org.example.clothingstore.dto.OrderDTO;
import org.example.clothingstore.entities.Order;
import org.example.clothingstore.entities.OrderStatusEnum;
import org.example.clothingstore.repositories.UserRepository;
import org.example.clothingstore.services.OrderService;
import org.example.clothingstorecontracts.controllers.OrderController;
import org.example.clothingstorecontracts.input.*;
import org.example.clothingstorecontracts.viewmodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("/orders")
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;
    private final UserRepository userRepository;

    @Autowired
    public OrderControllerImpl(OrderService orderService, UserRepository userRepository) {
        this.orderService = orderService;
        this.userRepository = userRepository;
    }

    public BaseViewModel createBaseViewModel(String title, String user) {
        return new BaseViewModel(title, user);
    }

    @Override
    @GetMapping
    public String listOrders(OrderSearchForm form, Model model) {
        int page = form.page() != null ? form.page() : 1;
        int size = form.size() != null ? form.size() : 5;
        form = new OrderSearchForm(page, size);

        var ordersPage = orderService.getOrders(page, size);
        var orderViewModel = ordersPage.stream()
                .map(o -> new OrderViewModel(o.getId(), createBaseViewModel("Список заказов", "Текущий пользователь"),
                        o.getDate(), o.getOrderAmount(),
                        String.valueOf(o.getOrderStatus()), o.getQuantityOfProducts()))
                .toList();
        System.out.println();

        var viewModel = new OrderListViewModel(
                createBaseViewModel("Список заказов", "Текущий пользователь"),
                orderViewModel,
                ordersPage.getTotalPages()
        );

        model.addAttribute("model", viewModel);
        model.addAttribute("form", form);
        return "order-list";
    }

    @Override
    @GetMapping("/{orderId}")
    public String orderDetails(@PathVariable String orderId, Model model) {
        var order = orderService.getOrder(orderId);
        if (order == null) {
            System.out.println("Заказ не найден, перенаправление на /orders");
            return "redirect:/orders";
        }

        Float discountCoupon = (order.getDiscountCoupon() != null) ? order.getDiscountCoupon().getDiscountPercentage() : null;

        var viewModel = new OrderDetailsViewModel(
                new OrderViewModel(orderId,
                        createBaseViewModel("Детали заказа", "Текущий пользователь"), order.getDate(),
                        order.getOrderAmount(), String.valueOf(order.getOrderStatus()), order.getQuantityOfProducts()),
                order.getUser ().getUsername(), discountCoupon);

        model.addAttribute("model", viewModel);
        return "order-details";
    }

//    @Override
//    @GetMapping("/create-order")
//    public String createOrderForm(Model model) {
//        model.addAttribute("form", new OrderAddForm("", "",
//                "", "", "",
//                ""));
//        return "product-create";
//    }

    @Override
    @PostMapping("/create")
    public String createOrder() {
        Date currentDate = new Date();

        OrderDTO newOrder = new OrderDTO();
        newOrder.setUser(userRepository.findByUsername("jon.moen"));
        newOrder.setDate(currentDate);
        newOrder.setOrderStatus(OrderStatusEnum.NOT_PAID);
        newOrder.setOrderAmount(0.0f);
        newOrder.setQuantityOfProducts(0);

        orderService.addOrder(newOrder);

        return "redirect:/orders/" + newOrder.getId();
    }

//    @Override
//    public String editOrderForm(String id, Model model) {
//        return "";
//    }
//
//    @Override
//    public String editOrder(String orderId, OrderEditForm form, BindingResult bindingResult, Model model) {
//        return "";
//    }
}
