package com.dnlab.coffeeshop.product.service;

import com.dnlab.coffeeshop.product.common.RecipeAddForm;
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


    public void processingAddRecipe(RecipeAddForm recipeAddForm, String ingredientName, Long productSeq) {
        recipeRepository.save(recipeAddForm.addRecipe(ingredientRepository.findByName(ingredientName), productRepository.findBySeq(productSeq)));
    }

}
