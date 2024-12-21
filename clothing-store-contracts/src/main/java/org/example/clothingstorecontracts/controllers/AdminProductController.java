package org.example.clothingstorecontracts.controllers;

import jakarta.validation.Valid;
import org.example.clothingstorecontracts.input.ProductAddForm;
import org.example.clothingstorecontracts.input.ProductEditForm;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("admin/products")
public interface AdminProductController {

    @GetMapping("/create")
    String createProductForm(Model model);

    @PostMapping("/create")
    String createProduct(
            @Valid @ModelAttribute("form") ProductAddForm form,
            BindingResult bindingResult,
            Model model
            );

    @GetMapping("/{productId}/edit")
    String editProductForm(
            @PathVariable("productId") String id,
            Model model
    );

    @PostMapping("/{productId}/edit")
    String editProduct(
            @PathVariable String productId,
            @Valid @ModelAttribute("form") ProductEditForm form,
            BindingResult bindingResult,
            Model model
    );

    @GetMapping("/{productId}/delete")
    String deleteProduct(
            @PathVariable("productId") String id,
            Model model
    );

}
