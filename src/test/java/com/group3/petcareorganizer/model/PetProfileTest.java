package com.group3.petcareorganizer.model;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class PetProfileTest {

    @Test
    void testEditHealthConcerns() {
        Pet pet = new Pet("Bella", 2020, 5, "Dog");
        PetProfile profile = new PetProfile();
        profile.setPet(pet);
        pet.setPetProfile(profile);

        profile.setHealthConcerns("Allergy");
        assertEquals("Allergy", profile.getHealthConcerns());
    }

    @Test
    void testAddAndGetEvent() {
        Pet pet = new Pet("Bella", 2020, 5, "Dog");
        PetProfile profile = new PetProfile(pet);

        LocalDateTime start = LocalDateTime.of(2025, 12, 15, 9, 0);
        LocalDateTime end = LocalDateTime.of(2025, 12, 15, 10, 0);
        Event event = new Event("Vet Visit", start, end);

        event.setPet(pet);
        pet.getEvents().add(event);

        List<Event> events = pet.getEvents();

        assertEquals(1, events.size());
        assertEquals("Vet Visit", events.get(0).getEventName());
        assertEquals(pet, events.get(0).getPet());

    }

    @Test
    void testDeleteEvent() {
        Pet pet = new Pet("Bella", 2020, 5, "Dog");
        PetProfile profile = new PetProfile(pet);
        profile.setPet(pet);
        pet.setPetProfile(profile);

        LocalDateTime start = LocalDateTime.of(2025, 12, 15, 12, 00);
        LocalDateTime end = LocalDateTime.of(2025, 12, 15, 13, 00);
        Event event = new Event("Checkup", start, end);

        event.setPet(pet);
        pet.getEvents().add(event);
        assertEquals(1, pet.getEvents().size());

        pet.getEvents().remove(event);
        assertEquals(0, pet.getEvents().size());
    }
}
