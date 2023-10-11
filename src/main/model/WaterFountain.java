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

    // TODO
    // REQUIRES: input must be valid status; from "Broken", unavailable, "working"
    // MODIFIES: this
    // EFFECTS: change the status of a pin
    @Override
    public void setStatus(String status) {

    }

    // TODO
    // MODIFIES: favourite
    // EFFECTS: add list to favourites
    @Override
    public void addToFavourite() {

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
