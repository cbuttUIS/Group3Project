package com.group3.petcareorganizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PetCareOrganizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetCareOrganizerApplication.class, args);
    }

}
