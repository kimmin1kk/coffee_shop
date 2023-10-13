package com.dnlab.coffeeshop.supllier.common;

import com.dnlab.coffeeshop.supllier.domain.Supplier;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class SupplierAddForm {
    public String name;
    public String address;

    public Supplier addSupplier() {
        return Supplier.builder()
                .name(name)
                .address(address)
                .build();
    }

}
