package model;

public interface Pin {

    // REQUIRES: Status must be valid( one from broken, unavailable, working, available)
    // MODIFIES: this
    // EFFECTS: change status of a pin
    void setStatus(String status);

    void setDirection(String direction);

    void setLocation(String location);

    void setTag(String tag);


    String getTag();

    String getLocation();


    String getStatus();

    String getId();

    String getDirections();
}
