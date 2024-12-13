package org.example.clothingstore.controllers;

import jakarta.validation.Valid;
import org.example.clothingstore.entities.SeasonEnum;
import org.example.clothingstore.services.DiscountCouponService;
import org.example.clothingstorecontracts.controllers.AdminCouponController;
import org.example.clothingstorecontracts.input.CouponAddForm;
import org.example.clothingstorecontracts.input.ProductAddForm;
import org.example.clothingstorecontracts.viewmodel.BaseViewModel;
import org.example.clothingstorecontracts.viewmodel.CouponCreateViewModel;
import org.example.clothingstorecontracts.viewmodel.ProductCreateViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/coupons")
public class AdminCouponControllerImpl implements AdminCouponController {

    private final DiscountCouponService discountCouponService;

    @Autowired
    public AdminCouponControllerImpl(DiscountCouponService discountCouponService) {
        this.discountCouponService = discountCouponService;
    }

    public BaseViewModel createBaseViewModel(String title, String user) {
        return new BaseViewModel(title, user);
    }

    @Override
    @GetMapping("/create-coupon")
    public String createCouponForm(Model model) {
        var viewModel = new CouponCreateViewModel(
                createBaseViewModel("Добавление купона", "Текущий пользователь")
        );
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new CouponAddForm("", "",
                5, 100));
        return "create-coupon";
    }

    @Override
    @PostMapping("/create-coupon")
    public String createCoupon(@Valid @ModelAttribute("form") CouponAddForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            var viewModel = new CouponCreateViewModel(
                    createBaseViewModel("Добавление товара", "Текущий пользователь")
            );
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "create-coupon";
        }
        try {
            var id = discountCouponService.createDiscountCoupon(form.categoryName(),
                    form.brandName(), form.discountPercentage(), form.minOrderAmount());
            return "redirect:/coupons";
        }
        catch (Exception e) {
            return "Нельзя создать такой купон";
        }
    }

    @Override
    @GetMapping("/{couponId}/delete")
    public String deleteCoupon(@PathVariable String couponId, Model model) {
        discountCouponService.deleteDiscountCoupon(couponId);
        return "redirect:/coupons";
    }
}
