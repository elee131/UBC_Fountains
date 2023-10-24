package model;

import org.json.JSONObject;
import persistance.Writable;

import java.util.UUID;

// a user-created pin with a tag (a type of feature), status (working, available, broken, unavailable)
// and directions. A unique id is generated so that the user can select a pin from the console.
public class UserPin implements Pin {
    private String tag;
    private String status;
    private String location;
    private String directions;
    private final String id;


    // EFFECT: constructs a userPin with given tag and location. ID is randomly generated
    public UserPin(String location, String tag) {

        this.location = location;
        this.tag = tag;
        status = "Working";
        directions = "";
        id = UUID.randomUUID().toString();

    }

    // REQUIRES: status must be Working, Available, Broken, or Unavailable
    // MODIFIES: this
    // EFFECTS: changes status to the status in the input
    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    // MODIFIES: this
    // EFFECTS: changes the pin's direction to the input
    @Override
    public void setDirection(String direction) {
        this.directions = direction;
    }

    // MODIFIES: this
    // EFFECTS: changes the pin's location to the input
    @Override
    public void setLocation(String location) {
        this.location = location;
    }

    // MODIFIES: this
    // EFFECTS: changes the pin's location to the input
    @Override
    public void setTag(String tag) {
        this.tag = tag;

    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("tag", tag);
        json.put("status", status);
        json.put("location", location);
        json.put("direction", directions);
        json.put("id", id);
        return json;
    }

    // EFFECTS: return true if status of userPin is "Broken", false otherwise
    public boolean isBroken() {
        return (status.equals("Broken"));
    }

    // EFFECTS: return true if status of userPin is "Unavailable", false otherwise
    public boolean isUnavailable() {
        return (status.equals("Unavailable"));
    }

    @Override
    public String toString() {
        return tag + ": " + status + ": " + location + ": " + directions;
    }

    public String getTag() {
        return tag;
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

