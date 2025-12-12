package com.group3.petcareorganizer.service;

import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.model.Pet;
import com.group3.petcareorganizer.repository.OwnerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;



/* @Service means this class is a Spring service, it handles owner operations and authentication
   This class handles operations for the Owner class, it loads the user details for authentication and it provides
   methods to get the logged-in owner's information

 */
@Service
public class OwnerService implements UserDetailsService {

    // ownerRepository is used to access the Owner's data in the database
    private final OwnerRepository ownerRepository;

    //owner service constructor
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    /* @Override because OwnerService is implementing the UserDetailsService interface and implementing
       loadUserByUsername()
       Loads a user by username if the user exists in the database
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Owner owner = ownerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " + username));
        return org.springframework.security.core.userdetails.User.builder()
                .username(owner.getUsername())
                .password(owner.getPassword())
                .roles("USER")
                .build();
    }

    /* getCurrentOwner fetches the currently logged-on Owner, it uses SecurityContext to find the authenticated user
     */
    public Owner getCurrentOwner() {

        // get the authentication from spring security
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //fetch the authenticated username of the Owner
        String username = auth.getName();

        // look up the Owner in the database with username and return the Owner account info
        return ownerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

    }



    // add pet to the logged in owner
    public void addPetToOwner(Pet pet){
        Owner owner = getCurrentOwner();
        owner.addPet(pet);
        ownerRepository.save(owner);
    }

    //get the pets for the logged in owner
    public List<Pet> getOwnerPets(){
        return getCurrentOwner().getPets();
    }

    // fetch a specific pet with the pet ID for the current owner
    public Pet getPetById(Long petId){
        return getOwnerPets().stream()
                .filter(p -> p.getId().equals(petId))
                .findFirst()
                .orElse(null);
    }
}
