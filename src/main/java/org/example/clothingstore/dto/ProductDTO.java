package org.example.clothingstore.dto;

import org.example.clothingstore.entities.Brand;
import org.example.clothingstore.entities.ClothingCategory;

public class ProductDTO {

    String id;
    private ClothingCategory clothingCategory;
    private Brand brand;
    private String productName;
    private String color;
    private String size;
    private float price;

    protected ProductDTO() {}

    public ProductDTO(String id, ClothingCategory clothingCategory, Brand brand, String productName, String color, String size, float price) {
        setId(id);
        setClothingCategory(clothingCategory);
        setBrand(brand);
        setProductName(productName);
        setColor(color);
        setSize(size);
        setPrice(price);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
