package com.group3.petcareorganizer.controller;



import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


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
    public String createUser(Owner owner){

        // Encoding the password
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));

        // Save the owner in the database
        ownerRepository.save(owner);

        // Redirects the user to the login page (/login.html) after saving the new owner/user
        return "redirect:/login";
    }
}
