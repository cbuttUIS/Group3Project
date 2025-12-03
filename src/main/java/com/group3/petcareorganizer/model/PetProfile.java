package com.group3.petcareorganizer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
public class PetProfile {

    // @Id field means this is the primary key for this pet profile in the database
    //@Getter fetches the id
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@Lob tells the application that healthConcerns could hold large text
    @Getter
    @Setter
    @Lob
    private String healthConcerns;

    // one pet profile associated with one pet
    @OneToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    // one profile can have many events in a list
    @OneToMany(mappedBy = "petProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    //create an empty PetProfile object
    public PetProfile() {}

    // create a pet profile for the pet and bind it to the pet
    public PetProfile(Pet pet) {
        this.pet = pet;
        pet.setPetProfile(this);
    }

    /* addEvent to pet profile by setting relationship between the event and the pet profile
     */
    public void addEvent(Event event) {
        event.setPetProfile(this);
        // add this event the list of events on this pet profile
        events.add(event);
    }

    /* fetches the event from the events list using the event id
     */
    public Event getEventById(Long id){
        return events.stream()
                .filter(event -> event.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /* getEvent fetches the events for the pet profile
     */
    public List<Event> getEventList() {
        return events;
    }

    /* Removes an event from the list
     */
    public void deleteEvent(Event event) {
        events.remove(event);

        //finish deleting by setting the profile null
        event.setPetProfile(null);
    }

    /* Method to edit the health concern notes for this pet profile
     */
    public void editHealthConcernNotes(String noteEdits) {

        //update the health concern notes with the new edits
        this.healthConcerns = noteEdits;
    }


    /* getter to fetch the pet for this pet profile
     */
     public Pet getPet() {
        return pet;
    }

    /* this method is for setting the pet for this pet profile
     */
    public void setPet(Pet pet) {
        this.pet = pet;
    }

}
