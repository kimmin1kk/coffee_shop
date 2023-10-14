package com.dnlab.coffeeshop.product.controller;

import com.dnlab.coffeeshop.product.common.ProductAddForm;
import com.dnlab.coffeeshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping("/add-product")
    public String productAddForm() {
        return "/product/productForm";
    }

    @PostMapping("/add-product")
    public String processAddProduct(ProductAddForm productAddForm) {
        productService.processingAddProduct(productAddForm);
        return "redirect:/";
    }
}
