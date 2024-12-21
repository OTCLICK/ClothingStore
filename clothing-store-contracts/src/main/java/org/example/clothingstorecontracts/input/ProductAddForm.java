package org.example.clothingstorecontracts.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ProductAddForm(
        @Min(value = 10, message = "Цена должна быть не меньше 10 рублей")
        float price,

        @NotBlank(message = "Название обязательно")
        String productName,

//        @Min(value=1, message = "Количество товара должно быть не менее 1")
//        int quantityInStock,

        @NotBlank(message = "Категория товара обязательна")
        String categoryName,

        String brandName,

        @NotBlank(message = "Цвет обязателен")
        String color,

        @NotBlank(message = "Размер обязателен")
        String size
        ){}
