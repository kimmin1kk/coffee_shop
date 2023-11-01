package com.dnlab.coffeeshop.supllier.controller;

import com.dnlab.coffeeshop.product.service.IngredientService;
import com.dnlab.coffeeshop.supllier.common.SupplyAddForm;
import com.dnlab.coffeeshop.supllier.service.SupplierService;
import com.dnlab.coffeeshop.supllier.service.SupplyContentService;
import com.dnlab.coffeeshop.supllier.service.SupplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SupplyController {
    private final SupplyService supplyService;
    private final SupplierService supplierService;
    private final SupplyContentService supplyContentService;
    private final IngredientService ingredientService;

    @GetMapping("/add-supply")
    public String supplyAddForm(Model model) {
        SupplyAddForm supplyAddForm = new SupplyAddForm();
        supplyAddForm.getSupplyContentList().add(new SupplyAddForm.SupplyContentInfo());

        model.addAttribute("supplyContentList", supplyAddForm.getSupplyContentList());
        model.addAttribute("suppliers", supplierService.getSupplierList());
        model.addAttribute("ingredients", ingredientService.getIngredientList());
        model.addAttribute("supplyAddForm", supplyAddForm);
        return "/supply/addSupplyForm";
    }

}
