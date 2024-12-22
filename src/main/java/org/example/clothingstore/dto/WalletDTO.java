package org.example.clothingstore.dto;

import org.example.clothingstore.entities.User;

public class WalletDTO {
    private String id;
    private User user;
    private float amount;

    public WalletDTO(String id, User user, float amount) {
        this.id = id;
        this.user = user;
        this.amount = amount;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
