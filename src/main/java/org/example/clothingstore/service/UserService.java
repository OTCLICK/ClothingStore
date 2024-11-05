package org.example.clothingstore.service;

import org.example.clothingstore.dto.UserDTO;
import org.example.clothingstore.entities.User;

public interface UserService {

    void addUser(String username, String login, String password, String email, String phone);
    User findByUsername(String username);
}
