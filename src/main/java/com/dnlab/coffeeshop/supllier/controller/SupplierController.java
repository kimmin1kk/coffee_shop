package com.dnlab.coffeeshop.supllier.controller;

import com.dnlab.coffeeshop.supllier.common.SupplierAddForm;
import com.dnlab.coffeeshop.supllier.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping("/add-supplier")
    public String supplierAddForm() {
        return "/supply/addSupplierForm";
    }

    @PostMapping("/add-supplier")
    public String processAddSupplier(SupplierAddForm supplierAddForm) {
        supplierService.addSupplier(supplierAddForm);
        return "redirect:/";
    }
}
