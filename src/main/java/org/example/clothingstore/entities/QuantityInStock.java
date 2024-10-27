package org.example.clothingstore.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "quantity_in_stock")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class QuantityInStock {

    private Product product;
    private int quantityOfProduct;

    public QuantityInStock(Product product, int quantityOfProduct) {
        setProduct(product);
        setQuantityOfProduct(quantityOfProduct);
    }

    protected QuantityInStock() {}

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "quantity_of_product", nullable = false)
    public int getQuantityOfProduct() {
        return quantityOfProduct;
    }

    public void setQuantityOfProduct(int quantityOfProduct) {
        this.quantityOfProduct = quantityOfProduct;
    }
}
