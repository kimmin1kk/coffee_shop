package com.dnlab.coffeeshop.user.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddAddressForm {
    private String addressName;
    private String postalCode;
    private String defaultAddress;
    private String detailAddress;
}
