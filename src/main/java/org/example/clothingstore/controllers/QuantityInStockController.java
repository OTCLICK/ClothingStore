package org.example.clothingstore.controllers;

import org.example.clothingstore.services.QuantityInStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quantity-in-stock")
public class QuantityInStockController {

    private final QuantityInStockService quantityInStockService;

    @Autowired
    public QuantityInStockController(QuantityInStockService quantityInStockService) {
        this.quantityInStockService = quantityInStockService;
    }
}
