package com.group3.petcareorganizer.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    void testEventCreation() {
        Event event = new Event("Vet Visit", 9, 10, 101);

        assertEquals("Vet Visit", event.getEventName());
        assertEquals(9, event.getStartTime());
        assertEquals(10, event.getEndTime());
        assertEquals(101, event.getEventId());
        assertFalse(event.isBooked());
    }

    @Test
    void testToggleRepeat() {
        Event event = new Event("Checkup", 9, 11, 202);

        // toggle repeat should not throw any errors
        event.toggleRepeat();

        // if you want to test the repeat flag in the future,
        // add a getter like:  event.isRepeating()
        // for now, just ensure nothing crashes:
        assertNotNull(event);
    }
}
