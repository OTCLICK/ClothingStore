package org.example.clothingstore.service;

import org.example.clothingstore.dto.QuantityInStockDTO;
import org.example.clothingstore.entities.QuantityInStock;

import java.util.List;

public interface QuantityInStockService {

    void addQuantityInStock(QuantityInStockDTO quantityInStockDto);
    List<QuantityInStock> findByQuantityOfProducts(int quantityOfProducts);
}
