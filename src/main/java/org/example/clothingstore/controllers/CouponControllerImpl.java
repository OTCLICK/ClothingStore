package org.example.clothingstore.controllers;

import org.example.clothingstore.services.DiscountCouponService;
import org.example.clothingstorecontracts.controllers.CouponController;
import org.example.clothingstorecontracts.input.CouponSearchForm;
import org.example.clothingstorecontracts.viewmodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coupons")
public class CouponControllerImpl implements CouponController {

    private final DiscountCouponService discountCouponService;

    @Autowired
    public CouponControllerImpl(DiscountCouponService discountCouponService) {
        this.discountCouponService = discountCouponService;
    }

    public BaseViewModel createBaseViewModel(String title, String user) {
        return new BaseViewModel(title, user);
    }

    @Override
    public String listCoupons(CouponSearchForm form, Model model) {
        int page = form.page() != null ? form.page() : 1;
        int size = form.size() != null ? form.size() : 3;
        form = new CouponSearchForm(page, size);

        var couponsPage = discountCouponService.getDiscountCoupons(page, size);
        var couponViewModels = couponsPage.stream()
                .map(c -> new CouponViewModel(createBaseViewModel("Список купонов", "Текущий пользователь"),
                        c.getClothingCategory().getCategoryName(), c.getBrand().getBrandName(),
                        c.getDiscountPercentage(), c.getMinOrderAmount()))
                .toList();
        System.out.println();

        var viewModel = new CouponListViewModel(
                createBaseViewModel("Список купонов", "Текущий пользователь"),
                couponViewModels,
                couponsPage.getTotalPages()
        );

        model.addAttribute("model", viewModel);
        model.addAttribute("form", form);
        return "coupon-list";
    }

    @Override
    public String showCoupon(String couponId, Model model) {
        return "";
    }
}
