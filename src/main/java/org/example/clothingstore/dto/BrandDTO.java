package org.example.clothingstore.dto;

public class BrandDTO {

    private String brandName;

    public BrandDTO() {}

    public BrandDTO(String brandName) {
        setBrandName(brandName);
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
