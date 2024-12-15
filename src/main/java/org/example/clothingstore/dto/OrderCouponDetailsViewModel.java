package org.example.clothingstore.dto;

import org.example.clothingstore.entities.DiscountCoupon;
import org.example.clothingstorecontracts.viewmodel.OrderViewModel;

import java.util.List;

public record OrderCouponDetailsViewModel(
        OrderViewModel orderViewModel,
        String user,
        Float discountCoupon,
        List<DiscountCouponDTO> availableCoupons
) {
}
