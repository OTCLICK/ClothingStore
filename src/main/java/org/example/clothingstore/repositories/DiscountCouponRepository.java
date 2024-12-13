package org.example.clothingstore.repositories;

import org.example.clothingstore.entities.DiscountCoupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountCouponRepository {

    void save(DiscountCoupon discountCoupon);
    DiscountCoupon findById(String id);
    List<DiscountCoupon> findListByDiscountPercentage(float discountPercentage);
    DiscountCoupon findByDiscountPercentage(float discountPercentage);
    Page<DiscountCoupon> findAll(Pageable pageable);
    List<DiscountCoupon> getAllDiscountCoupons();
    void saveAll(List<DiscountCoupon> discountCoupons);
    void deleteById(String id);


}
