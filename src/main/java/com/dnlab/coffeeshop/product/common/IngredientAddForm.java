package com.dnlab.coffeeshop.product.common;

import com.dnlab.coffeeshop.product.domain.Ingredient;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class IngredientAddForm {
    @NotBlank
    private String name;


    public Ingredient addIngredient() {
        return Ingredient.builder()
                .name(name)
                .build();
    }
}
