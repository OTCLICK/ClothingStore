package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.clothingstore.entities.User;
import org.example.clothingstore.repositories.BaseCURepository;
import org.example.clothingstore.repositories.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends BaseCURepository<User> implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    public UserRepositoryImpl() {
        super(User.class);
    }

}
