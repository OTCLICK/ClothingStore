package org.example.clothingstorecontracts.input;

import jakarta.validation.constraints.Min;

public record OrderSearchForm(
        @Min(value = 1, message = "Страница должна быть больше 0")
        Integer page,

        @Min(value = 5, message = "Размер страницы должен быть больше 4")
        Integer size
) {
}
