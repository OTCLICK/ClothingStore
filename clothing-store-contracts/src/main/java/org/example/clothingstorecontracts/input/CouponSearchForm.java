package org.example.clothingstorecontracts.input;

import jakarta.validation.constraints.Min;

public record CouponSearchForm(
        @Min(value = 1, message = "Страница должна быть больше 0")
        Integer page,

        @Min(value = 3, message = "Размер страницы должен быть больше 2")
        Integer size
) {
}
