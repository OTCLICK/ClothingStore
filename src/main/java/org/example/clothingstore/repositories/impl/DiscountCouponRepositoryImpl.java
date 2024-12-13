package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.clothingstore.entities.DiscountCoupon;
import org.example.clothingstore.entities.Product;
import org.example.clothingstore.repositories.BaseCRRepository;
import org.example.clothingstore.repositories.BaseCRURepository;
import org.example.clothingstore.repositories.DiscountCouponRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DiscountCouponRepositoryImpl extends BaseCRURepository<DiscountCoupon> implements DiscountCouponRepository {

    @PersistenceContext
    EntityManager em;

    public DiscountCouponRepositoryImpl() {
        super(DiscountCoupon.class);
    }

    @Override
    public DiscountCoupon findById(String id) {
        return em.createQuery("SELECT dc FROM DiscountCoupon dc WHERE dc.id = :id", DiscountCoupon.class).
                setParameter("id", id).getSingleResult();
    }

    @Override
    public List<DiscountCoupon> findListByDiscountPercentage(float discountPercentage) {
        return em.createQuery("SELECT dc FROM DiscountCoupon dc WHERE " +
                        "dc.discountPercentage = :discountPercentage", DiscountCoupon.class).
                setParameter("discountPercentage", discountPercentage).getResultList();
    }

    @Override
    public Page<DiscountCoupon> findAll(Pageable pageable) {
        long total = em.createQuery("SELECT COUNT(dc) FROM DiscountCoupon dc", Long.class).getSingleResult();
        List<DiscountCoupon> results = em.createQuery("SELECT dc FROM DiscountCoupon dc", DiscountCoupon.class)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(results, pageable, total);    }

    @Override
    public void saveAll(List<DiscountCoupon> discountCoupons) {
        for (DiscountCoupon discountCoupon : discountCoupons) {
            em.persist(discountCoupon);
        }
    }

    @Override
    public void deleteById(String id) {
        em.remove(em.find(DiscountCoupon.class, id));
    }

    @Override
    public List<DiscountCoupon> getAllDiscountCoupons() {
        return em.createQuery("SELECT dc FROM DiscountCoupon dc", DiscountCoupon.class).getResultList();
    }

    @Override
    public DiscountCoupon findByDiscountPercentage(float discountPercentage) {
        return em.createQuery("SELECT dc FROM DiscountCoupon dc WHERE dc.discountPercentage = :discountPercentage",
                        DiscountCoupon.class).
                setParameter("discountPercentage", discountPercentage).getSingleResult();
    }
}
