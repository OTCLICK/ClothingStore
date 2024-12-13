package org.example.clothingstore.repositories;

import org.example.clothingstore.entities.DiscountCoupon;
import org.example.clothingstore.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    void save(User user);
    void update(User user);
    User findByUsername(String username);
    void saveAll(List<User> users);


}
