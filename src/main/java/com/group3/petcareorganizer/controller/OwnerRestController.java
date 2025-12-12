package com.group3.petcareorganizer.controller;


import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.service.OwnerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/owner")
public class OwnerRestController {



    //for the owner services
    private final OwnerService ownerService;

    public OwnerRestController( OwnerService ownerService) {

        this.ownerService = ownerService;
    }

    @GetMapping
    public Owner getCurrentOwner(){
        return ownerService.getCurrentOwner();
    }

    @GetMapping("/username")
    public String getUsername(){
        Owner owner = ownerService.getCurrentOwner();
        return owner.getUsername();
    }

    @GetMapping("/email")
    public String getEmail(){
        Owner owner = ownerService.getCurrentOwner();
        return owner.getEmail();
    }
}
