package model;

import org.json.JSONObject;

import java.util.Objects;
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

        EventLog.getInstance().logEvent(new Event("new water fountain created"));
    }

    // REQUIRES: input must be valid status;  "Broken" or "working"
    // MODIFIES: this
    // EFFECTS: change the status of a pin
    @Override
    public void setStatus(String status) {
        this.status = status;
        EventLog.getInstance().logEvent(new Event("fountain status edited"));
    }


    // MODIFIES: this
    // EFFECTS: adds direction to the given fountain
    @Override
    public void setDirection(String directions) {
        this.directions = directions;
        EventLog.getInstance().logEvent(new Event("fountain direction edited"));
    }

    // MODIFIES: this
    // EFFECTS: changes the pin's location to the input
    @Override
    public void setLocation(String location) {
        this.location = location;
        EventLog.getInstance().logEvent(new Event("fountain location edited"));
    }

    // MODIFIES: this
    // EFFECTS: changes the pin's tag to the input
    @Override
    public void setTag(String tag) {
        this.tag = tag;
        EventLog.getInstance().logEvent(new Event("fountain tag edited"));


    }

    // EFFECTS: writes waterFountain as a Json object with its attributes
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("location", location);
        json.put("tag", tag);
        json.put("status", status);
        json.put("direction", directions);
        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WaterFountain that = (WaterFountain) o;
        return Objects.equals(tag, that.tag) && Objects.equals(status, that.status)
                && Objects.equals(location, that.location) && Objects.equals(directions, that.directions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag, status, location, directions);
    }

    // EFFECTS: writes the attributes of waterFountain as a string separated by colons
    @Override
    public String toString() {
        return  location + ": " + tag + ": " + status + ": " + directions;
    }

    // EFFECTS: return true if the pin has the status "broken"
    public boolean isBroken() {
        return (status.equals("Broken"));
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
