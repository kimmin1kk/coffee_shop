package com.dnlab.coffeeshop.product.controller;

import com.dnlab.coffeeshop.product.common.RecipeAddForm;
import com.dnlab.coffeeshop.product.service.IngredientService;
import com.dnlab.coffeeshop.product.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("recipeAddForm", recipeAddForm);
        model.addAttribute("ingredients", ingredientService.getIngredientList());
        return "product/addRecipeForm";
    }

    @PostMapping("/add-recipe")
    public String processAddRecipe(RecipeAddForm recipeAddForm, @RequestParam("ingredientName") String ingredientName) {
        recipeService.processingAddRecipe(recipeAddForm, ingredientName, recipeAddForm.getProductSeq());
        return "redirect:/";
    }


}
