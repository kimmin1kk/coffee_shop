package com.dnlab.coffeeshop.product.controller;

import com.dnlab.coffeeshop.product.common.RecipeAddForm;
import com.dnlab.coffeeshop.product.domain.Recipe;
import com.dnlab.coffeeshop.product.repository.RecipeRepository;
import com.dnlab.coffeeshop.product.service.IngredientService;
import com.dnlab.coffeeshop.product.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RecipeController {

    private final IngredientService ingredientService;
    private final RecipeService recipeService;
    private final RecipeRepository recipeRepository;

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

    @GetMapping("/edit-recipe/{seq}")
    public String editForm(Model model, Principal principal, @PathVariable("seq") Long recipeSeq) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeSeq);
        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            model.addAttribute("recipe", recipe);
            model.addAttribute("ingredients", ingredientService.getIngredientList());
        }
        return "/product/editRecipeForm";
    }

    @PostMapping("/edit-recipe/{recipeSeq}")
    public String recipeEdit(Model model, Principal principal, @PathVariable("recipeSeq") Long recipeSeq, Recipe recipe, @RequestParam("productSeq") Long productSeq) {
        recipeService.updateRecipe(recipeSeq, recipe);

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeSeq);
        return "redirect:/recipe-list/" + productSeq;
    }
}
