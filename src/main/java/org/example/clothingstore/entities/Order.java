package org.example.clothingstore.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "order")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Order extends BaseEntity {

    private User user;
    private DiscountCoupon discountCoupon;
    private Date date;
    private float orderAmount;
    private OrderStatusEnum orderStatus;
    private int quantityOfProducts;

    public Order(User user, DiscountCoupon discountCoupon, Date date, float orderAmount, OrderStatusEnum orderStatus,
                 int quantityOfProducts) {
        setUser(user);
        setDiscountCoupon(discountCoupon);
        setDate(date);
        setOrderAmount(orderAmount);
        setOrderStatus(orderStatus);
        setQuantityOfProducts(quantityOfProducts);
    }

    protected Order() {}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne(fetch = FetchType.LAZY)
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
}
