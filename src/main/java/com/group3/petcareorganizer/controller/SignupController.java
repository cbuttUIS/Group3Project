package com.group3.petcareorganizer.controller;

import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/* Controller annotation indicates that this is a controller class and will
 handle incoming requests from users for account creation via sign up
 */
@Controller
public class SignupController {

    // @Autowired with ownerRepository is for saving the new owner/user to the database
    @Autowired
    private OwnerRepository ownerRepository;

    // @Autowired with passwordEncode is used to encode the new owner's password before saving them in the database
    @Autowired
    private PasswordEncoder passwordEncoder;

    /* @PostMapping is to connect the /signup url and handles new sign up form submissions
     */
    @PostMapping(value = "/signup")
    @ResponseBody
    public String createOwner(@RequestBody Owner owner){


        if (ownerRepository.existsByUsername(owner.getUsername())) {
            return "redirect:/signup?error=username-taken";
        }

        // Encoding the password
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));

        // Save the owner in the database
        ownerRepository.save(owner);

        // Redirects the user to the login page (/login.html) after saving the new owner/user
        return "Success";
    }
}
