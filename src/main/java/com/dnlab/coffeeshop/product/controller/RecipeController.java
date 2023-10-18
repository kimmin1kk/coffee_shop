package com.dnlab.coffeeshop.product.controller;

import com.dnlab.coffeeshop.product.common.RecipeAddForm;
import com.dnlab.coffeeshop.product.domain.Recipe;
import com.dnlab.coffeeshop.product.service.IngredientService;
import com.dnlab.coffeeshop.product.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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

    @GetMapping("/recipe-list/{seq}")
    public String recipeList(Model model, Principal principal,@PathVariable("seq") Long productSeq) {
        model.addAttribute("recipes", recipeService.getRecipes(productSeq));

        return "product/recipeList";

    }
    @PostMapping("/edit-recipe/{seq}")
    public String recipeEdit(Model model, Principal principal ,@PathVariable("seq") Long recipeSeq, Recipe recipe) {
        recipeService.updateRecipe(recipeSeq, recipe);

        return "redirect:/recipe-list";
    }
}
