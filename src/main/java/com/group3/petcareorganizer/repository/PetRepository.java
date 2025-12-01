package com.group3.petcareorganizer.repository;


import com.group3.petcareorganizer.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {


    Optional<Pet> findByPetName(String name);
    List<Pet> findByOwnerId(Long ownerId);
}
