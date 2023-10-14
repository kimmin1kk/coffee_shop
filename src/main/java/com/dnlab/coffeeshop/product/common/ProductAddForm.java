package com.dnlab.coffeeshop.product.common;

import com.dnlab.coffeeshop.product.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class ProductAddForm {
    @NotBlank
    private String name;
    @NotBlank
    private Integer price;
    @NotBlank
    private Category category;

    public Product addProduct() {
        return Product.builder()
                .name(name)
                .price(price)
                .category(category)
                .recipeList(new ArrayList<>())
                .build();
    }

}
