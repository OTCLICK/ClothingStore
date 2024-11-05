package org.example.clothingstore.dto;

import org.example.clothingstore.entities.Product;

public class QuantityInStockDTO {

    private Product product;
    private int quantityOfProduct;

    public QuantityInStockDTO(Product product, int quantityOfProduct) {
        setProduct(product);
        setQuantityOfProduct(quantityOfProduct);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantityOfProduct() {
        return quantityOfProduct;
    }

    public void setQuantityOfProduct(int quantityOfProduct) {
        this.quantityOfProduct = quantityOfProduct;
    }
}
