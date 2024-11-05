package org.example.clothingstore.dto;

import org.example.clothingstore.entities.SeasonEnum;

public class ClothingCategoryDTO {

    private String categoryName;
    private SeasonEnum season;

    public ClothingCategoryDTO() {}

    public ClothingCategoryDTO(SeasonEnum season, String categoryName) {
        setSeason(season);
        setCategoryName(categoryName);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public SeasonEnum getSeason() {
        return season;
    }

    public void setSeason(SeasonEnum season) {
        this.season = season;
    }
}
