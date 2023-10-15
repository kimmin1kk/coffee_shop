package com.dnlab.coffeeshop.product.service;

import com.dnlab.coffeeshop.product.common.IngredientAddForm;
import com.dnlab.coffeeshop.product.domain.Ingredient;
import com.dnlab.coffeeshop.product.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public void processAddIngredient(IngredientAddForm ingredientAddForm) {
        ingredientRepository.save(ingredientAddForm.addIngredient());
    }

    public List<Ingredient> getIngredientList() {
        return ingredientRepository.findAll();
    }

}
