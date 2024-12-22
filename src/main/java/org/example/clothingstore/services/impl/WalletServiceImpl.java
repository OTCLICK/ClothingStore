package org.example.clothingstore.services.impl;

import jakarta.validation.ConstraintViolation;
import org.example.clothingstore.dto.WalletDTO;
import org.example.clothingstore.entities.User;
import org.example.clothingstore.entities.Wallet;
import org.example.clothingstore.repositories.WalletRepository;
import org.example.clothingstore.services.UserService;
import org.example.clothingstore.services.WalletService;
import org.example.clothingstore.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    private WalletRepository walletRepository;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;
    private UserService userService;

    public WalletServiceImpl(WalletRepository walletRepository, ModelMapper modelMapper, ValidationUtil validationUtil,
                             UserService userService) {
        this.walletRepository = walletRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
    }

    @Override
    public void createWallet(WalletDTO walletDto) {
        if (!this.validationUtil.isValid(walletDto)) {
            this.validationUtil.violations(walletDto).stream().map(ConstraintViolation::getMessage).
                    forEach(System.out::println);
            throw new IllegalArgumentException("Illegal arguments while saving product");
        }
        Wallet wallet = this.modelMapper.map(walletDto, Wallet.class);
        wallet.setUser(userService.findByUsername(walletDto.getUser().getUsername()));
        walletRepository.save(wallet);
    }


    @Override
    public void updateWallet(String id, float amount) {
        try {
            User user = userService.findById(id);
            Wallet wallet = walletRepository.findByUser(user);
            float oldAmount = wallet.getAmount();
            wallet.setAmount(oldAmount + amount);
            walletRepository.save(wallet);
        } catch (RuntimeException e) {
            System.out.println("Кошелёк не найден");
        }
    }

    @Override
    public Wallet getWallet(User user) {
        return walletRepository.findByUser(user);
    }
}
