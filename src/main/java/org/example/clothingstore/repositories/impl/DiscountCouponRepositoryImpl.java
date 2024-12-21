package org.example.clothingstore.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.clothingstore.entities.Brand;
import org.example.clothingstore.entities.ClothingCategory;
import org.example.clothingstore.entities.DiscountCoupon;
import org.example.clothingstore.entities.Product;
import org.example.clothingstore.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DiscountCouponRepositoryImpl extends BaseCRURepository<DiscountCoupon> implements DiscountCouponRepository {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ClothingCategoryRepository clothingCategoryRepository;

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

    @Override
    public List<DiscountCoupon> findAllCoupons() {
        return em.createQuery("SELECT dc FROM DiscountCoupon dc", DiscountCoupon.class).getResultList();
    }

    @Override
    public List<DiscountCoupon> findTopBrands(int limit) {
        List<Object[]> results = em.createQuery(
                        "SELECT dc.brand.id, MAX(dc.discountPercentage) FROM DiscountCoupon dc " +
                                "GROUP BY dc.brand.id ORDER BY MAX(dc.discountPercentage) DESC", Object[].class)
                .setMaxResults(limit)
                .getResultList();

        return results.stream()
                .map(result -> {
                    Brand brand = brandRepository.findById((String) result[0]);
                    float maxDiscount = (Float) result[1];
                    return new DiscountCoupon(null, brand, maxDiscount, 0);
                })
                .collect(Collectors.toList());
    }



    @Override
    public List<DiscountCoupon> findTopCategories(int limit) {
        List<Object[]> results = em.createQuery(
                        "SELECT dc.clothingCategory.id, MAX(dc.discountPercentage) FROM DiscountCoupon dc " +
                                "GROUP BY dc.clothingCategory.id ORDER BY MAX(dc.discountPercentage) DESC", Object[].class)
                .setMaxResults(limit)
                .getResultList();

        return results.stream()
                .map(result -> {
                    ClothingCategory clothingCategory = clothingCategoryRepository.findById((String) result[0]);
                    float maxDiscount = (Float) result[1];
                    return new DiscountCoupon(clothingCategory, null, maxDiscount, 0);
                })
                .collect(Collectors.toList());
    }

}
