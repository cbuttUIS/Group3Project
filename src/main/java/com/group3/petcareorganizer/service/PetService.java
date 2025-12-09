package com.group3.petcareorganizer.service;

import com.group3.petcareorganizer.model.Event;
import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.model.Pet;
import com.group3.petcareorganizer.model.PetProfile;
import com.group3.petcareorganizer.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    //for accessijng pet info in the database
    private final PetRepository petRepository;
    //for accessing the owner services
    private final OwnerService ownerService;

    //pet service constructor
    public PetService(PetRepository petRepository,OwnerService ownerService) {
        this.petRepository = petRepository;
        this.ownerService = ownerService;
    }

    //add a pet for the logged in owner
    public Pet addPet(Pet pet) {
        Owner owner = ownerService.getCurrentOwner();
       //add the pet to the owner
        owner.addPet(pet);
        // set the owner to this pet
        pet.setOwner(owner);
        return petRepository.save(pet);
    }


    // get a list of all the owner's pets
    public List<Pet> getOwnerPets(){
        Owner owner = ownerService.getCurrentOwner();
        return owner.getPets();
    }

    //get a specific pet of the owner's
    public Pet getPetById(Long id){
        return getOwnerPets().stream().filter(pet -> pet.getId().equals(id)).findFirst().orElse(null);
    }

    // delete a pet of the owner's
    public void deletePet(Long id){
        Pet pet = getPetById(id);
        // check the pet exists to delete them
        if(pet != null){
            petRepository.delete(pet);
        }
    }

    // get the pets profile using the pet id
    public PetProfile getPetProfileById(Long id){
        Pet pet = getPetById(id);
        // if the pet exists return their profile
        if(pet != null){
            return pet.getPetProfile();
        }
        // if the pet doesnt exist, their pet profile does not either
        return null;
    }

    // add an event to the pet's events and save the info to the databse if the pet exists
    public Event addEvent(Long id, Event event){
        Pet pet =  getPetById(id);
        if(pet != null){
            event.setPet(pet);
            pet.getEvents().add(event);
            petRepository.save(pet);
            return event;
        }
        return null;
    }

    // get all the events for this pet unless they are null because the pet doesnt exist
    public List<Event> getEvents(Long id){
        Pet pet = getPetById(id);
        if(pet != null){
            return pet.getEvents();
        }
        return null;
    }


    /* using id get the pet, if they are not null, and delete the event from the pet using eventId
        save changes to the pet
     */
    public void deleteEvent(Long id, Long eventId){
        Pet pet = getPetById(id);
        if(pet != null){
            pet.getEvents().removeIf(event -> event.getId().equals(eventId));
            petRepository.save(pet);
        }
    }



}
