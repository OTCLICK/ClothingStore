package org.example.clothingstore.dto;

import org.example.clothingstore.entities.DiscountCoupon;
import org.example.clothingstore.entities.OrderStatusEnum;
import org.example.clothingstore.entities.User;

import java.util.Date;

public class OrderDTO {

    String id;
    private User user;
    private DiscountCoupon discountCoupon;
    private Date date;
    private float orderAmount;
    private OrderStatusEnum orderStatus;
    private int quantityOfProducts;

    public OrderDTO() {}

    public OrderDTO(String id, User user, DiscountCoupon discountCoupon, Date date, float orderAmount, OrderStatusEnum orderStatus, int quantityOfProducts) {
        setId(id);
        setUser(user);
        setDiscountCoupon(discountCoupon);
        setDate(date);
        setOrderAmount(orderAmount);
        setOrderStatus(orderStatus);
        setQuantityOfProducts(quantityOfProducts);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DiscountCoupon getDiscountCoupon() {
        return discountCoupon;
    }

    public void setDiscountCoupon(DiscountCoupon discountCoupon) {
        this.discountCoupon = discountCoupon;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(float orderAmount) {
        this.orderAmount = orderAmount;
    }

    public OrderStatusEnum getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getQuantityOfProducts() {
        return quantityOfProducts;
    }

    public void setQuantityOfProducts(int quantityOfProducts) {
        this.quantityOfProducts = quantityOfProducts;
    }
}
