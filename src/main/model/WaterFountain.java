package model;

import java.util.UUID;

public class WaterFountain implements Pin {

    private String tag;
    private String status;
    private String location; // building codes
    private String directions;
    private String id;

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


    // TODO
    // MODIFIES: this
    // EFFECTS: adds direction to the given fountain

    @Override
    public void setDirection(String directions) {
        this.directions = directions;
    }

    @Override
    public void setLocation(String location) {
        this.location = location;
    }

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
