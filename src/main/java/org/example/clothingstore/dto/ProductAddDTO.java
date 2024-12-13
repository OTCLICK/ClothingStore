package org.example.clothingstore.dto;

public record ProductAddDTO(
        float price,
        String productName,
        String categoryName,
        String brandName,
        String color,
        String size,
        String season
) {
}
