package com.group3.petcareorganizer.controller;


import com.group3.petcareorganizer.model.Event;
import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.model.Pet;
import com.group3.petcareorganizer.repository.EventRepository;
import com.group3.petcareorganizer.repository.PetRepository;
import com.group3.petcareorganizer.service.OwnerService;
import com.group3.petcareorganizer.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    // to use event info in the dashboard
    private final EventRepository eventRepository;
    //to use pet services
    private final PetService petService;
    //to use the owner services
    private final OwnerService ownerService;

    @Autowired
    /* PetRestController constructor is used to give database access to this controller
     */
    public PetRestController(PetRepository petRepository, OwnerService ownerService, EventRepository eventRepository,
                             PetService petService) {
        this.petRepository = petRepository;
        this.ownerService = ownerService;
        this.eventRepository = eventRepository;
        this.petService = petService;
    }

    /*get all pets for the logged in owner
     */
    @GetMapping
    public List<Pet> getPets(){
        Owner owner = ownerService.getCurrentOwner();
        return owner.getPets();
    }

    /*to add a pet to the owner
    @RequestBody means the user is sending pet info to the controller
     */
    @PostMapping
    public Pet addPet(@RequestBody Pet pet){
        Owner owner = ownerService.getCurrentOwner();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        return petRepository.save(pet);
    }

    /* @GetMapping connects to and says that the /{id} url is to get the pet id from the url
       @PathVariable extracts the pet id from the url
     */
    @GetMapping("/{id}")
    public Pet getPet(@PathVariable Long id){

        return petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pet ID: " + id));
    }


    /* to add an event for the pet by finding the pet with pet id
     */
    @PostMapping("/{id}/events")
    public Event addEvent(@PathVariable Long id, @RequestBody Event event){
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pet ID: " + id));
        event.setPet(pet);
        pet.getEvents().add(event);
        return eventRepository.save(event);
    }

    // find the pet with the pet id and get all the pets events
    @GetMapping("/{id}/events")
    public List<Event> getEvents(@PathVariable Long id){
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pet ID: " + id));
        return pet.getEvents();
    }

    @DeleteMapping("/api/pets/{id}/events/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id,
                                            @PathVariable Long eventId) {
        petService.deleteEvent(id, eventId);
        return ResponseEntity.noContent().build();
    }


}
