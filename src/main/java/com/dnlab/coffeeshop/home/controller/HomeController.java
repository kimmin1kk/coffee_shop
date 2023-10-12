package com.dnlab.coffeeshop.home.controller;

import com.dnlab.coffeeshop.product.domain.Product;
import com.dnlab.coffeeshop.product.service.ProductService;
import com.dnlab.coffeeshop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;
    private final UserService userService;

    public String home(Model model, Principal principal) {
        List<Product> displayProducts = productService.productList();
        model.addAttribute("products", displayProducts);

        return "index";
    }

    @GetMapping("/my-page")
    public String myPage(Model model, Principal principal) {
        model.addAttribute("user", userService.findUserInformationByUsername(principal.getName()));
        model.addAttribute("addressList", userService.findUserAddressListByUsername(principal.getName()));

        return "account/myPage";
    }

    @GetMapping("/delete-address/{seq})")
    public String addressDelete(@PathVariable Long seq) {
        userService.deleteAddressBySeq(seq);
        return "redirect:/my-page";
    }
}
