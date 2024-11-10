package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.clothingstore.entities.DiscountCoupon;
import org.example.clothingstore.repositories.BaseCRRepository;
import org.example.clothingstore.repositories.DiscountCouponRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DiscountCouponRepositoryImpl extends BaseCRRepository<DiscountCoupon> implements DiscountCouponRepository {

    @PersistenceContext
    EntityManager em;

    public DiscountCouponRepositoryImpl() {
        super(DiscountCoupon.class);
    }

    @Override
    public DiscountCoupon findById(int id) {
        return em.createQuery("SELECT dc FROM DiscountCoupon dc WHERE dc.id = :id", DiscountCoupon.class).
                setParameter("id", id).getSingleResult();
    }

    @Override
    public List<DiscountCoupon> findByDiscountPercentage(float discountPercentage) {
        return em.createQuery("SELECT dc FROM DiscountCoupon dc WHERE " +
                        "dc.discountPercentage = :discountPercentage", DiscountCoupon.class).
                setParameter("discountPercentage", discountPercentage).getResultList();
    }
}
