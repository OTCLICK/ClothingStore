package org.example.clothingstore.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public abstract class BaseCRepository<Entity> {

    private final Class<Entity> entityClass;

    @PersistenceContext
    protected EntityManager em;

    public BaseCRepository(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional
    public void save(Entity entity) {
        em.persist(entity);
    }

}
