package org.example.clothingstorecontracts.viewmodel;

public record ShowProductViewModel(
        String id,
        Float price,
        String name,
        String imageName
) {
}
