package com.dnlab.coffeeshop.product.controller;

import com.dnlab.coffeeshop.product.common.IngredientAddForm;
import com.dnlab.coffeeshop.product.service.IngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
@Slf4j
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping("/add-ingredient")
    public String addForm() {
        return "/product/addIngredientForm";
    }

    @PostMapping("/add-ingredient")
    public String processAddIngredient(IngredientAddForm ingredientAddForm) {
        ingredientService.processAddIngredient(ingredientAddForm);

        return "redirect:/";
    }
}
