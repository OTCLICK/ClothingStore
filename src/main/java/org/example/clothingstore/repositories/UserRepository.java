package org.example.clothingstore.repositories;

import org.example.clothingstore.entities.DiscountCoupon;
import org.example.clothingstore.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {

    void save(User user);
    void update(User user);
    User findByUsername(String username);
    void saveAll(List<User> users);
    Optional<User> findOptionalByUsername(String username);
    Optional<User> findOptionalByEmail(String email);
//    Optional<User> findOptionalByPhone(String phone);
    long count();
    List<User> findAll();



}
