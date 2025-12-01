package com.group3.petcareorganizer.repository;

import com.group3.petcareorganizer.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/* @Repository means this interface is a Spring Data JPA it provides methods to interact with the Owner data in the
    database.
    Extending JpaRepository gives the CRUD operations to our interface
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    // findByUsername finds the owner with the owner's username and returns optional in case the username does not
    // exist in the database
    Optional<Owner> findByUsername(String username);


}
