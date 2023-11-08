package com.dnlab.coffeeshop.product.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class ProductEditForm {
    @NotBlank
    private String name;
    @NotBlank
    private int price;
    @NotBlank
    private Category category;
}
