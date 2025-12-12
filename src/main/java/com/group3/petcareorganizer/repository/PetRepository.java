package com.group3.petcareorganizer.repository;


import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/*Extending JpaRepository gives the CRUD operations to our interface,give access to pet data
 */
public interface PetRepository extends JpaRepository<Pet, Long> {

    Optional<Pet> findByPetName(String name);

    List<Pet> findByOwnerId(Long ownerId);

    List<Pet> findByOwner(Owner owner);

    Optional<Pet> findById(Long id);
}
