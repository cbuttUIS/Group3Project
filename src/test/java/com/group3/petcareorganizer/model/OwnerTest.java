package com.group3.petcareorganizer.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OwnerTest {

    @Test
    void testOwnerCreation() {
        Owner owner = new Owner("janki", "password123");

        assertEquals("janki", owner.getUsername());
    }

    @Test
    void testAddPet() {
        Owner owner = new Owner("janki", "pass");
        Pet pet = new Pet("Bella", 2020, 5, "Dog");

        owner.addPet(pet);

        assertEquals(1, owner.getPets().size());
        assertEquals("Bella", owner.getPets().get(0).getPetName());
    }
}
