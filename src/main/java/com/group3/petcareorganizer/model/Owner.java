package com.group3.petcareorganizer.model;

import java.util.ArrayList;
import java.util.List;

public class Owner {

    private String ownerUsername;
    private String ownerPassword;
    private List<Pet> pets;

    public Owner() {
        pets = new ArrayList<>();
    }

    public Owner(String username, String password) {
        this.ownerUsername = username;
        this.ownerPassword = password;
        this.pets = new ArrayList<>();
    }

    public void setOwnerID(String id) {
        this.ownerUsername = id;
    }

    public void setPassword(String pass) {
        this.ownerPassword = pass;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void addPet(Pet p) {
        pets.add(p);
    }

    public List<Pet> getPets() {
        return pets;
    }
}
