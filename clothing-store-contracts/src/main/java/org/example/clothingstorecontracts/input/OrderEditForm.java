package org.example.clothingstorecontracts.input;

import java.util.Date;

public record OrderEditForm(
        String id,
        String username,
        float discountPercentage,
        Date date,
        float orderAmount,
        String orderStatus,
        int quantityOfProducts
) {
}
