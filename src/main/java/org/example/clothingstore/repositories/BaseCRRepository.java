package org.example.clothingstore.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public abstract class BaseCRRepository<Entity> {

    private final Class<Entity> entityClass;

    @PersistenceContext
    protected EntityManager em;

    public BaseCRRepository(Class<Entity> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional
    public void save(Entity entity) {
        em.persist(entity);
    }

    @Transactional
    public Entity findById(Class<Entity> entityClass, int id) {
        return em.find(entityClass, id);
    }

}
