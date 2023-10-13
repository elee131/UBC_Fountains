package model;

import java.util.UUID;

// a water fountain with a status (working/broken), location (UBC buliding code), and direction
// a random id is generated so that the user can select a pin from the console.
public class WaterFountain implements Pin {

    private String tag;
    private String status;
    private String location; // building codes
    private String directions;
    private final String id;

    // REQUIRES: location must be a valid building code (ICCS, CIRS, etc)
    public WaterFountain(String location) {
        this.location = location;
        status = "Working";
        tag = "Water Fountain";
        directions = "";
        id = UUID.randomUUID().toString();
    }

    // REQUIRES: input must be valid status;  "Broken" or "working"
    // MODIFIES: this
    // EFFECTS: change the status of a pin
    @Override
    public void setStatus(String status) {
        this.status = status;
    }


    // MODIFIES: this
    // EFFECTS: adds direction to the given fountain
    @Override
    public void setDirection(String directions) {
        this.directions = directions;
    }

    // MODIFIES: this
    // EFFECTS: changes the pin's location to the input
    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    // MODIFIES: this
    // EFFECTS: changes the pin's tag to the input
    @Override
    public void setTag(String tag) {
        this.tag = tag;

    }

    public boolean isBroken() {
        return (status.equals("Broken"));
    }

    public String getTag() {
        return this.tag;
    }

    public String getStatus() {
        return status;
    }

    public String getLocation() {
        return location;
    }

    public String getDirections() {
        return directions;
    }

    public String getId() {
        return id;
    }
}
