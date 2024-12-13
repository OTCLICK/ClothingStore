package org.example.clothingstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ProductEditDTO(
        String id,
        float price,
        String productName,
        String categoryName,
        String brandName,
        String color,
        String size,
        String season
) {
}