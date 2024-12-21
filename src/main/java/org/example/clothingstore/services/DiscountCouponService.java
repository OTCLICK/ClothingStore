package org.example.clothingstore.services;

import org.example.clothingstore.dto.DiscountCouponDTO;
import org.example.clothingstore.dto.OrderDTO;
import org.example.clothingstore.entities.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DiscountCouponService {

    void addDiscountCoupon(DiscountCouponDTO discountCouponDto);
    DiscountCoupon findById(String id);
    List<DiscountCoupon> findListByDiscountPercentage(float discountPercentage);
    DiscountCoupon findByDiscountPercentage(float discountPercentage);
    Page<DiscountCouponDTO> getDiscountCoupons(int page, int size);
    String createDiscountCoupon(String categoryName, String brandName,
                                float discountPercentage, float minOrderAmount);
    void deleteDiscountCoupon(String id);
    List<DiscountCouponDTO> getAvailableCouponsForOrder(Order order);
    List<DiscountCoupon> getTopBrands(int limit);
    List<DiscountCoupon> getTopCategories(int limit);


}
