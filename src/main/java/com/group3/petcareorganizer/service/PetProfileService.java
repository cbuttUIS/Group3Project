package com.group3.petcareorganizer.service;

import com.group3.petcareorganizer.model.Event;
import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.model.Pet;
import com.group3.petcareorganizer.model.PetProfile;
import com.group3.petcareorganizer.repository.PetProfileRepository;
import com.group3.petcareorganizer.repository.PetRepository;
import org.springframework.stereotype.Service;

@Service
public class PetProfileService {

    //for accessing pet info in the database
    private final PetProfileRepository petProfileRepository;

    private final OwnerService ownerService;


    //constructor for PetProfileService
    public PetProfileService(PetProfileRepository petProfileRepository, OwnerService ownerService) {
        this.petProfileRepository = petProfileRepository;
        this.ownerService = ownerService;
    }


    // get the pet's profile with the pet id
    public PetProfile getPetProfileById(Long petId){
        Owner owner = ownerService.getCurrentOwner();
        return owner.getPets().stream().filter(p -> p.getId().equals(petId)).map(Pet :: getPetProfile)
                .findFirst().orElse(null);
    }

    // to edit the health concerns on the pet's profile
    public PetProfile editHealthConcerns(Long petId, String healthConcerns){
        PetProfile petProfile = getPetProfileById(petId);
        petProfile.editHealthConcernNotes(healthConcerns);
        return petProfileRepository.save(petProfile);
    }

    public PetProfile createPetProfile(Pet pet){
        if(pet.getPetProfile() == null){
            PetProfile petProfile = new PetProfile();
            petProfile.setPet(pet);
            petProfileRepository.save(petProfile);
            pet.setPetProfile(petProfile);
            return petProfile;
        }
        return pet.getPetProfile();
    }
}
