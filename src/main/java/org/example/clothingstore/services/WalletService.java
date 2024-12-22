package org.example.clothingstore.services;

import org.example.clothingstore.dto.WalletDTO;
import org.example.clothingstore.entities.User;
import org.example.clothingstore.entities.Wallet;

public interface WalletService {

    void createWallet(WalletDTO walletDto);
    void updateWallet(String id, float amount);
    Wallet getWallet(User user);

}
