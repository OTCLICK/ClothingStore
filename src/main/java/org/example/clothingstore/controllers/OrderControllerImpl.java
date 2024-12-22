package org.example.clothingstore.controllers;

import org.example.clothingstore.dto.*;
import org.example.clothingstore.entities.*;
import org.example.clothingstore.repositories.DiscountCouponRepository;
import org.example.clothingstore.repositories.OrderRepository;
import org.example.clothingstore.repositories.ProductRepository;
import org.example.clothingstore.repositories.UserRepository;
import org.example.clothingstore.services.*;
import org.example.clothingstorecontracts.controllers.OrderController;
import org.example.clothingstorecontracts.input.*;
import org.example.clothingstorecontracts.viewmodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    private final WalletService walletService;
    private final UserService userService;

    @Autowired
    public OrderControllerImpl(OrderService orderService, UserRepository userRepository, ProductService productService,
                               OrderRepository orderRepository, DiscountCouponService discountCouponService,
                               DiscountCouponRepository discountCouponRepository, WalletService walletService,
                               UserService userService) {
        this.orderService = orderService;
        this.userRepository = userRepository;
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.discountCouponService = discountCouponService;
        this.discountCouponRepository = discountCouponRepository;
        this.walletService = walletService;
        this.userService = userService;
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
        return "order-details";
    }

    @Override
    @PostMapping("/create")
    public String createOrder() {
        Date currentDate = new Date();

        // Получаем текущего аутентифицированного пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName(); // Получаем имя пользователя

        // Находим пользователя в базе данных по имени
        User currentUser  = userRepository.findByUsername(currentUsername);
        if (currentUser  == null) {
            // Обработка случая, когда пользователь не найден
            System.out.println("Пользователь не найден: " + currentUsername);
            return "redirect:/error"; // Перенаправление на страницу ошибки
        }

        OrderDTO newOrder = new OrderDTO();
        newOrder.setUser (currentUser ); // Устанавливаем текущего пользователя
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
    public String payOrder(@RequestParam String orderId, RedirectAttributes redirectAttributes) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            System.out.println("Заказ не найден");
            redirectAttributes.addFlashAttribute("message", "Заказ не найден.");
            return "redirect:/orders";
        }

        User user = order.getUser ();
        if (user == null) {
            System.out.println("Пользователь не найден для заказа: " + orderId);
            redirectAttributes.addFlashAttribute("message", "Пользователь не найден.");
            return "redirect:/orders";
        }

        Wallet wallet = walletService.getWallet(user);

        if (wallet.getAmount() >= order.getOrderAmount()) {
            orderRepository.updateOrderStatus(orderId, OrderStatusEnum.PAID);
            wallet.setAmount(wallet.getAmount() - order.getOrderAmount());
            walletService.updateWallet(user.getId(), wallet.getAmount());
            redirectAttributes.addFlashAttribute("message", "Оплата прошла успешно!");
        } else {
            System.out.println("Недостаточно средств для оплаты заказа. Баланс: " + wallet.getAmount() + ", Сумма заказа: " + order.getOrderAmount());
            redirectAttributes.addFlashAttribute("message", "Недостаточно средств для оплаты.");
        }

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
            } else {

            }
        }

        return "redirect:/orders/details?id=" + orderId;
    }
}


