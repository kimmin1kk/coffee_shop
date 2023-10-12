package com.dnlab.coffeeshop.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    @RequestMapping("/login")
    public String login() {
        return "account/login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "index";
    }
}
