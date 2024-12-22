package org.example.clothingstore.services.impl;

import jakarta.validation.ConstraintViolation;
import org.example.clothingstore.dto.UserDTO;
import org.example.clothingstore.entities.User;
import org.example.clothingstore.entities.Wallet;
import org.example.clothingstore.repositories.UserRepository;
import org.example.clothingstore.repositories.WalletRepository;
import org.example.clothingstore.services.UserService;
import org.example.clothingstore.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final WalletRepository walletRepository;


    public UserServiceImpl(UserRepository userRepository, ValidationUtil validationUtil, ModelMapper modelMapper,
                           WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.walletRepository = walletRepository;
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
                User user = modelMapper.map(userDTO, User.class);
                this.userRepository.save(user);
//                Wallet wallet = new Wallet(userRepository.findByUsername(username), 0);
//                this.walletRepository.save(wallet);
            } catch (Exception e) {
                System.out.println("Error while saving user: " + e.getMessage());
            }
        }
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User findById(String id) {
        return this.userRepository.findById(id);
    }
}
