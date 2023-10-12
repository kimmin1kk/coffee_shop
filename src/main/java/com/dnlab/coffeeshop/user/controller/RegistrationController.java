package com.dnlab.coffeeshop.user.controller;


import com.dnlab.coffeeshop.user.common.RegistrationForm;
import com.dnlab.coffeeshop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping
    public String registerForm() {
        log.info("RegistrationController -> registerForm : OK");
        return "account/register";
    }
    @PostMapping
    public String processRegistration(RegistrationForm registrationForm) {
        log.info("registrationController/processRegistration : OK");
        userService.processRegistration(registrationForm);
        return "redirect:/login";
    }

}
