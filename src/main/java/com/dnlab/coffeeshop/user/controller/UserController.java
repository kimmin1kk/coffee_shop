package com.dnlab.coffeeshop.user.controller;

import com.dnlab.coffeeshop.user.common.RegistrationForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {
    @RequestMapping("/register")
    public String register(WebRequest request, Model model) {
        log.info("HomeController -> register : OK");
        RegistrationForm userRegisterDto = new RegistrationForm();
        model.addAttribute("user", userRegisterDto);
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "account/login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "index";
    }
}
