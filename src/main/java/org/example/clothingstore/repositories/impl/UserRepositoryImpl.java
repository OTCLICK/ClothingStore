package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.clothingstore.entities.ClothingCategory;
import org.example.clothingstore.entities.DiscountCoupon;
import org.example.clothingstore.entities.Role;
import org.example.clothingstore.entities.User;
import org.example.clothingstore.repositories.BaseCURepository;
import org.example.clothingstore.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<User> findOptionalByUsername(String username) {
        return Optional.of(em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class).
                setParameter("username", username).getSingleResult());
    }

    @Override
    public Optional<User> findOptionalByEmail(String email) {
        List<User> users = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();

        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
    }


//    @Override
//    public Optional<User> findOptionalByPhone(String phone) {
//        List<User> users = em.createQuery("SELECT u FROM User u WHERE u.phone = :phone", User.class).
//                setParameter("phone", phone).getResultList();
//
//        return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
//    }

    @Override
    public long count() {
        return em.createQuery("SELECT COUNT(u) FROM User u", Long.class).getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
}
