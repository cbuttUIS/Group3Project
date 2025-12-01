package com.group3.petcareorganizer.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PetTest {

    @Test
    void testPetCreation() {
        Pet pet = new Pet("Bella", 2020, 5, "Dog");

        assertEquals("Bella", pet.getName());
        assertEquals(2020, pet.getBirthYear());
        assertEquals(5, pet.getBirthMonth());
        assertEquals("Dog", pet.getPetType());
    }

    @Test
    void testCalculateAge() {
        Pet pet = new Pet("Rocky", 2018, 8, "Cat");

        int age = pet.calculateAge(2025, 7);
        assertEquals(6, age);  // (2025 - 2018) = 7, but 7 < birth month 8 → age = 6
    }
}
