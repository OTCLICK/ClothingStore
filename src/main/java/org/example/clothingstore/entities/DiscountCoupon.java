package org.example.clothingstore.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "discount_coupon")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class DiscountCoupon extends BaseEntity implements Serializable {

    private ClothingCategory clothingCategory;
    private Brand brand;
    private float discountPercentage;
    private float minOrderAmount;

    public DiscountCoupon(ClothingCategory clothingCategory, Brand brand, float discountPercentage, float minOrderAmount) {
        setClothingCategory(clothingCategory);
        setBrand(brand);
        setDiscountPercentage(discountPercentage);
        setMinOrderAmount(minOrderAmount);
    }

    protected DiscountCoupon() {}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clothing_category_id", referencedColumnName = "id", nullable = false)
    public ClothingCategory getClothingCategory() {
        return clothingCategory;
    }

    public void setClothingCategory(ClothingCategory clothingCategory) {
        this.clothingCategory = clothingCategory;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Column(name = "discount_percentage", nullable = false)
    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Column(name = "min_order_amount")
    public float getMinOrderAmount() {
        return minOrderAmount;
    }

    public void setMinOrderAmount(float minOrderAmount) {
        this.minOrderAmount = minOrderAmount;
    }
}
