package com.group3.petcareorganizer.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PetTest {

    @Test
    void testPetCreation() {
        Pet pet = new Pet("Bella", 2020, 5, "Dog");

        assertEquals("Bella", pet.getPetName());
        assertEquals(2020, pet.getBirthYear());
        assertEquals(5, pet.getBirthMonth());
        assertEquals("Dog", pet.getPetType());
    }

    @Test
    void testCalculateAge() {
        Pet pet = new Pet("Rocky", 2018, 8, "Cat");

        int currentYear = java.time.LocalDate.now().getYear();
        int expectedAge = currentYear - 2018; // ignores month for simplicity

        int age = pet.getAge();
        assertEquals(expectedAge, age);
    }
}
