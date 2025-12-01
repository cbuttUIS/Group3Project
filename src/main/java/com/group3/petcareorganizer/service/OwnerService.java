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

@Service
@AllArgsConstructor
public class OwnerService implements UserDetailsService {


    private final OwnerRepository ownerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Owner> user = ownerRepository.findByUsername(username);
        if (user.isPresent()) {

            var userObj = user.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles("USER") // or authorities("ROLE_USER")
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }



    public Owner getCurrentOwner() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return ownerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

    }
}
