package org.example.clothingstorecontracts.controllers;

import org.example.clothingstorecontracts.input.ProductSearchForm;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/products")
public interface ProductController {

    @GetMapping
    String listProducts(
            @ModelAttribute("form") ProductSearchForm form,
            Model model
            );

    @GetMapping("/{productId}")
    String showProduct(
            @PathVariable("productId") String productId,
            Model model
    );

//    @GetMapping("/create")
//    String createProductForm(Model model);
//
//    @PostMapping("/create")
//    String createProduct(
//            @Valid @ModelAttribute("form") ProductAddForm form,
//            BindingResult bindingResult,
//            Model model
//            );
//
//    @GetMapping("/{productId}/edit")
//    String editProductForm(
//            @PathVariable("productId") String id,
//            Model model
//    );
//
//    @PostMapping("/{productId}/edit")
//    String editProduct(
//            @PathVariable String productId,
//            @Valid @ModelAttribute("form") ProductEditForm form,
//            BindingResult bindingResult,
//            Model model
//    );
//
//    @GetMapping("/{productId}/delete")
//    String deleteProduct(
//            @PathVariable("productId") String id,
//            Model model
//    );

}
