package com.example.demo.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;

@Controller
@RequestMapping()
public class LoginController {
    @GetMapping("/showLoginPage")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/showPage403")
    public String showPage403() {
        return "error/403";
    }

}
