package com.group3.petcareorganizer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Entity
public class PetProfile {

    // @Id field means this is the primary key for this pet profile in the database
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@Lob tells the application that healthConcerns could hold large text
    @Lob
    @Column(columnDefinition = "TEXT")
    private String healthConcerns;

    // one pet profile associated with one pet
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;


    //create an empty PetProfile object
    public PetProfile() {}

    // create a pet profile for the pet and bind it to the pet
    public PetProfile(Pet pet) {
        setPet(pet);

    }



    /* fetches the event from the events list using the event id
     */
    public Long getId() {
        return id;
    }


    public String getHealthConcerns() {
        return healthConcerns;
    }

    public void setHealthConcerns(String healthConcerns) {
        this.healthConcerns = healthConcerns;
    }

    /* Method to edit the health concern notes for this pet profile
     */
    public void editHealthConcernNotes(String updatedText) {
        this.healthConcerns = updatedText;
    }


    /* getter to fetch the pet for this pet profile
     */
     public Pet getPet() {
        return pet;
    }

    /* this method is for setting the pet for this pet profile
     */
    public void setPet(Pet pet) {
        this.pet = pet;
        if (pet != null && pet.getPetProfile() != this) {
            pet.setPetProfile(this);
        }
    }

}
