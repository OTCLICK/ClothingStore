package org.example.clothingstorecontracts.controllers;

import jakarta.validation.Valid;
import org.example.clothingstorecontracts.input.CouponAddForm;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/coupons")
public interface AdminCouponController {

    @GetMapping("/create")
    String createCouponForm(Model model);

    @PostMapping("/create")
    String createCoupon(
            @Valid @ModelAttribute("form") CouponAddForm form,
            BindingResult bindingResult,
            Model model
    );

    @GetMapping("/{couponId}/delete")
    String deleteCoupon(
            @PathVariable("couponId") String couponId,
            Model model
    );

}
