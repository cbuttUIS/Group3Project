package com.group3.petcareorganizer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "events")
public class Event {

    // generate an id for the Event
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //name/description of the event
    private String eventName;

    // start time/date of the event
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime eventStartTime;

    // end time/date of the event
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime eventEndTime;

    // boolean to tag if this is a repeating event
    private boolean eventRepeat;
    private boolean eventBooked;
    private boolean eventEnded;


    @ManyToOne
    @JoinColumn(name="owner_id")
    @JsonIgnore
    private Owner owner;

    // there can be many events to one pet profile
    @ManyToOne
    @JoinColumn(name = "pet_id")
    @JsonIgnore
    private Pet pet;

    //constructor for an empty Event object
    public Event() {}

    // constructor for an Event object that has a name, start time, and end time
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        this.eventName = name;
        this.eventStartTime = startTime;
        this.eventEndTime = endTime;
        this.eventBooked = false;
        this.eventEnded = false;
        this.eventRepeat = false;
    }



    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEventName(String name) {

        this.eventName = name;
    }

    public String getEventName() {
        return eventName;
    }


    public LocalDateTime getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(LocalDateTime eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public LocalDateTime getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(LocalDateTime eventEndTime) {
        this.eventEndTime = eventEndTime;
    }


    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet){
        this.pet = pet;
    }

    public void toggleRepeat() {
        this.eventRepeat = !this.eventRepeat;
    }

    public void endEvent() {
        this.eventEnded = true;
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


}
