package com.dnlab.coffeeshop.product.common;

public enum Category {
    ALL,
    COFFEE,
    TEA,
    DRINK,
    BAKERY,
    GOODS,
    SET,
    GIFT_CARD,
    WIFI;

    public String getDisplayName() {
        return switch (this) {
            case ALL -> "전체";
            case COFFEE -> "커피";
            case TEA -> "차";
            case DRINK -> "음료";
            case BAKERY -> "제과";
            case GOODS -> "상품";
            case SET -> "세트 메뉴";
            case GIFT_CARD -> "상품권";
            case WIFI -> "무선 인터넷";
        };
    }
}
