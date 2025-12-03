package com.group3.petcareorganizer.model;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;

@Entity
public class Event {

    // generate an id for the Event
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String eventName;
    private int eventStartTime;
    private int eventEndTime;
    private boolean eventRepeat;
    private boolean eventBooked;
    private int eventId;
    private boolean eventEnded;

    // there can be many events to one pet profile
    @ManyToOne
    @JoinColumn(name = "pet_profile_id")
    private PetProfile petProfile;

    public Event() {}

    public Event(String name, int startTime, int endTime, int eventId) {
        this.eventName = name;
        this.eventStartTime = startTime;
        this.eventEndTime = endTime;
        this.eventId = eventId;
        this.eventBooked = false;
        this.eventEnded = false;
        this.eventRepeat = false;
    }

    // ---- SETTERS ----
    public void setName(String name) {
        this.eventName = name;
    }

    public void setStartTime(int start) {
        this.eventStartTime = start;
    }

    public void setEndTime(int end) {
        this.eventEndTime = end;
    }

    public void toggleRepeat() {
        this.eventRepeat = !this.eventRepeat;
    }

    public void setEventId(int id) {
        this.eventId = id;
    }

    public void endEvent() {
        this.eventEnded = true;
    }

    // ---- GETTERS ----
    public String getEventName() {
        return eventName;
    }

    public int getStartTime() {
        return eventStartTime;
    }

    public int getEndTime() {
        return eventEndTime;
    }

    public int getEventId() {
        return eventId;
    }

    public boolean isBooked() {
        return eventBooked;
    }

    public boolean isRepeating() {
        return eventRepeat;
    }

    public boolean hasEnded() {
        return eventEnded;
    }

    public PetProfile getPetProfile() {
        return petProfile;
    }

    public void setPetProfile(PetProfile petProfile) {
        this.petProfile = petProfile;

        if(petProfile != null && petProfile.getEventList().contains(this)) {
            petProfile.getEventList().add(this);
        }

    }

    public Long getId() {
        return id;

    }
}
