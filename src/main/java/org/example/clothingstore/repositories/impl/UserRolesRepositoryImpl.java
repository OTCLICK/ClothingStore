package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.clothingstore.entities.Product;
import org.example.clothingstore.entities.Role;
import org.example.clothingstore.entities.UserRolesEnum;
import org.example.clothingstore.repositories.BaseCRepository;
import org.example.clothingstore.repositories.UserRolesRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRolesRepositoryImpl extends BaseCRepository<Role> implements UserRolesRepository  {

    @PersistenceContext
    private EntityManager em;

    public UserRolesRepositoryImpl() {
        super(Role.class);
    }

    @Override
    public Optional<Role> findRoleByName(UserRolesEnum roleName) {
        return Optional.of(em.createQuery("SELECT r FROM Role r WHERE r.name = :roleName", Role.class).
                setParameter("roleName", roleName).getSingleResult());
    }

    @Override
    public long count() {
        return em.createQuery("SELECT COUNT(r) FROM Role r", Long.class).getSingleResult();
    }
}
