package com.group3.petcareorganizer.repository;


import com.group3.petcareorganizer.model.PetProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PetProfileRepository extends JpaRepository<PetProfile,Long> {

    PetProfile findByPetId(Long petId);
}
