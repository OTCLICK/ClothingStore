package org.example.clothingstorecontracts.viewmodel;

import java.util.List;

public record FullProductViewModel(
        ShowProductViewModel showProductViewModel,
        String categoryName,
        String season,
        String brandName,
        String color,
        String size,
        BaseViewModel base
) {}
