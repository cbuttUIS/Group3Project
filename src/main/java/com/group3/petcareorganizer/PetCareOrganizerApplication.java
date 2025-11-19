package com.group3.petcareorganizer;

import com.group3.petcareorganizer.service.UserService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class PetCareOrganizerApplication {

    public static void main(String[] args) {


        var context = SpringApplication.run(PetCareOrganizerApplication.class, args);


        UserService userService = context.getBean(UserService.class);

        int factorialOf5 = userService.factorial(5);
        System.out.println("Factorial of 5 is: " + factorialOf5);
    }
}
