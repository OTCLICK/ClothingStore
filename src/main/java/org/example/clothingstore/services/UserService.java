package org.example.clothingstore.services;

import org.example.clothingstore.entities.User;

public interface UserService {

    void addUser(String username, String login, String password, String email, String phone);
    User findByUsername(String username);
}
