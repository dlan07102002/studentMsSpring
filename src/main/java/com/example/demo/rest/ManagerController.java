package com.example.demo.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @GetMapping()
    public String showManagePage() {
        return "manager/manager";
    }
}
