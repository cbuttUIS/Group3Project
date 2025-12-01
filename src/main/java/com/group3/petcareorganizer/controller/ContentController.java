package com.group3.petcareorganizer.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Controller annotation indicates that this class is a controller and will
// handle incoming requests from users.
@Controller
public class ContentController {

    // get mapping annotation connects the code to the url that we need
    // here that url will be for the log in page
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

}
