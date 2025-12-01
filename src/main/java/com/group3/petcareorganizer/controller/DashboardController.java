package com.group3.petcareorganizer.controller;

import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/* This is a controller class that handles dashboard requests for the owner/user
 */
@Controller
public class DashboardController {

    // @Autowired tells the application to use methods from OwnerService
    @Autowired
    private OwnerService ownerService;

    /*
     @GetMapping connects to and says that the /dashboard url handles requests on the owner dashboard
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        // fetch the user that is logged-in
        Owner owner = ownerService.getCurrentOwner();

        //add the owner object to the model layer for viewing purposes
        model.addAttribute("owner", owner);

        // Returns the name/url (dashboard.html) to the dashboard for viewing purposes
        return "dashboard";
    }

}
