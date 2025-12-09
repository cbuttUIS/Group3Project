package com.group3.petcareorganizer.repository;


import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/*
    Extending JpaRepository gives the CRUD operations to our interface
 */
public interface PetRepository extends JpaRepository<Pet, Long> {

    // findByPetName finds a pet by its name and returns an Optional in case the pet name does not exist in the
    // database
    Optional<Pet> findByPetName(String name);

    // findByOwnerId uses the owner's id to finds all the pets that belong to an owner and returns the pets as a list
    List<Pet> findByOwnerId(Long ownerId);

    List<Pet> findByOwner(Owner owner);

    Optional<Pet> findById(Long id);
}
