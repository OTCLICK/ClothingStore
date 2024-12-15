package org.example.clothingstorecontracts.viewmodel;

import java.util.List;

public record OrderListViewModel(
        BaseViewModel base,
        List<OrderViewModel> orders,
        int totalPages
) {}
