package com.group3.petcareorganizer.controller;

import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // fetch the user that is logged-in
        Owner owner = ownerService.getCurrentOwner();
        model.addAttribute("owner", owner);
        return "dashboard";
    }

}
