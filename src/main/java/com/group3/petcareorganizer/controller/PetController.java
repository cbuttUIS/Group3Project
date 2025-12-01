package com.group3.petcareorganizer.controller;



import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.model.Pet;
import com.group3.petcareorganizer.repository.PetRepository;
import com.group3.petcareorganizer.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;


/*
    @Controller annotates that this is a controller and will handle incoming requests from users, in this case,
    requests will be about Pet entities
 */

@Controller
public class PetController {


    private final PetRepository petRepository;
    private final OwnerService ownerService;

    public PetController(PetRepository petRepository, OwnerService ownerService) {
        this.petRepository = petRepository;
        this.ownerService = ownerService;
    }

    /* @GetMapping annotation connects the controller to the urls
        "/pets/add" is the url to use the addPet() method
     */
    @GetMapping("/pets/add")
    public String addPet(Model model){
        model.addAttribute("pet", new Pet());
        return "pets/add";
    }

    /* "/pets/{id}" is the url to use the petProfile() method to view a single pet profile
        {id} is a path variable for the url so that it can handle any pet ID
     */
    @GetMapping("/pets/{id}")
    public String petProfile(@PathVariable Long id, Model model){
        // this method is fetching the pet by id from the petRepository and adding it to model
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pet ID: " + id));
        model.addAttribute("pet", pet);
        return "pets/id";
    }

    /* "/pets" is the url to use the pets() method to view all pets that the owner has created
     */
    @GetMapping("/pets")
    public String pets(Model model){
        Owner owner = ownerService.getCurrentOwner();
        List<Pet> pets = petRepository.findByOwnerId(owner.getId());
        model.addAttribute("pets", pets);
        return "pets/pets";
    }

    @GetMapping("/api/pets")
    @ResponseBody
    public List<Pet> getPets(){
        Owner owner = ownerService.getCurrentOwner();
        return petRepository.findByOwnerId(owner.getId());
    }

    /* @PostMapping annotation saves the information added to the model/pet entity
     */
    @PostMapping("/pets/add")
    public String savePet(@ModelAttribute("pet") Pet pet){
        // fetch the Pet Owner
        Owner owner = ownerService.getCurrentOwner();

        //set the Pet Owner
        pet.setOwner(owner);

        // save the pet in the database
        petRepository.save(pet);

        //redirect to the list after the pet is saved
        return "redirect:/pets";

    }
}
