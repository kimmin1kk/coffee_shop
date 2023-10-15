package com.dnlab.coffeeshop.supllier.controller;

import com.dnlab.coffeeshop.supllier.common.SupplierAddForm;
import com.dnlab.coffeeshop.supllier.domain.Supplier;
import com.dnlab.coffeeshop.supllier.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

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

    @GetMapping("/supplier-list")
    public String supplierList(Model model, Principal principal) {

        model.addAttribute("suppliers", supplierService.getSupplierList());
        return "/supply/supplierList";
    }
}
