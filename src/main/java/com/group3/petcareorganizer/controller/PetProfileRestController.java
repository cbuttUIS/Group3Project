package com.group3.petcareorganizer.controller;


import com.group3.petcareorganizer.model.Pet;
import com.group3.petcareorganizer.model.PetProfile;
import com.group3.petcareorganizer.repository.PetProfileRepository;
import com.group3.petcareorganizer.repository.PetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pet-profiles")
public class PetProfileRestController {

    // for interacting with the database
    private final PetRepository petRepository;

    // to use the pet's profile info from the database
    private final PetProfileRepository petProfileRepository;

    //constructor to create a rest controller: it responds to queries made to the database
    public PetProfileRestController(PetRepository petRepository, PetProfileRepository petProfileRepository) {
        this.petRepository = petRepository;
        this.petProfileRepository = petProfileRepository;
    }

    /* get profile for a pet using the pet id, including the pet info and the pet's events
     */
    @GetMapping("/{id}")
    public PetProfile getPetProfile(@PathVariable Long id) {

       // locate the pet in the database, or throw exception if pet doesn't exist
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pet id " +id));

        PetProfile petProfile = pet.getPetProfile();

        // if no profile exists, return an empty one
        if (petProfile == null) {
            petProfile = new PetProfile();
        }

        pet.getEvents().size();
        // return the profile with the pet and event info included
        return petProfile;

    }

    /* To update the health concerns of a pet, locate the pet with pet id
     */
    @PutMapping("/{id}/health-concerns")
    public ResponseEntity<PetProfile> updateHealthConcerns(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        String healthConcerns = requestBody.get("healthConcerns");

        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pet id " + id));

        PetProfile petProfile = pet.getPetProfile();
        petProfile.setHealthConcerns(healthConcerns);
        petProfileRepository.save(petProfile);

        return ResponseEntity.ok(petProfile);
    }

}
