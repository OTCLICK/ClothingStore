package org.example.clothingstore.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "country_of_origin")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class CountryOfOrigin extends BaseEntity {

    private Brand brand;
    private String country;

    public CountryOfOrigin(Brand brand, String country) {
        setBrand(brand);
        setCountry(country);
    }

    protected CountryOfOrigin() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Column(name = "country", nullable = false)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
