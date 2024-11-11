package org.example.clothingstore.controllers;

import org.example.clothingstore.dto.CountryOfOriginDTO;
import org.example.clothingstore.dto.DiscountCouponDTO;
import org.example.clothingstore.services.DiscountCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discount-coupon")
public class DiscountCouponController {

    private final DiscountCouponService discountCouponService;

    @Autowired
    public DiscountCouponController(DiscountCouponService discountCouponService) {
        this.discountCouponService = discountCouponService;
    }

    @PostMapping("/add")
    public void createDiscountCoupon(@RequestBody DiscountCouponDTO newDiscountCoupon) {
        discountCouponService.addDiscountCoupon(newDiscountCoupon);
    }
}
