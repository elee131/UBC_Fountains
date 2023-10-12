package model;

public class UserPin implements Pin {
    private String tag;
    private String status;
    private String location;
    private String directions;
    private int id;


    public UserPin(String location, String tag) {

        this.location = location;
        this.tag = tag;
        status = "Working";
        directions = "";


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

    // EFFECTS: return true if status of userPin is "Broken", false otherwise
    public boolean isBroken() {
        return (status == "Broken");
    }

    // EFFECTS: return true if status of userPin is "Unavailable", false otherwise
    public boolean isUnavailable() {
        return (status == "Unavailable");
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
}

