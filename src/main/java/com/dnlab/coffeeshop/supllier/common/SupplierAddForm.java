package com.dnlab.coffeeshop.supllier.common;

import com.dnlab.coffeeshop.supllier.domain.Supplier;
import lombok.Data;

@Data
public class SupplierAddForm {
    public String name;
    public String address;

    public Supplier toEntity() {
        return Supplier.builder()
                .name(name)
                .address(address)
                .build();
    }

}
