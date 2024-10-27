package org.example.clothingstore.entities;

public enum SeasonEnum {

    SUMMER(0),
    WINTER(1),
    DEMI_SEASON(2),
    UNIVERSAL(3);

    private final int VALUE;

    SeasonEnum(int value) {
        this.VALUE = value;
    }

    public int getValue() {
        return VALUE;
    }

}
