package com.group3.petcareorganizer.repository;

import com.group3.petcareorganizer.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/*
    Extending JpaRepository gives the CRUD operations to our interface
 */
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    // findByUsername finds the owner with the owner's username and returns optional in case the username does not
    // exist in the database
    Optional<Owner> findByUsername(String username);


    //used for signup validation
    boolean existsByUsername(String username);


}
