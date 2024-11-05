package org.example.clothingstore.service;

import org.example.clothingstore.entities.DiscountCoupon;

import java.util.List;

public interface DiscountCouponService {

    void addDiscountCoupon(DiscountCoupon discountCoupon);
    DiscountCoupon findById(Class<DiscountCoupon> discountCouponClass, int id);
    List<DiscountCoupon> findAll();

}
