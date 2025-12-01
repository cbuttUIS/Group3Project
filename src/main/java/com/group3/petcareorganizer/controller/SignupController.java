package com.group3.petcareorganizer.controller;



import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping(value = "/signup")
    public String createUser(Owner owner){
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        ownerRepository.save(owner);

        return "redirect:/login";
    }
}
