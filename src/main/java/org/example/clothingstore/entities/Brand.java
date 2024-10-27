package org.example.clothingstore.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "brand")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Brand extends BaseEntity {

    private String brandName;

    public Brand(String brandName) {
        setBrandName(brandName);
    }

    protected Brand() {}

    @Column(name = "brand_name", nullable = false)
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
