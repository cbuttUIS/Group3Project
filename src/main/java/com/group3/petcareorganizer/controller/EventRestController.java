package com.group3.petcareorganizer.controller;

import com.group3.petcareorganizer.model.Event;
import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.model.Pet;
import com.group3.petcareorganizer.repository.EventRepository;
import com.group3.petcareorganizer.repository.PetRepository;
import com.group3.petcareorganizer.service.OwnerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

/* @RestController means that this class handles API requests and returns information in JSON format (for javascript
    purposes)
   @RequestMapping is used to set the endpoints in this controller.
    The class is to handle information about the events
 */
@RestController
@RequestMapping("/api")
public class EventRestController {

    // to use event info from the database
    private final EventRepository eventRepository;

    // to use pet data in the database
    private final PetRepository petRepository;

    //to use owner services
    private final OwnerService ownerService;

    /* EventRestController constructor
     */
    public EventRestController(EventRepository eventRepository, PetRepository petRepository, OwnerService ownerService) {
        this.eventRepository = eventRepository;
        this.petRepository = petRepository;
        this.ownerService = ownerService;
    }


    /* to get all the events for a logged in owner
     */
    @GetMapping
    public List<Event> getEvents(){
        Owner owner = ownerService.getCurrentOwner();

        return owner.getPets().stream().flatMap(p -> p.getEvents().stream())
                .sorted((e1, e2) -> e1.getEventStartTime().compareTo(e2.getEventStartTime()))
                .collect(Collectors.toList());
    }

    /* get all events for one pet
     */
    @GetMapping("/pet/{id}")
    public List<Event> getEventsByPet(@PathVariable Long id){
        Pet pet = petRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ID; "+ id));

        return pet.getEvents().stream()
                .sorted((e1, e2) -> e1.getEventStartTime().compareTo(e2.getEventStartTime()))
                .collect(Collectors.toList());
    }

    /* to add an event for the pet
     */
    @PostMapping("/pet/{id}")
    public Event addEvent(@PathVariable Long id, @RequestBody Event event) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pet id: " + id));

        event.setPet(pet);
        pet.getEvents().add(event);
        return eventRepository.save(event);
    }

    /* get a single event using the event id
     */
    @GetMapping("/events/{eventId}")
    public Event getEvent(@PathVariable Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event id: " + eventId));
    }

    /* Delete an event using the event id to locate it
     */
    @DeleteMapping("/{eventId}")
    public String deleteEvent(@PathVariable Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event id: " + eventId));

        eventRepository.delete(event);
        return "Event deleted";
    }


}
