package org.example.clothingstorecontracts.viewmodel;

import java.util.Date;

public record OrderViewModel(
        String id,
        BaseViewModel base,
        Date date,
        float amount,
        String status,
        int quantityOfProducts
) {
}
