package com.dnlab.coffeeshop.ingredient.common;

public enum Unit {
    KILOMETER,
    LITER,
    POUND,
    KILOLITER,
    KILOGRAM,
    GRAM;

    public String getDisplayName() {
        return switch (this) {
            case KILOMETER -> "킬로미터";
            case KILOLITER -> "킬로리터";
            case LITER -> "리터";
            case GRAM -> "그램";
            case KILOGRAM -> "킬로그램";
            case POUND -> "파운드";
        };
    }
}
