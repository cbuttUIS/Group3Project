package com.group3.petcareorganizer.service;
import org.springframework.stereotype.Service;


// Annotation
@Service


// Class
public class UserService {

    // Method to compute factorial
    public int factorial(int n){

        //base case
        if(n==0){
            return 1;
        }
        return n * factorial(n-1);

    }
}
