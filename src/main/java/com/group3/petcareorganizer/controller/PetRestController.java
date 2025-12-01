package com.group3.petcareorganizer.controller;


import com.group3.petcareorganizer.model.Pet;
import com.group3.petcareorganizer.repository.PetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pets")
public class PetRestController {

    private final PetRepository petRepository;

    public PetRestController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @GetMapping("/{id}")
    public Pet getPet(@PathVariable Long id){
            return petRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid pet ID: " + id));
        }
}
