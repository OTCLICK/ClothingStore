package org.example.clothingstore.repositories;

import org.example.clothingstore.entities.CountryOfOrigin;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryOfOriginRepository {

    void save(CountryOfOrigin countryOfOrigin);
    void update(CountryOfOrigin countryOfOrigin);

}
