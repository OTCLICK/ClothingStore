package org.example.clothingstore.controllers;

import org.example.clothingstore.dto.ProductDTO;
import org.example.clothingstore.dto.UserDTO;
import org.example.clothingstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public void createUser(@RequestBody String username, String login, String password, String email, String phone) {
        userService.addUser(username, login, password, email, phone);
    }
}
