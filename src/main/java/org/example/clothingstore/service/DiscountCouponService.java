package org.example.clothingstore.service;

import org.example.clothingstore.dto.DiscountCouponDTO;
import org.example.clothingstore.entities.DiscountCoupon;

import java.util.List;

public interface DiscountCouponService {

    void addDiscountCoupon(DiscountCouponDTO discountCouponDto);
    DiscountCoupon findById(int id);
    List<DiscountCoupon> findByDiscountPercentage(float discountPercentage);

}
