package com.dnlab.coffeeshop.user.common;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class AddAddressForm {
    private String addressName;
    private String postalCode;
    private String defaultAddress;
    private String detailAddress;
}
