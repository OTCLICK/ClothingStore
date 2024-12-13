package org.example.clothingstore.controllers;

import org.example.clothingstore.services.ProductService;
import org.example.clothingstorecontracts.controllers.ProductController;
import org.example.clothingstorecontracts.input.ProductSearchForm;
import org.example.clothingstorecontracts.viewmodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Autowired
    public ProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    public BaseViewModel createBaseViewModel(String title, String user) {
        return new BaseViewModel(title, user);
    }

    @Override
    @GetMapping
    public String listProducts(@ModelAttribute("form") ProductSearchForm form, Model model) {
        var searchTerm = form.searchWord() != null ? form.searchWord() : "";
        int page = form.page() != null ? form.page() : 1;
        int size = form.size() != null ? form.size() : 10;
        form = new ProductSearchForm(searchTerm, page, size);

        var productsPage = productService.getProducts(searchTerm, page, size);

        var productViewModels = productsPage.stream()
                .map(p -> new ShowProductViewModel(p.getId(), p.getPrice(), p.getProductName(),
                        "istockphoto-916092484-612x612.jpg"))
                .toList();
        System.out.println();

        var viewModel = new ProductListViewModel(
                createBaseViewModel("Список товаров", "Текущий пользователь"),
                productViewModels,
                productsPage.getTotalPages()
        );

        model.addAttribute("model", viewModel);
        model.addAttribute("form", form);
        return "product-list";
    }

    @Override
    @GetMapping("/{productId}")
    public String showProduct(@PathVariable String productId, Model model) {
        System.out.println("Запрос на товар с ID: " + productId);
        var product = productService.getProduct(productId);
        if (product == null) {
            System.out.println("Товар не найден, перенаправление на /products");
            return "redirect:/products";
        }
        var viewModel = new FullProductViewModel(
                new ShowProductViewModel(
                        product.getId(), product.getPrice(), product.getProductName(),
                        "istockphoto-916092484-612x612.jpg"),
                product.getClothingCategory().getCategoryName(),
                product.getClothingCategory().getSeason().toString(),
                product.getBrand().getBrandName(),
                product.getColor(),
                product.getSize(),
                createBaseViewModel("Детали товара", "Текущий пользователь")
        );
        model.addAttribute("model", viewModel);
        return "show-product";
    }

}
