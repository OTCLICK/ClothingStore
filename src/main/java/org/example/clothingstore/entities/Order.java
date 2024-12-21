package org.example.clothingstore.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Order extends BaseEntity implements Serializable {

    private User user;
    private DiscountCoupon discountCoupon;
    private Date date;
    private float orderAmount;
    private OrderStatusEnum orderStatus;
    private int quantityOfProducts;

    private List<Product> products = new ArrayList<>();

    public Order(User user, DiscountCoupon discountCoupon, Date date, float orderAmount, OrderStatusEnum orderStatus,
                 int quantityOfProducts) {
        setUser (user);
        setDiscountCoupon(discountCoupon);
        setDate(date);
        setOrderAmount(orderAmount);
        setOrderStatus(orderStatus);
        setQuantityOfProducts(quantityOfProducts);
    }

    public Order(String id, User user, DiscountCoupon discountCoupon, Date date, float orderAmount, OrderStatusEnum orderStatus,
                 int quantityOfProducts) {
        setId(id);
        this.user = user;
        this.discountCoupon = discountCoupon;
        this.date = date;
        this.orderAmount = orderAmount;
        this.orderStatus = orderStatus;
        this.quantityOfProducts = quantityOfProducts;
    }

    protected Order() {}

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser () {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "discount_coupon_id", referencedColumnName = "id")
    public DiscountCoupon getDiscountCoupon() {
        return discountCoupon;
    }

    public void setDiscountCoupon(DiscountCoupon discountCoupon) {
        this.discountCoupon = discountCoupon;
    }

    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "order_amount", nullable = false)
    public float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(float orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Column(name = "order_status", nullable = false)
    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Column(name = "quantity_of_products", nullable = false)
    public int getQuantityOfProducts() {
        return quantityOfProducts;
    }

    public void setQuantityOfProducts(int quantityOfProducts) {
        this.quantityOfProducts = quantityOfProducts;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }
}

