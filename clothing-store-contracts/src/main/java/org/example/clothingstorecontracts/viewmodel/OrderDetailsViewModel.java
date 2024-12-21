package org.example.clothingstorecontracts.viewmodel;

public record OrderDetailsViewModel(
        OrderViewModel orderViewModel,
        String user,
        Float discountCoupon
) {
}
