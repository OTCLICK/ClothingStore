package org.example.clothingstore.repositories;

import org.example.clothingstore.entities.QuantityInStock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantityInStockRepository {

    void save(QuantityInStock quantityInStock);
    void update(QuantityInStock quantityInStock);
    List<QuantityInStock> findByQuantityOfProduct(int quantityOfProduct);

}
