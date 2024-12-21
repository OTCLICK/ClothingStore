package org.example.clothingstorecontracts.input;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CouponAddForm(
        @NotBlank(message = "Название категории одежды обязательно")
        String categoryName,

        @NotBlank(message = "Название бренда обязательно")
        String brandName,

        @Min(value = 5, message = "Скидка должна быть не менее 5%")
        @Max(value = 95, message = "Скидка должна быть не более 95%")
        float discountPercentage,

        @Min(value = 100, message = "Минимальная сумма заказа должна быть не менее 100 рублей")
        @Max(value = 20000, message = "Минимальная сумма заказа должна быть не более 20000 рублей")
        float minOrderAmount
) {}
