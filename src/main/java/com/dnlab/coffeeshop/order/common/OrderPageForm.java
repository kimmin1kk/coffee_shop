package com.dnlab.coffeeshop.order.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderPageForm {
    private boolean orderInstant;
    private PaymentMethod paymentMethod;
    private Integer totalPrice;
}
