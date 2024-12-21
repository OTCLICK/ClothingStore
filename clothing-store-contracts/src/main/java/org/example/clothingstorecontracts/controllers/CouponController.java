package org.example.clothingstorecontracts.controllers;

import org.example.clothingstorecontracts.input.CouponSearchForm;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/coupons")
public interface CouponController {

    @GetMapping
    String listCoupons(
            @ModelAttribute("form") CouponSearchForm form,
            Model model
    );

    @GetMapping("/{couponId}")
    String showCoupon(
            @PathVariable("couponId") String couponId,
            Model model
    );

//    @GetMapping("/create")
//    String createCouponForm(Model model);
//
//    @PostMapping("/create")
//    String createCoupon(
//            @Valid @ModelAttribute("form") CouponAddForm form,
//            BindingResult bindingResult,
//            Model model
//    );
//
//    @GetMapping("/{couponId}/delete")
//    String deleteCoupon(
//            @PathVariable("couponId") String couponId,
//            Model model
//    );

}
