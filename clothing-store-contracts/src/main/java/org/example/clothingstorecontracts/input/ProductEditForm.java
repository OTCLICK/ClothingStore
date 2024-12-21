package org.example.clothingstorecontracts.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ProductEditForm(

        String id,

        @Min(value = 10, message = "Цена должна быть не меньше 10 рублей")
        float price,

        @NotBlank(message = "Название обязательно")
        String productName,

        @NotBlank(message = "Категория товара обязательна")
        String categoryName,

        String brandName,

        @NotBlank(message = "Цвет обязателен")
        String color,

        @NotBlank(message = "Размер обязателен")
        String size,

        String season
        ) {
}
