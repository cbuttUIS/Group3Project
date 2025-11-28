package com.group3.petcareorganizer.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PetProfile {

    private List<String> healthConcerns;
    private HashMap<Integer, Event> events;
    private Pet pet;

    public PetProfile(Pet pet) {
        this.pet = pet;
        this.healthConcerns = new ArrayList<>();
        this.events = new HashMap<>();
    }

    public void addConcern(String concern) {
        healthConcerns.add(concern);
    }

    public void addEvent(Event event) {
        events.put(event.getStartTime(), event);
    }

    public String getConcern(int index) {
        if (index < 0 || index >= healthConcerns.size()) return null;
        return healthConcerns.get(index);
    }

    public Event getEvent(int eventId) {
        return events.get(eventId);
    }

    public void deleteEvent(int eventId) {
        events.remove(eventId);
    }

    public Pet getPet() {
        return pet;
    }
}
