package model;

import java.util.List;

public class WaterFountain implements Pin {

    private String tag;
    private String status;
    private String location; // building codes
    private String directions;

    private PinList allPins = new AllPins();


    // REQUIRES: location must be a valid building code (ICCS, CIRS, etc)
    public WaterFountain(String location) {
        this.location = location;
        status = "Working";
        tag = "Water Fountain";
        directions = "";
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

    public boolean isBroken() {
        return (status == "Broken");
    }

    public String getTag() {
        return this.tag;
    }

    public String getStatus() {
        return this.status;
    }

    public String getLocation() {
        return this.location;
    }

    public String getDirections() {
        return this.directions;
    }
}
