package com.group3.petcareorganizer.controller;

import com.group3.petcareorganizer.model.Event;
import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.model.Pet;
import com.group3.petcareorganizer.repository.EventRepository;
import com.group3.petcareorganizer.repository.PetRepository;
import com.group3.petcareorganizer.service.OwnerService;
import com.group3.petcareorganizer.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* @RestController means that this class handles API requests and returns information in JSON format (for javascript
    purposes)
 */
@RestController
public class DashboardRestController {

    // to use owner services
    private final OwnerService ownerService;

    // for using pet data in the database
    private final PetRepository petRepository;

    // fo using event data in the database
    private final EventRepository eventRepository;

    // to use pet services
    private final PetService petService;

    /* constructor for dashboard rest controller
     */
    public DashboardRestController(OwnerService ownerService, PetRepository petRepository,
                                   EventRepository eventRepository, PetService petService) {
        this.ownerService =  ownerService;
        this.petRepository = petRepository;
        this.eventRepository = eventRepository;
        this.petService = petService;

    }

    /* create the owner dashboard
     */
    @GetMapping("/api/dashboard")
    public Map<String, Object> getDashboard() {
        Owner owner = ownerService.getCurrentOwner();

        List<Pet> pets = owner.getPets();

        // collect all events for all pets
        List<Event> events = pets.stream().flatMap(pet -> pet.getEvents().stream())
                .sorted(Comparator.comparing(Event::getEventStartTime))
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("pets", pets);
        response.put("events", events);

        return response;

    }


    /* Add an event to a specific
     */
    @PostMapping("/api/dashboard/pets/{id}/events")
    public Event addEvent(@PathVariable Long id, @RequestBody Event event){
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pet ID: " + id));

        event.setPet(pet);
        pet.getEvents().add(event);
        return eventRepository.save(event);
    }

    @DeleteMapping("/api/pets/{id}/events/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id,
                                            @PathVariable Long eventId) {
        petService.deleteEvent(id, eventId);
        return ResponseEntity.noContent().build();
    }


}
