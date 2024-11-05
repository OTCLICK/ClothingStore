package org.example.clothingstore.service;

import org.example.clothingstore.entities.QuantityInStock;

import java.util.List;

public interface QuantityInStockService {

    void addQuantityInStock(QuantityInStock quantityInStock);
    List<QuantityInStock> findByQuantityOfProducts(int quantityOfProducts);
}
