package org.example.clothingstore.dto;

import org.example.clothingstore.entities.Brand;

public class CountryOfOriginDTO {

    private Brand brand;
    private String country;

    public CountryOfOriginDTO() {}

    public CountryOfOriginDTO(Brand brand, String country) {
        setBrand(brand);
        setCountry(country);
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
