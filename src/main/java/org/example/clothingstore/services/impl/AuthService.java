package org.example.clothingstore.services.impl;

import org.example.clothingstore.dto.UserRegistrationDTO;
import org.example.clothingstore.entities.User;
import org.example.clothingstore.entities.UserRolesEnum;
import org.example.clothingstore.repositories.UserRepository;
import org.example.clothingstore.repositories.UserRolesRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    private UserRolesRepository rolesRepository;

    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, UserRolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(UserRegistrationDTO registrationDTO) {
        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<User> byEmail = this.userRepository.findOptionalByEmail(registrationDTO.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        var userRole = rolesRepository.findRoleByName(UserRolesEnum.CUSTOMER).orElseThrow();

        User user = new User(
                registrationDTO.getUsername(),
                passwordEncoder.encode(registrationDTO.getPassword()),
                registrationDTO.getEmail(),
                registrationDTO.getFullname(),
                registrationDTO.getAge()
        );

        user.setRoles(List.of(userRole));

        this.userRepository.save(user);
    }

    public User getUser(String username) {
        return userRepository.findOptionalByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }

    public boolean authenticate(String username, String rawPassword) {
        User user = userRepository.findOptionalByUsername(username)
                .orElse(null);

        if (user == null) {
            System.out.println("Пользователь не найден");
            return false;
        }

        boolean passwordMatch = passwordEncoder.matches(rawPassword, user.getPassword());

        if (passwordMatch) {
            System.out.println("Аутентификация успешна для пользователя: " + username);
        } else {
            System.out.println("Неверный пароль для пользователя: " + username);
        }

        return passwordMatch;
    }

}
