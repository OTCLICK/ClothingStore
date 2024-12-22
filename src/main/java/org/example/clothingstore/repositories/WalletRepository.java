package org.example.clothingstore.repositories;

import org.example.clothingstore.entities.User;
import org.example.clothingstore.entities.Wallet;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository {

    void save(Wallet wallet);

    void update(Wallet wallet);

    Wallet findById(String  id);

    Wallet findByUser(User user);

    Wallet findByUserId(String userId);
}
