//package org.example.clothingstore.controllers;
//
//import org.example.clothingstore.dto.ProductDTO;
//import org.example.clothingstore.dto.QuantityInStockDTO;
//import org.example.clothingstore.services.QuantityInStockService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/quantity-in-stock")
//public class QuantityInStockController {
//
//    private final QuantityInStockService quantityInStockService;
//
//    @Autowired
//    public QuantityInStockController(QuantityInStockService quantityInStockService) {
//        this.quantityInStockService = quantityInStockService;
//    }
//
//    @PostMapping("/add")
//    public void createQuantityInStock(@RequestBody QuantityInStockDTO newQuantityInStock) {
//        quantityInStockService.addQuantityInStock(newQuantityInStock);
//    }
//}
