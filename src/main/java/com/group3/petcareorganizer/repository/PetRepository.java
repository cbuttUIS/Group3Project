package com.group3.petcareorganizer.repository;


import com.group3.petcareorganizer.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/* @Repository means this interface is a Spring Data JPA it provides methods to interact with the Pet data in the
    database.
    Extending JpaRepository gives the CRUD operations to our interface
 */
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    // findByPetName finds a pet by its name and returns an Optional in case the pet name does not exist in the
    // database
    Optional<Pet> findByPetName(String name);

    // findByOwnerId uses the owner's id to finds all the pets that belong to an owner and returns the pets as a list
    List<Pet> findByOwnerId(Long ownerId);
}
