package org.example.clothingstore.controllers;

import jakarta.validation.Valid;
import org.example.clothingstore.dto.WalletDTO;
import org.example.clothingstore.services.WalletService;
import org.example.clothingstorecontracts.controllers.WalletController;
import org.example.clothingstorecontracts.input.BalanceUpForm;
import org.example.clothingstorecontracts.viewmodel.BalanceUpViewModel;
import org.example.clothingstorecontracts.viewmodel.BaseViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wallet")
public class WalletControllerImpl implements WalletController {

    private final WalletService walletService;

    public WalletControllerImpl(WalletService walletService) {
        this.walletService = walletService;
    }

    public BaseViewModel createBaseViewModel(String title, String user) {
        return new BaseViewModel(title, user);
    }

    @Override
    @GetMapping("/{id}")
    public String topUpBalanceForm(@PathVariable("id") String id, Model model) {
        var viewModel = new BalanceUpViewModel(createBaseViewModel("Пополнение баланса",
                "Текущий пользователь"));
        model.addAttribute("model", viewModel);
        model.addAttribute("form", new BalanceUpForm(id ,50.f));
        return "top-up-balance";
    }

    @Override
    @PostMapping("/{id}")
    public String topUpBalance(@PathVariable("id") String id, @Valid @ModelAttribute("form") BalanceUpForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            var viewModel = new BalanceUpViewModel(createBaseViewModel("Пополнение баланса",
                    "Текущий пользователь"));
            model.addAttribute("model", viewModel);
            model.addAttribute("form", form);
            return "top-up-balance";
        }
        walletService.updateWallet(id, form.amount());
        return "redirect:/users/profile";
    }
}
