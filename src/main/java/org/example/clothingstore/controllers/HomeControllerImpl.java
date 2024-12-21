package org.example.clothingstore.controllers;
import org.example.clothingstore.entities.DiscountCoupon;
import org.example.clothingstore.services.DiscountCouponService;
import org.example.clothingstorecontracts.controllers.HomeController;
import org.example.clothingstorecontracts.viewmodel.BaseViewModel;
import org.example.clothingstorecontracts.viewmodel.HomeViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeControllerImpl implements HomeController{

    private final DiscountCouponService discountCouponService;

    public HomeControllerImpl(DiscountCouponService discountCouponService) {
        this.discountCouponService = discountCouponService;
    }

    @Override
    @GetMapping("/")
    public String index(Model model) {
        var viewModel = new HomeViewModel(createBaseViewModel("Главная страница", "Текущий пользователь"));

        List<DiscountCoupon> topBrands = discountCouponService.getTopBrands(5);
        List<DiscountCoupon> topCategories = discountCouponService.getTopCategories(5);

        System.out.println("Top Brands: " + topBrands);
        System.out.println("Top Categories: " + topCategories);

        model.addAttribute("topBrands", topBrands);
        model.addAttribute("topCategories", topCategories);
        model.addAttribute("model", viewModel);
        return "index";
    }

    public BaseViewModel createBaseViewModel(String title, String user) {return new BaseViewModel(title, user);}
}
