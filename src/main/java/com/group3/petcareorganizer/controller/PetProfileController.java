package com.group3.petcareorganizer.controller;

import com.group3.petcareorganizer.model.Pet;
import com.group3.petcareorganizer.repository.PetRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/* Controller annotation indicates that this is a controller class and will
 handle incoming requests from users for managing profile info
 */
@Controller
public class PetProfileController {

    private final PetRepository petRepository;

    public PetProfileController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }


    @GetMapping("/pets/{id}")
    public String viewPetProfile(@PathVariable Long id, Model model) {

        Pet pet =  petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Pet Id: " + id));

        //load eventts
        pet.getEvents().size();

        model.addAttribute("pet", pet);
        model.addAttribute("profile", pet.getPetProfile());
        model.addAttribute("events", pet.getEvents());

        return "pets/pet-profile";

    }

}
