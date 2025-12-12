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


    private final PetRepository petRepository;
    private final OwnerService ownerService;

    @Autowired
    public PetController(PetRepository petRepository, OwnerService ownerService) {
        this.petRepository = petRepository;
        this.ownerService = ownerService;
    }

    @GetMapping("/pets/add-pet")
    public String addPetForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "pets/add-pet";
    }

    @PostMapping("/pets/add")
    public String savePet(@ModelAttribute Pet pet) {
        Owner owner = ownerService.getCurrentOwner();
        pet.setOwner(owner);
        petRepository.save(pet);
        return "redirect:/pets"; // go back to pets list
    }


}
