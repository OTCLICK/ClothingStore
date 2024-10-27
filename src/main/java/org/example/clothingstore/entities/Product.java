package org.example.clothingstore.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Product extends BaseEntity {

    private ClothingCategory clothingCategory;
    private Brand brand;
    private String productName;
    private String color;
    private String size;
    private String price;

    public Product(ClothingCategory clothingCategory, Brand brand, String productName, String color, String size, String price) {
        setClothingCategory(clothingCategory);
        setBrand(brand);
        setProductName(productName);
        setColor(color);
        setSize(size);
        setPrice(price);
    }

    protected Product() {}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clothing_category_id", referencedColumnName = "id", nullable = false)
    public ClothingCategory getClothingCategory() {
        return clothingCategory;
    }

    public void setClothingCategory(ClothingCategory clothingCategory) {
        this.clothingCategory = clothingCategory;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Column(name = "product_name", nullable = false)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Column(name = "color", nullable = false)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "size", nullable = false)
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Column(name = "price", nullable = false)
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}