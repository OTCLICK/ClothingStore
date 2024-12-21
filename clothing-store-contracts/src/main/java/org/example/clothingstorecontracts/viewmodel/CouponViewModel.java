package org.example.clothingstorecontracts.viewmodel;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CouponViewModel(
        BaseViewModel base,
        String categoryName,
        String brandName,
        float discountPercentage,
        float minOrderAmount
) {}
