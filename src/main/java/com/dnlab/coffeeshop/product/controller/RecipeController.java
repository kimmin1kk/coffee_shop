package com.dnlab.coffeeshop.product.controller;

import com.dnlab.coffeeshop.product.common.RecipeAddForm;
import com.dnlab.coffeeshop.product.service.IngredientService;
import com.dnlab.coffeeshop.product.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RecipeController {

    private final IngredientService ingredientService;
    private final RecipeService recipeService;

    @GetMapping("/add-recipe/{seq}")
    public String addForm(Model model, @PathVariable("seq") Long productSeq) {
        RecipeAddForm recipeAddForm = new RecipeAddForm();
        recipeAddForm.setProductSeq(productSeq);

        recipeAddForm.getIngredients().add(new RecipeAddForm.IngredientInfo());

        model.addAttribute("recipeAddForm", recipeAddForm);
        model.addAttribute("ingredients", ingredientService.getIngredientList());

        return "product/addRecipeForm";
    }


    @PostMapping("/add-recipe")
    public String processAddRecipe(@ModelAttribute RecipeAddForm recipeAddForm) {
        recipeService.processingAddRecipe(recipeAddForm);
        return "redirect:/";
    }


}
