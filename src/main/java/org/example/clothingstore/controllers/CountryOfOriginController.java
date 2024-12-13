//package org.example.clothingstore.controllers;
//
//import org.example.clothingstore.dto.CountryOfOriginDTO;
//import org.example.clothingstore.services.CountryOfOriginService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/country-of-origin")
//public class CountryOfOriginController {
//
//    private final CountryOfOriginService countryOfOriginService;
//
//    @Autowired
//    public CountryOfOriginController(CountryOfOriginService countryOfOriginService) {
//        this.countryOfOriginService = countryOfOriginService;
//    }
//
//    @PostMapping("/add")
//    public void createCountryOfOrigin(@RequestBody CountryOfOriginDTO newCountryOfOrigin) {
//        countryOfOriginService.addCountryOfOrigin(newCountryOfOrigin);
//    }
//}
