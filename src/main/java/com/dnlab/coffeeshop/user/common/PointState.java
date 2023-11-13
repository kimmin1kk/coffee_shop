package com.dnlab.coffeeshop.user.common;

public enum PointState {
    PLUS,
    MINUS;

    public String getDisplayName() {
        return switch (this) {
            case PLUS -> "적립";
            case MINUS -> "차감";
        };
    }
}
