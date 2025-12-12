package com.group3.petcareorganizer.repository;


import com.group3.petcareorganizer.model.PetProfile;
import org.springframework.data.jpa.repository.JpaRepository;


/*Extending JpaRepository gives the CRUD operations to our interface,give access to pet profile data
 */
public interface PetProfileRepository extends JpaRepository<PetProfile,Long> {

    PetProfile findByPetId(Long petId);
}
