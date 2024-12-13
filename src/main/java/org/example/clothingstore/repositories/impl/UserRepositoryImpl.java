package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.clothingstore.entities.ClothingCategory;
import org.example.clothingstore.entities.DiscountCoupon;
import org.example.clothingstore.entities.User;
import org.example.clothingstore.repositories.BaseCURepository;
import org.example.clothingstore.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl extends BaseCURepository<User> implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        return em.createQuery("SELECT u FROM User u WHERE u.username = :username",
                User.class).setParameter("username", username).getSingleResult();
    }

    @Override
    public void saveAll(List<User> users) {
        for (User user : users) {
            em.persist(user);
        }
    }
}
