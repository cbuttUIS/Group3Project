package com.group3.petcareorganizer.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;



/*
@Entity annotates that this is a model class
 */
@Entity
public class Pet {

    /* @Id marks this as the primary key of the entity created, primary key identifies the entity in the database
        @GeneratedValue and GeneratedType.Identity use the database's auto increment to generate the primary key
     */
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


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

    private int petAge;


    /* @Lob tells the database that this field can store large text
     */
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


    public Pet() {}

    /*
        calls calcAge()
     */
    public Pet(String petName, int birthYear, int birthMonth, String petType) {
        this.petName = petName;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.petType = petType;

    }

    public int getPetAge(){
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int currentMonth = today.getMonthValue();

        int years = currentYear - this.birthYear;
        if(currentMonth < this.birthMonth){
            years--;
        }

        return years;
    }


}
