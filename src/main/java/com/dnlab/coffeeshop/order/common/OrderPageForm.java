package com.dnlab.coffeeshop.order.common;

import lombok.Data;

@Data
public class OrderPageForm {
    private boolean orderInstant;
    private PaymentMethod paymentMethod;
    private Integer totalPrice;
    private Integer point;
}
