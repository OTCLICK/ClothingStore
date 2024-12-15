package org.example.clothingstore.entities;

public enum UserRolesEnum {

    CUSTOMER(0),
    ADMIN(1);

    private final int VALUE;

    UserRolesEnum(int value) {
        this.VALUE = value;
    }

    public int getValue() {
        return VALUE;
    }

}
