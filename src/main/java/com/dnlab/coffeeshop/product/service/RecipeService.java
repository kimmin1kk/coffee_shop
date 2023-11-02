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

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;

    public void processingAddRecipe(RecipeAddForm recipeAddForm) {
        Product product = productRepository.findBySeq(recipeAddForm.getProductSeq());

        recipeAddForm.getIngredients().stream()
                .map(info -> {
                    Ingredient ingredient = ingredientRepository.findByName(info.getIngredientName());
                    return info.addRecipe(ingredient, product);
                })
                .forEach(recipeRepository::save);
    }

    public void updateRecipe(Long recipeSeq, Recipe updatedRecipe) {
        recipeRepository.findById(recipeSeq).ifPresentOrElse(
                recipeValue -> {
                    recipeValue.setAmount(updatedRecipe.getAmount());
                    recipeValue.setUnit(updatedRecipe.getUnit());
                    recipeRepository.save(recipeValue);
                },
                () -> {
                    throw new RuntimeException("ser not found with seq:" + recipeSeq);
                }
        );
    }


    public List<Recipe> getRecipes(Long productSeq) {
        return recipeRepository.findRecipesByProductSeq(productSeq);
    }

    public void deleteRecipe(Long recipeSeq) {
        recipeRepository.deleteById(recipeSeq);
    }


}
