package org.example.clothingstore.controllers;

import org.example.clothingstore.dto.*;
import org.example.clothingstore.entities.DiscountCoupon;
import org.example.clothingstore.entities.Order;
import org.example.clothingstore.entities.OrderStatusEnum;
import org.example.clothingstore.entities.Product;
import org.example.clothingstore.repositories.DiscountCouponRepository;
import org.example.clothingstore.repositories.OrderRepository;
import org.example.clothingstore.repositories.ProductRepository;
import org.example.clothingstore.repositories.UserRepository;
import org.example.clothingstore.services.DiscountCouponService;
import org.example.clothingstore.services.OrderService;
import org.example.clothingstore.services.ProductService;
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
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;
    private final UserRepository userRepository;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final DiscountCouponService discountCouponService;
    private final DiscountCouponRepository discountCouponRepository;

    @Autowired
    public OrderControllerImpl(OrderService orderService, UserRepository userRepository, ProductService productService,
                               OrderRepository orderRepository, DiscountCouponService discountCouponService,
                               DiscountCouponRepository discountCouponRepository) {
        this.orderService = orderService;
        this.userRepository = userRepository;
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.discountCouponService = discountCouponService;
        this.discountCouponRepository = discountCouponRepository;
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
        var order = orderService.getOrderById(orderId);
        if (order == null) {
            return "redirect:/orders";
        }

        List<DiscountCouponDTO> availableCoupons = discountCouponService.getAvailableCouponsForOrder(order);

        Float discountCoupon = (order.getDiscountCoupon() != null) ? order.getDiscountCoupon().getDiscountPercentage() : null;

        var viewModel = new OrderCouponDetailsViewModel(
                new OrderViewModel(orderId,
                        createBaseViewModel("Детали заказа", "Текущий пользователь"), order.getDate(),
                        order.getOrderAmount(), String.valueOf(order.getOrderStatus()), order.getQuantityOfProducts()),
                order.getUser().getUsername(), discountCoupon, availableCoupons);

        model.addAttribute("model", viewModel);
        return "order-details"; // Убедитесь, что это правильное имя шаблона
    }



//    @Override
//    @GetMapping("/{orderId}")
//    public String orderDetails(@PathVariable String orderId, Model model) {
//        var order = orderService.getOrder(orderId);
//        if (order == null) {
//            System.out.println("Заказ не найден, перенаправление на /orders");
//            return "redirect:/orders";
//        }
//
//        Float discountCoupon = (order.getDiscountCoupon() != null) ? order.getDiscountCoupon().getDiscountPercentage() : null;
//
//        var viewModel = new OrderDetailsViewModel(
//                new OrderViewModel(orderId,
//                        createBaseViewModel("Детали заказа", "Текущий пользователь"), order.getDate(),
//                        order.getOrderAmount(), String.valueOf(order.getOrderStatus()), order.getQuantityOfProducts()),
//                order.getUser ().getUsername(), discountCoupon);
//
//        model.addAttribute("model", viewModel);
//        return "order-details";
//    }

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
        newOrder.setUser(userRepository.findByUsername("customer"));
        newOrder.setDate(currentDate);
        newOrder.setOrderStatus(OrderStatusEnum.NOT_PAID);
        newOrder.setOrderAmount(0.0f);
        newOrder.setQuantityOfProducts(0);

        orderService.addOrder(newOrder);

        return "redirect:/orders/" + newOrder.getId();
    }

    @PostMapping("/add-product")
    public String addProductToOrder(@RequestParam String orderId, @RequestParam String productId) {
        OrderDTO order = orderService.getOrder(orderId);

        ProductDTO productDTO = productService.getProduct(productId);
        if (productDTO == null) {
            throw new IllegalArgumentException("Product not found with id: " + productId);
        }

        float newOrderAmount = order.getOrderAmount() + productDTO.getPrice();
        int newQuantityOfProducts = order.getQuantityOfProducts() + 1;

        Order trueOrder = orderRepository.findById(orderId);
        if (trueOrder == null) {
            throw new IllegalArgumentException("Order not found with id: " + orderId);
        }

        trueOrder.setOrderAmount(newOrderAmount);
        trueOrder.setQuantityOfProducts(newQuantityOfProducts);

        Product productEntity = new Product(productDTO.getId(), productDTO.getClothingCategory(), productDTO.getBrand(),
                productDTO.getProductName(), productDTO.getColor(), productDTO.getSize(), productDTO.getPrice());

        trueOrder.getProducts().add(productEntity);
        orderRepository.save(trueOrder);

        return "redirect:/orders";
    }



    @PostMapping("/pay")
    public String payOrder(@RequestParam String orderId) {
//        Order order = orderRepository.findById(orderId);
//        order.setOrderStatus(OrderStatusEnum.PAID);
//        orderService.save(order);
        orderRepository.updateOrderStatus(orderId, OrderStatusEnum.PAID);


        return "redirect:/orders";
    }

    @PostMapping("/add-coupon")
    public String addCoupon(@RequestParam String orderId, @RequestParam String couponId) {
        Order order = orderRepository.findById(orderId);
        DiscountCoupon coupon = discountCouponRepository.findById(couponId);

        if (order != null && coupon != null) {
            if (order.getOrderAmount() >= coupon.getMinOrderAmount()) {
                float discountAmount = (order.getOrderAmount() * coupon.getDiscountPercentage()) / 100;
                order.setOrderAmount(order.getOrderAmount() - discountAmount);
                order.setDiscountCoupon(coupon);
                orderRepository.update(order);
                System.out.println("Я всё сделал!!!");
            } else {

            }
        }

        return "redirect:/orders/details?id=" + orderId;
    }
}


