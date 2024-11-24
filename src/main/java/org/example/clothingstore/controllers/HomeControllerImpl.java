package org.example.clothingstore.controllers;
import org.example.clothingstorecontracts.controllers.HomeController;
import org.example.clothingstorecontracts.viewmodel.BaseViewModel;
import org.example.clothingstorecontracts.viewmodel.HomeViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeControllerImpl implements HomeController{

    @Override
    @GetMapping("/")
    public String index(Model model) {
        var viewModel = new HomeViewModel(createBaseViewModel("Главная страница", "Текущий пользователь"));
        model.addAttribute("model", viewModel);
        return "index";
    }

    public BaseViewModel createBaseViewModel(String title, String user) {return new BaseViewModel(title, user);}
}
