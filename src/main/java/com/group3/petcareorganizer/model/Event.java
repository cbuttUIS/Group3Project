package com.group3.petcareorganizer.model;

public class Event {

    private String eventName;
    private int eventStartTime;
    private int eventEndTime;
    private boolean eventRepeat;
    private boolean eventBooked;
    private int eventId;
    private boolean eventEnded;

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
}
