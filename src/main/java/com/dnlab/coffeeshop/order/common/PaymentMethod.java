package com.dnlab.coffeeshop.order.common;

public enum PaymentMethod {
    UNKNOWN,
    CASH,
    CREDIT_CARD,
    GIFT_CARD,
    CHECK;

    public String getDisplayName() {
        return switch (this) {
            case UNKNOWN -> "알 수 없음";
            case CASH -> "현금";
            case CREDIT_CARD -> "신용카드";
            case GIFT_CARD -> "상품권";
            case CHECK -> "수표";
        };
    }

}
