package com.group3.petcareorganizer.controller;



import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.model.Pet;
import com.group3.petcareorganizer.repository.PetRepository;
import com.group3.petcareorganizer.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;


/* @Controller annotates that this controller class will handle requests from users about the Pet class
 */
@Controller
public class PetController {

    /*
      petRepository is for interacting with pet data for this owner's account
     */
    private final PetRepository petRepository;

    /*
     ownerService is to use methods for accessing the owner
     */
    private final OwnerService ownerService;

    /*
     PetController constructor assign  petRepository and ownerService to this controller so that it can use them
     */
    public PetController(PetRepository petRepository, OwnerService ownerService) {
        this.petRepository = petRepository;
        this.ownerService = ownerService;
    }

    /* @GetMapping connects to and says that /pets/add is the url that handles requests for adding a pet to the owner
        model
     */
    @GetMapping("/pets/add")
    public String addPet(Model model){

        // Calling the Pet class constructor to create a new empty pet object
        model.addAttribute("pet", new Pet());

        // Returns the name/url  to add a pet (pets/add.html) for viewing purposes
        return "pets/add";
    }

    /* @GetMapping connects to and says that the /pets/{id} url handles requests to an individual pet profile
        {id} a variable for the url so that it can handle any pet ID
        @PathVariable takes the {id} from the url
     */
    @GetMapping("/pets/{id}")
    public String petProfile(@PathVariable Long id, Model model){
        // fetching the pet from the petRepository with id from the url parameter
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pet ID: " + id));

        // Adding the pet to the model
        model.addAttribute("pet", pet);

        // Returns the name/url to a pet's profile (pets/id.html) for viewing
        return "pets/id";
    }

    /* @GetMapping connects to and says that the /pets url is to handle requests about pets created by the owner,
        the point is to create a list to access the pets
     */
    @GetMapping("/pets")
    public String pets(Model model){
        // Fetching owner of the pets
        Owner owner = ownerService.getCurrentOwner();

        // Using the owner to look up the pets in petRepository using ownerId
        List<Pet> pets = petRepository.findByOwnerId(owner.getId());

        // Adding the pets to the model
        model.addAttribute("pets", pets);

        // Returns the name/url to a list of the pets (pets/pets.html) created by the owner
        return "pets/pets";
    }

    /* @GetMapping uses /api/pets to get the owner's pets
        @ResponseBody returns the pets in JSON format (for javascript purposes)
     */
    @GetMapping("/api/pets")
    @ResponseBody
    public List<Pet> getPets(){

        // fetching the owner of the pets
        Owner owner = ownerService.getCurrentOwner();

        // Returns the pets from petRepository the owner's id
        return petRepository.findByOwnerId(owner.getId());
    }

    /* @PostMapping annotation is for saving information added to the model
        Saves the new pet from the Add Pet form
     */
    @PostMapping("/pets/add")
    public String savePet(@ModelAttribute("pet") Pet pet){
        // fetch the Pet Owner
        Owner owner = ownerService.getCurrentOwner();

        //set the Pet Owner
        pet.setOwner(owner);

        // save the pet in the database
        petRepository.save(pet);

        // Redirect to the list (/pets.html) after the pet is saved
        return "redirect:/pets";

    }
}
