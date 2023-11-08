package com.dnlab.coffeeshop.user.controller;

import com.dnlab.coffeeshop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserRepository userRepository;

    @RequestMapping("/login")
    public String login() {
        return "account/login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "index";
    }

    @GetMapping("/user-list")
    public String userList(Model model, Principal principal) {
        model.addAttribute("users", userRepository.findAll());
        return "/account/userList";
    }
}
