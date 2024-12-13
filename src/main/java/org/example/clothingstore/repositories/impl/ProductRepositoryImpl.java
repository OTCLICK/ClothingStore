package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.clothingstore.entities.Brand;
import org.example.clothingstore.entities.Product;
import org.example.clothingstore.repositories.BaseCRURepository;
import org.example.clothingstore.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl extends BaseCRURepository<Product> implements ProductRepository {

    @PersistenceContext
    private EntityManager em;

    public ProductRepositoryImpl() {
        super(Product.class);
    }

    @Override
    public void saveAll(List<Product> products) {
        for (Product product : products) {
            em.persist(product);
        }
    }

    @Override
    public Product findById(String id) {
        return em.createQuery("select p from Product p where p.id = :id", Product.class).setParameter("id", id).
                getSingleResult();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return (Page<Product>) em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    @Override
    public Product findByProductName(String productName) {
        return em.createQuery("SELECT p FROM Product p WHERE p.productName = :productName", Product.class).
                setParameter("productName", productName).getSingleResult();
    }

    @Override
    public Page<Product> findByProductNameContainingIgnoreCase(String productName, Pageable pageable) {
        String query = "SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :productName, '%'))";
        TypedQuery<Product> typedQuery = em.createQuery(query, Product.class);
        typedQuery.setParameter("productName", productName);

        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        long totalResults = em.createQuery("SELECT COUNT(p) FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :productName, '%'))", Long.class)
                .setParameter("productName", productName)
                .getSingleResult();

        return new PageImpl<>(typedQuery.getResultList(), pageable, totalResults);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        em.remove(em.find(Product.class, id));
    }
}
