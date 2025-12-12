package com.group3.petcareorganizer.controller;

import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.model.Pet;
import com.group3.petcareorganizer.repository.PetRepository;
import com.group3.petcareorganizer.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/* Controller annotation indicates that this is a controller class and will
 handle incoming requests from users for pet additions
 */
@Controller
public class PetController {

    // access pet data
    private final PetRepository petRepository;
    //access owner services
    private final OwnerService ownerService;

    /* constructor for pet controller
     */
    @Autowired
    public PetController(PetRepository petRepository, OwnerService ownerService) {
        this.petRepository = petRepository;
        this.ownerService = ownerService;
    }

    /*gets the add pet form and its url
     */
    @GetMapping("/pets/add-pet")
    public String addPetForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "pets/add-pet";
    }

    /*posts the added pet to the pets list
     */
    @PostMapping("/pets/add")
    public String savePet(@ModelAttribute Pet pet) {
        Owner owner = ownerService.getCurrentOwner();
        pet.setOwner(owner);
        petRepository.save(pet);
        return "redirect:/pets"; // go back to pets list
    }


}
