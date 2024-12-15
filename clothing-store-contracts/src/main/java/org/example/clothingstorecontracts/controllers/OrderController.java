package org.example.clothingstorecontracts.controllers;

import jakarta.validation.Valid;
import org.example.clothingstorecontracts.input.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/orders")
public interface OrderController {

    @GetMapping("/completed")
    String listOrders(
            @ModelAttribute("form") OrderSearchForm form,
            Model model
    );

    @GetMapping
    String orderDetails(@PathVariable String orderId, Model model);

//    @GetMapping("/create")
//    String createOrderForm(Model model);

    @PostMapping("/create")
    String createOrder(
    );

//    @GetMapping("/{orderId}/edit")
//    String editOrderForm(
//            @PathVariable("orderId") String id,
//            Model model
//    );
//
//    @PostMapping("/{orderId}/edit")
//    String editOrder(
//            @PathVariable String orderId,
//            @Valid @ModelAttribute("form") OrderEditForm form,
//            BindingResult bindingResult,
//            Model model
//    );


}
