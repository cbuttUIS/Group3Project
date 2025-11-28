package com.group3.petcareorganizer.model;

public class Pet {

    private String petName;
    private int birthYear;
    private int birthMonth;
    private String petType;

    public Pet() {}

    public Pet(String petName, int birthYear, int birthMonth, String petType) {
        this.petName = petName;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.petType = petType;
    }

    public void setName(String name) {
        this.petName = name;
    }

    public void setBirthYear(int year) {
        this.birthYear = year;
    }

    public void setBirthMonth(int month) {
        this.birthMonth = month;
    }

    public void setPetType(String type) {
        this.petType = type;
    }

    public String getName() {
        return petName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public String getPetType() {
        return petType;
    }

    public int calculateAge(int currentYear, int currentMonth) {
        int years = currentYear - birthYear;
        if (currentMonth < birthMonth) {
            years--;
        }
        return years;
    }
}
