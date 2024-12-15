package org.example.clothingstorecontracts.input;

import java.util.Date;

public record OrderAddForm(
        String username,
        float discountPercentage,
        Date date,
        float orderAmount,
        String orderStatus,
        int quantityOfProducts
) {
}
