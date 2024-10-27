package org.example.clothingstore.repositories;

import org.example.clothingstore.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    void save(User user);
    void update(User user);

}
