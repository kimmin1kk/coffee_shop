package com.dnlab.coffeeshop.product.service;

import com.dnlab.coffeeshop.product.common.RecipeAddForm;
import com.dnlab.coffeeshop.product.domain.Ingredient;
import com.dnlab.coffeeshop.product.domain.Product;
import com.dnlab.coffeeshop.product.domain.Recipe;
import com.dnlab.coffeeshop.product.repository.IngredientRepository;
import com.dnlab.coffeeshop.product.repository.ProductRepository;
import com.dnlab.coffeeshop.product.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;

    public void processingAddRecipe(RecipeAddForm recipeAddForm) {
        Product product = productRepository.findBySeq(recipeAddForm.getProductSeq());

        for (RecipeAddForm.IngredientInfo info : recipeAddForm.getIngredients()) {
            Ingredient ingredient = ingredientRepository.findByName(info.getIngredientName());
            Recipe recipe = info.addRecipe(ingredient, product);
            recipeRepository.save(recipe);
        }
    }



}
