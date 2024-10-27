package org.example.clothingstore.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "clothing_category")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ClothingCategory extends BaseEntity {

    private String categoryName;
    private SeasonEnum season;

    public ClothingCategory(String categoryName, SeasonEnum season) {
        setCategoryName(categoryName);
        setSeason(season);
    }

    protected ClothingCategory() {}

    @Column(name = "category_name", nullable = false)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Column(name = "season", nullable = false)
    public SeasonEnum getSeason() {
        return season;
    }

    public void setSeason(SeasonEnum season) {
        this.season = season;
    }
}
