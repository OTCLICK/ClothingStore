package org.example.clothingstore.dto;

import org.example.clothingstore.entities.Brand;
import org.example.clothingstore.entities.ClothingCategory;

public class ProductDTO {

    private ClothingCategory clothingCategory;
    private Brand brand;
    private String productName;
    private String color;
    private String size;
    private String price;

    public ProductDTO(ClothingCategory clothingCategory, Brand brand, String productName, String color, String size, String price) {
        setClothingCategory(clothingCategory);
        setBrand(brand);
        setProductName(productName);
        setColor(color);
        setSize(size);
        setPrice(price);
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
