package com.group3.petcareorganizer.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    void testEventCreation() {

        LocalDateTime start = LocalDateTime.of(2025,12,15,9,30);
        LocalDateTime end = LocalDateTime.of(2025,12,15,10,45);

        Event event = new Event("Vet Visit", start, end);

        assertEquals("Vet Visit", event.getEventName());
        assertEquals(start, event.getEventStartTime());
        assertEquals(end, event.getEventEndTime());
        assertFalse(event.isBooked());
        assertFalse(event.isRepeating());
    }

    @Test
    void testToggleRepeat() {

        LocalDateTime start = LocalDateTime.of(2025,12,15,9,00);
        LocalDateTime end = LocalDateTime.of(2025,12,15,11,00);
        Event event = new Event("Checkup", start, end);

        boolean beforeToggle = event.isRepeating();
        // toggle repeat should not throw any errors
        event.toggleRepeat();
        boolean afterToggle = event.isRepeating();


        assertNotEquals(beforeToggle, afterToggle);
    }
}
