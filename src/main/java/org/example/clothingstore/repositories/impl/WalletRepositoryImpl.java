package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.clothingstore.entities.User;
import org.example.clothingstore.entities.Wallet;
import org.example.clothingstore.repositories.BaseCRURepository;
import org.example.clothingstore.repositories.WalletRepository;
import org.springframework.stereotype.Repository;

@Repository
public class WalletRepositoryImpl extends BaseCRURepository<Wallet> implements WalletRepository {

    @PersistenceContext
    private EntityManager em;

    public WalletRepositoryImpl() {
        super(Wallet.class);
    }

    @Override
    public Wallet findByUser(User user) {
        return em.createQuery("select w from Wallet w where user = :user", Wallet.class)
                .setParameter("user", user).getSingleResult();
    }

    @Override
    public Wallet findById(String id) {
        return em.find(Wallet.class, id);
    }

    @Override
    public Wallet findByUserId(String userId) {
        return em.createQuery("select w from Wallet w join User u where w.user = u.id", Wallet.class).getSingleResult();
    }
}
