package com.dnlab.coffeeshop.product.controller;

import com.dnlab.coffeeshop.product.common.Category;
import com.dnlab.coffeeshop.product.common.ProductAddForm;
import com.dnlab.coffeeshop.product.domain.Product;
import com.dnlab.coffeeshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping("/add-product")
    public String productAddForm() {
        return "/product/addProductForm";
    }

    @PostMapping("/add-product")
    public String processAddProduct(ProductAddForm productAddForm) {
        productService.processingAddProduct(productAddForm);
        return "redirect:/";
    }

    @GetMapping("/single-product/{seq}")
    public String singleProduct(Model model, Principal principal, @PathVariable("seq") Long seq) {
        Product product = productService.findProductBySeq(seq);
        model.addAttribute("product", product);

        return "shop/singleProduct";
    }

    @GetMapping("/search-product")
    public String searchProduct(Model model, Principal principal, String keyword, Category category) {
        List<Product> productList = productService.searchProductList(keyword, category);
        model.addAttribute("products", productList);
        return "index";
    }

    @GetMapping("/product-list")
    public String productList(Model model, Principal principal) {
        model.addAttribute("products", productService.getProductList());
        return "/product/productList";
    }
}
