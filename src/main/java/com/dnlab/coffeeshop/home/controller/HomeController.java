package com.dnlab.coffeeshop.home.controller;

import com.dnlab.coffeeshop.product.domain.Product;
import com.dnlab.coffeeshop.product.service.ProductService;
import com.dnlab.coffeeshop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        List<Product> displayProducts = productService.getProductList();
        model.addAttribute("products", displayProducts);

        return "index";
    }

    @GetMapping("/my-page")
    public String myPage(Model model, Principal principal) {
        model.addAttribute("user", userService.findUserInformationByUsername(principal.getName()));
        return "account/myPage";
    }

}
