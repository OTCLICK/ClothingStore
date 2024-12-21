package org.example.clothingstorecontracts.viewmodel;

import java.util.List;

public record ProductListViewModel(
        BaseViewModel base,
        List<ShowProductViewModel> products,
        int totalPages
) {}
