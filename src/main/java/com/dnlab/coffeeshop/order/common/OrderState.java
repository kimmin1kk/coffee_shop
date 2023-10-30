package com.dnlab.coffeeshop.order.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderState {
    PREPARING, CANCELED, RETURN, SHIPPING ,CONFIRM;

    public String getDisplayName() {
        return switch (this) {
            case PREPARING -> "준비 중";
            case CANCELED -> "취소 처리";
            case RETURN -> "반품 처리";
            case SHIPPING -> "배송 중";
            case CONFIRM -> "배송 완료";
        };
    }
}
