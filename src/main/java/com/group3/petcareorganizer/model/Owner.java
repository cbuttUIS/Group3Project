package com.group3.petcareorganizer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/* @Entity means this class is the owner model for the database, and will be added to a database table

 */
@Entity
@Table(name = "owners")
public class Owner {

    // @Id means the id field is the primary key for the database table
    // @GeneratedValue creates a unique ID for each owner
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //name the column in the database with the username of the account
    @Column(unique = true)
    private String username;

    private String email;

    private String password;

    // @OneToMany means that one owner can have many pets in the database, and mappedby "owner" connects the
    // relationship to the owner field in the Pet class
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Pet> pets = new ArrayList<>();

    // Constructor for empty Owner object
    public Owner(){}

    // Constructor for an Owner object with data in the fields: username and password
    public Owner(String username, String password){
        this.username = username;
        this.password = password;
    }



    // Getters and Setters for each attribute
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /* getPets fetches the list of pets that belong to the Owner
     */
    public List<Pet> getPets() {

        //returns a list of pet objects
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    /* addPets adds a pet object to the Owner account,
        used in junit tests
     */
    public void addPet(Pet pet){
        //add the pet to the list of pets
        pets.add(pet);

        // sets the Owner of this pet
        pet.setOwner(this);
    }

    public void removePet(Pet pet){
        pets.remove(pet);
        pet.setOwner(null);

    }

}
