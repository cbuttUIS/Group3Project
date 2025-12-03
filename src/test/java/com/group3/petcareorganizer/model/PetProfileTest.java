package com.group3.petcareorganizer.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PetProfileTest {

    @Test
    void testEditHealthConcerns() {
        Pet pet = new Pet("Bella", 2020, 5, "Dog");
        PetProfile profile = new PetProfile(pet);

        profile.setHealthConcerns("Allergy");
        assertEquals("Allergy", profile.getHealthConcerns());
    }

    @Test
    void testAddAndGetEvent() {
        Pet pet = new Pet("Bella", 2020, 5, "Dog");
        PetProfile profile = new PetProfile(pet);

        Event event = new Event("Vet Visit", 9, 10, 1);
        profile.addEvent(event);

        assertEquals(1,  profile.getEventList().size());
        assertEquals("Vet Visit", profile.getEventList().get(0).getEventName());
        assertEquals(profile, event.getPetProfile());

    }

    @Test
    void testDeleteEvent() {
        Pet pet = new Pet("Bella", 2020, 5, "Dog");
        PetProfile profile = new PetProfile(pet);

        Event event = new Event("Checkup", 12, 13, 3);
        profile.addEvent(event);
        assertEquals(1, profile.getEventList().size());

        profile.deleteEvent(event);

        assertEquals(0, profile.getEventList().size());
    }
}
