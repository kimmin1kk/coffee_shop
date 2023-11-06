package com.dnlab.coffeeshop.product.common;

import com.dnlab.coffeeshop.product.domain.Ingredient;
import com.dnlab.coffeeshop.product.domain.Product;
import com.dnlab.coffeeshop.product.domain.Recipe;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RecipeAddForm {

    private Long productSeq;

    private List<IngredientInfo> ingredients = new ArrayList<>();

    @Getter
    @Setter
    public static class IngredientInfo {
        private String ingredientName;
        private Integer amount;
//        private Unit unit;

        public Recipe addRecipe(Ingredient ingredient, Product product) {
            return Recipe.builder()
                    .ingredient(ingredient)
                    .product(product)
                    .amount(amount)
//                    .unit(unit)
                    .build();
        }
    }
}

