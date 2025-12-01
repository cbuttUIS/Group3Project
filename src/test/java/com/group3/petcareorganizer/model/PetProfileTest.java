package com.group3.petcareorganizer.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PetProfileTest {

    @Test
    void testAddAndGetConcern() {
        Pet pet = new Pet("Bella", 2020, 5, "Dog");
        PetProfile profile = new PetProfile(pet);

        profile.addConcern("Allergy");
        assertEquals("Allergy", profile.getConcern(0));
    }

    @Test
    void testAddAndGetEvent() {
        Pet pet = new Pet("Bella", 2020, 5, "Dog");
        PetProfile profile = new PetProfile(pet);

        Event event = new Event("Vet Visit", 9, 10, 1);
        profile.addEvent(event);

        assertEquals("Vet Visit", profile.getEvent(9).getEventName());
    }

    @Test
    void testDeleteEvent() {
        Pet pet = new Pet("Bella", 2020, 5, "Dog");
        PetProfile profile = new PetProfile(pet);

        Event event = new Event("Checkup", 12, 13, 3);
        profile.addEvent(event);
        profile.deleteEvent(12);

        assertNull(profile.getEvent(12));
    }
}
