package org.example.clothingstore.dto;

import org.example.clothingstore.entities.Brand;
import org.example.clothingstore.entities.ClothingCategory;

public class DiscountCouponDTO {

    private int couponId;
    private ClothingCategory clothingCategory;
    private Brand brand;
    private float discountPercentage;
    private float minOrderAmount;

    public DiscountCouponDTO(int couponId, ClothingCategory clothingCategory, Brand brand, float discountPercentage, float minOrderAmount) {
        setCouponId(couponId);
        setClothingCategory(clothingCategory);
        setBrand(brand);
        setDiscountPercentage(discountPercentage);
        setMinOrderAmount(minOrderAmount);
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public ClothingCategory getClothingCategory() {
        return clothingCategory;
    }

    public void setClothingCategory(ClothingCategory clothingCategory) {
        this.clothingCategory = clothingCategory;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public float getMinOrderAmount() {
        return minOrderAmount;
    }

    public void setMinOrderAmount(float minOrderAmount) {
        this.minOrderAmount = minOrderAmount;
    }
}
