package com.group3.petcareorganizer.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/* @Entity means this class is the pet model for the database, and will be added to a database
    model class that defines the attributes and methods of pet
 */
@Entity
@Table(name = "pets")
public class Pet {

    // @Id means that the id field is the primary key for this pet in the database
    //   @GeneratedValue used to create a unique value for each pet id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    // pet's name, birth year (YYYY), birth month (MM), and type
    private String petName;
    private int birthYear;
    private int birthMonth;
    private String petType;



    /* @ManyToOne tells the database that there can be many pets belonging to one Owner
     */
    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private Owner owner;


    //@OneToOne one pet to one pet profile
    @OneToOne (mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private PetProfile petProfile;

    // one pet can have many events
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Event> events = new ArrayList<>();


    /* Pet constructor for creating an empty pet object, used for Adding a new pet
     */
    public Pet() {}

    /* Pet constructor to return a Pet object with data in the attribute fields
     */
    public Pet(String petName, int birthYear, int birthMonth, String petType) {
        this.petName = petName;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.petType = petType;


    }

    /* getter for pet id
     */
    public Long getId() {
        return id;
    }

    /* setter for pet id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /* getter for pet name
     */
    public String getPetName(){
        return petName;
    }

    /* setter for pet name
     */
    public void setPetName(String name){
        this.petName = name;
    }

    /* getter for pet's birth year
     */
    public int getBirthYear() {
        return birthYear;
    }

    /* setter for this pet's birht yeat
     */
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    /* getter for pet's birth month
     */
    public int getBirthMonth() {
        return birthMonth;
    }

    /* setter for this pet's birth month
     */
    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    /* getter for pet type
     */
    public String getPetType() {
        return petType;
    }

    /* setter for pet's type of animal
     */
    public void setPetType(String petType) {
        this.petType = petType;
    }

    /* setter for this pet's owner
     */
    public void setOwner(Owner owner){
        this.owner = owner;
    }

    /* getOwner fetches the owner of this pet
     */
    public Owner getOwner(){
        return this.owner;
    }

    /* getter for pet's events
     */
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event){
        events.add(event);
        event.setPet(this);
    }

    /* getter for pet's profile
     */
    public PetProfile getPetProfile() {
        return this.petProfile;
    }



    /* setProfile connects pet profile and pet to each other
     */
    public void setPetProfile(PetProfile petProfile){
        this.petProfile = petProfile;

        // if the pet profile is not connected to the pet
        if(petProfile.getPet() != this){

            // connect the pet profile to the pet
            petProfile.setPet(this);
        }
    }

    /* getPetAge() fetches the current date with LocalDate.now(), and uses today to fetch the current year and
        current month to calculate the pets age in years with the pet's birthYear and birthMonth
     */
    @JsonProperty("age")
    public int getAge() {
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int currentMonth = today.getMonthValue();

        // calculate the difference between the currentYear and the pet's birthYear
        int years = currentYear - this.birthYear;

        // subtract 1 from the number of years if the currentMonth is less than the pet's birthMonth, meaning the pet's
        // birthday has not passed yet.
        if(currentMonth < this.birthMonth){
            years--;
        }

        // Return the pet's age in years
        return years;
    }

}
