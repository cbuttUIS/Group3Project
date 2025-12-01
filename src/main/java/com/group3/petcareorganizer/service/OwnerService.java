package com.group3.petcareorganizer.service;

import com.group3.petcareorganizer.model.Owner;
import com.group3.petcareorganizer.repository.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


/* @Service means this class is a Spring service, it handles owner operations and authentication
    @AllArgsConstructor is from Lombok to create constructor for each field in the class
   This class handles operations for the Owner class, it loads the user details for authentication and it provides
   methods to get the logged-in owner's information

 */
@Service
@AllArgsConstructor
public class OwnerService implements UserDetailsService {

    // ownerRepository is used to access the Owner's data in the database
    private final OwnerRepository ownerRepository;

    /* @Override because OwnerService is implementing the UserDetailsService interface and implementing
       loadUserByUsername()
       Loads a user by username if the user exists in the database
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Owner> user = ownerRepository.findByUsername(username);
        if (user.isPresent()) {

            var userObj = user.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
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
}
