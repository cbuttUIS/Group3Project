package com.group3.petcareorganizer.controller;


import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.service.OwnerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* @RestController means that this class handles API requests and returns information in JSON format (for javascript
    purposes)
   @RequestMapping is used to set the endpoints in this controller.
    The class is to handle information about the owner
 */
@RestController
@RequestMapping("/api/owner")
public class OwnerRestController {

    //for the owner services
    private final OwnerService ownerService;

    /* constructor for owner rest controller
     */
    public OwnerRestController( OwnerService ownerService) {

        this.ownerService = ownerService;
    }

    /* gets the owner currently logged in
     */
    @GetMapping
    public Owner getCurrentOwner(){
        return ownerService.getCurrentOwner();
    }

    /* get username of owner currently logged in
     */
    @GetMapping("/username")
    public String getUsername(){
        Owner owner = ownerService.getCurrentOwner();
        return owner.getUsername();
    }

    /* get email of owner currently logged in
     */
    @GetMapping("/email")
    public String getEmail(){
        Owner owner = ownerService.getCurrentOwner();
        return owner.getEmail();
    }
}
