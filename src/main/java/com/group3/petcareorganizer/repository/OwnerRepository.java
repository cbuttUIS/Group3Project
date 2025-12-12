package com.group3.petcareorganizer.repository;

import com.group3.petcareorganizer.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/*Extending JpaRepository gives the CRUD operations to our interface,give access to owner data
 */
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Optional<Owner> findByUsername(String username);

    boolean existsByUsername(String username);


}
