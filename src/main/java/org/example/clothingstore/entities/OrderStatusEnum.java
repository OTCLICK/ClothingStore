package org.example.clothingstore.entities;

public enum OrderStatusEnum {

    NOT_PAID(0),
    PAID(1);

    private final int VALUE;

    OrderStatusEnum(int value) {
        this.VALUE = value;
    }

    public int getValue() {
        return VALUE;
    }

}
