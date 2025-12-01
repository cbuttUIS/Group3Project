package com.group3.petcareorganizer.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;



/*
@Entity means this class is the Pet model for the database
This class uses Lombok Getters and Setters with the @Getter and @Setter annotations
 */
@Entity
public class Pet {

    // @Id means that the id field is the primary key for this pet in the database
    //   @GeneratedValue used to create a unique value for each pet id
    //@Getter to get id
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    // @Getter and @Setter to get and set petName, birthYear (YYYY), birthMonth (MM), and petType
    @Getter
    @Setter
    private String petName;
    @Getter
    @Setter
    private int birthYear;
    @Getter
    @Setter
    private int birthMonth;
    @Getter
    @Setter
    private String petType;

    // petAge field is going to hold the pet's age after it has been calculated with getPetAge() method in Pet class
    private int petAge;


    // @Getter and @Setter get and set healthConcerns
    // @Lob tells the database that this field can store large text
    @Getter
    @Setter
    @Lob
    private String healthConcerns;

    /* @ManToOne tells the database that there can be many of this entity belonging to another, in this case, Owner
        @JoinColumn tells the database to create a column owner_id to identify each Pet entity attached to Owner
     */
    @Setter
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    // Pet constructor for creating an empty pet object, used for Adding a new pet
    public Pet() {}

    /* Pet constructor to return a Pet object with data in the attribute fields
     */
    public Pet(String petName, int birthYear, int birthMonth, String petType) {
        this.petName = petName;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.petType = petType;

    }

    /* getPetAge() fetches the current date with LocalDate.now(), and uses today to fetch the current year and
        current month to calculate the pets age in years with the pet's birthYear and birthMonth
     */
    public int getPetAge(){
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int currentMonth = today.getMonthValue();

        // calculate the difference between the currentYear and the pet's birthYear
        int years = currentYear - this.birthYear;

        // subtract 1 from the number of years if the currentMonth is less than the pet's birthMonth, meaning the pet's
        // birthday has not passed yet.
        if(currentMonth < this.birthMonth){
            years--;
        }

        // Return the pet's age in years
        return years;
    }


}
