package org.example.clothingstore.controllers;

import jakarta.validation.Valid;
import org.example.clothingstore.dto.UserRegistrationDTO;
import org.example.clothingstore.entities.User;
import org.example.clothingstore.entities.Wallet;
import org.example.clothingstore.repositories.UserRepository;
import org.example.clothingstore.services.WalletService;
import org.example.clothingstore.services.impl.AuthService;
import org.example.clothingstorecontracts.viewmodel.UserProfileView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class AuthController {

    private AuthService authService;
    private WalletService walletService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public AuthController(AuthService authService, WalletService walletService) {
        this.authService = authService;
        this.walletService = walletService;
    }

    @ModelAttribute("userRegistrationDTO")
    public UserRegistrationDTO initForm() {
        return new UserRegistrationDTO();
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto",
                    bindingResult);
            return "redirect:/users/register";
        }
        authService.register(userRegistrationDTO);
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                String username, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY,
                username);

        System.out.println(userRepository.findOptionalByUsername(username).get());


        redirectAttributes.addFlashAttribute("badCredentialsMessage", true);
        System.out.println("ОШИБОЧКА");

        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        String username = principal.getName();
        User user = authService.getUser(username);
        Wallet wallet = walletService.getWallet(user);
        UserProfileView userProfileView =new UserProfileView(user.getId(), username, user.getEmail(), user.getFullName(), user.getAge(),
                wallet.getAmount());
        model.addAttribute("user", userProfileView);
        return "profile";
    }
}
