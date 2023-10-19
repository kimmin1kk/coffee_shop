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
import java.util.Optional;

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

    public Recipe updateRecipe(Long recipeSeq, Recipe updatedRecipe) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeSeq);
        if (recipeOptional.isPresent()) {
            Recipe existingRecipe = recipeOptional.get();
            existingRecipe.setAmount(updatedRecipe.getAmount());
            existingRecipe.setUnit(updatedRecipe.getUnit());
            return recipeRepository.save(existingRecipe);
        } else {
            throw new RuntimeException("ser not found with seq:" + recipeSeq);
        }
    }


    public List<Recipe> getRecipes(Long productSeq) {
        return recipeRepository.findRecipesByProductSeq(productSeq);
    }



}
