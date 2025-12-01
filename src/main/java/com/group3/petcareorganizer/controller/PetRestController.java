package com.group3.petcareorganizer.controller;


import com.group3.petcareorganizer.model.Pet;
import com.group3.petcareorganizer.repository.PetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* @RestController means that this class handles API requests and returns information in JSON format (for javascript
    purposes)
   @RequestMapping is used to set the url for all endpoints in this controller. All routes start with /api/pets
    The class is to handle information about the pets
 */
@RestController
@RequestMapping("/api/pets")
public class PetRestController {

    //  petRepository is for interacting with pet data in the database
    private final PetRepository petRepository;

    // PetRestController constructor is used to give database access to this controller
    public PetRestController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /* @GetMapping connects to and says that the /{id} url is to get the pet id from the url
       @PathVariable extracts the pet id from the url so that the method can return it in JSON format
     */
    @GetMapping("/{id}")
    public Pet getPet(@PathVariable Long id){

            //return the pet from the database using the id from the url
            return petRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid pet ID: " + id));
        }
}
