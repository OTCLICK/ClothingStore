package org.example.clothingstorecontracts.viewmodel;

import java.util.List;

public record CouponListViewModel(
        BaseViewModel base,
        List<CouponViewModel> coupons,
        int totalPages
) {
}
