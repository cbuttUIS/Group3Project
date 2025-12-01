package com.group3.petcareorganizer.model;

import jakarta.persistence.*;

import java.util.List;

/* @Entity means this class is the owner model for the database, and will be added to a database table
   @Table creates a table in the database for the Owner's information
   The class is representing an Owner account. It will create a model that store the owner's username, password,
   email, and a list of the pets that belong to the owner.
 */
@Entity
@Table
public class Owner {

    // @Id means the id field is the primary key for the database table
    // @GeneratedValue creates a unique ID for each owner
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String username;
    private String email;
    private String password;

    // @OneToMany means that one owner can have many pets in the database, and mappedby "owner" connects the
    // relationship to the owner field in the Pet class
    @OneToMany(mappedBy = "owner")
    List<Pet> pets;


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

}
