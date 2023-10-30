package com.dnlab.coffeeshop.order.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderPageForm {
    private boolean orderInstant;

    private Long addressSeq;
    private String postalCode;
    private String defaultAddress;
    private String detailAddress;

    private Long cardSeq;
    private String cardNumber;
    private String cardType;
    private String validation;
}
