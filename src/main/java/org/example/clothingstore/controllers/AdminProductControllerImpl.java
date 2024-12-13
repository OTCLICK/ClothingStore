package org.example.clothingstore.controllers;

import org.example.clothingstore.dto.ProductEditDTO;
import org.example.clothingstore.entities.SeasonEnum;
import org.example.clothingstore.services.ProductService;
import org.example.clothingstorecontracts.controllers.AdminProductController;
import org.example.clothingstorecontracts.input.ProductAddForm;
import org.example.clothingstorecontracts.input.ProductEditForm;
import org.example.clothingstorecontracts.viewmodel.BaseViewModel;
import org.example.clothingstorecontracts.viewmodel.ProductCreateViewModel;
import org.example.clothingstorecontracts.viewmodel.ProductEditViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class AdminProductControllerImpl implements AdminProductController {

    private final ProductService productService;

    @Autowired
    public AdminProductControllerImpl(ProductService productService) {
        this.productService = productService;
    }

    public BaseViewModel createBaseViewModel(String title, String user) {
        return new BaseViewModel(title, user);
    }

    @Override
    @GetMapping("/create-product")
    public String createProductForm(Model model) {
        var viewModel = new ProductCreateViewModel(
                createBaseViewModel("Добавление товара", "Текущий пользователь")
        );
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new ProductAddForm(10, "",
                "", "", "",
                ""));
        return "product-create";
    }

    @Override
    @PostMapping("/create-product")
    public String createProduct(ProductAddForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            var viewModel = new ProductCreateViewModel(
                    createBaseViewModel("Добавление товара", "Текущий пользователь")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "product-create";
        }
        var id = productService.createProduct(form.categoryName(), form.brandName(),
                form.productName(), form.color(), form.size(), form.price());
        return "redirect:/products/" + id;
    }

    @Override
    @GetMapping("/{productId}/edit")
    public String editProductForm(String productId, Model model) {
        var product = productService.getProductEdit(productId);
        var viewModel = new ProductEditViewModel(
                createBaseViewModel("Редактирование товара", "Текущий пользователь")
        );
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new ProductEditForm(productId, product.price(), product.productName(),
                product.categoryName(), product.brandName(), product.color(), product.size(), product.season()));
        return "product-edit";
    }

    @Override
    @PostMapping("/{productId}/edit")
    public String editProduct(@PathVariable String productId, ProductEditForm form, BindingResult bindingResult,
                              Model model) {

        if (bindingResult.hasErrors()) {
            var viewModel = new ProductEditViewModel(
                    createBaseViewModel("Редактирование товара", "Текущий пользователь")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "product-edit";
        }
        productService.updateProduct(new ProductEditDTO(form.id(), form.price(), form.productName(), form.categoryName(),
                form.brandName(), form.color(), form.size(), form.season()));
        return "redirect:/products/" + form.id();
    }

    @Override
    @GetMapping("/{productId}/delete")
    public String deleteProduct(String productId, Model model) {
        productService.deleteProduct(productId);
        return "redirect:/products";
    }
}
