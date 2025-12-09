package com.group3.petcareorganizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {


    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/pets")
    public String petsList() {
        return "pets/pets";
    }

    @GetMapping("/account-info")
    public String accountInfo() {
        return "account-info";
    }




}
