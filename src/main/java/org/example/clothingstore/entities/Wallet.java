package org.example.clothingstore.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "wallet")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Wallet extends BaseEntity {

    private User user;
    private float amount;

    public Wallet(User user, float amount) {
        this.user = user;
        this.amount = amount;
    }

    protected Wallet() {}

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "amount", nullable = false)
    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
