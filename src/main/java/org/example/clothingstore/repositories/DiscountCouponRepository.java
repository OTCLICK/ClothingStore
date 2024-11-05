package org.example.clothingstore.repositories;

import org.example.clothingstore.entities.DiscountCoupon;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountCouponRepository {

    void save(DiscountCoupon discountCoupon);
    DiscountCoupon findById(int id);
    List<DiscountCoupon> findByDiscountPercentage(float discountPercentage);

}
