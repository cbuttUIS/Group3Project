package com.group3.petcareorganizer.service;

import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.model.Pet;
import com.group3.petcareorganizer.model.PetProfile;
import com.group3.petcareorganizer.repository.PetProfileRepository;
import com.group3.petcareorganizer.repository.PetRepository;
import org.springframework.stereotype.Service;

/* @Service means this class is a Spring service, it handles profile operations
    it loads the profile details and priovides methods for managing pet profile
 */
@Service
public class PetProfileService {

    //for accessing pet info in the database
    private final PetProfileRepository petProfileRepository;
    // access pet data
    private final PetRepository petRepository;
    //access owner services
    private final OwnerService ownerService;


    /*constructor for PetProfileService
     */
    public PetProfileService(PetProfileRepository petProfileRepository, OwnerService ownerService,
                             PetRepository petRepository) {
        this.petProfileRepository = petProfileRepository;
        this.ownerService = ownerService;
        this.petRepository = petRepository;
    }


    /* get the pet's profile with the pet id
     */
    public PetProfile getPetProfileById(Long id){
        Owner owner = ownerService.getCurrentOwner();
        return owner.getPets().stream().filter(p -> p.getId().equals(id)).map(Pet :: getPetProfile)
                .findFirst().orElse(null);
    }

    /* for editinng the health concerns field
     */
    public PetProfile updateHealthConcerns(Long petId, String healthConcerns) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid pet id " + petId));

        PetProfile profile = pet.getPetProfile();
        if (profile == null) {
            profile = new PetProfile();
            profile.setPet(pet);
        }

        profile.setHealthConcerns(healthConcerns);
        return petProfileRepository.save(profile);
    }

    /* creates a new profile for pet
     */
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
