package com.group3.petcareorganizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/* Controller annotation indicates that this is a controller class and will
 handle incoming requests from users for the login, signup, and homepage
 */
@Controller
public class ContentController {


    /*
     @GetMapping connects to and says that the /login url handles requests from the login form for a returning user
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /*
    @GetMapping connects to and says that the /signup url handles requests from the signup form for a new user
     */
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    /*
     @GetMapping connects to and says that the /index url handles requests from the homepage
     */
    @GetMapping("/index")
    public String home(){
        return "index";
    }

}
