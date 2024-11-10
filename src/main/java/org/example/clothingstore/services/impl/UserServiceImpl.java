package org.example.clothingstore.services.impl;

import jakarta.validation.ConstraintViolation;
import org.example.clothingstore.dto.UserDTO;
import org.example.clothingstore.entities.User;
import org.example.clothingstore.repositories.UserRepository;
import org.example.clothingstore.services.UserService;
import org.example.clothingstore.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;
    public final ValidationUtil validationUtil;
    public final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addUser(String username, String login, String password, String email, String phone) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setLogin(login);
        userDTO.setPassword(password);
        userDTO.setEmail(email);
        userDTO.setPhone(phone);
        if (!this.validationUtil.isValid(userDTO)) {
            this.validationUtil.violations(userDTO).stream().map(ConstraintViolation::getMessage).
                    forEach(System.out::println);
        } else {
            try {
                this.userRepository.save(modelMapper.map(userDTO, User.class));
            } catch (Exception e) {
                System.out.println("Error while saving user: " + e.getMessage());
            }
        }
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
