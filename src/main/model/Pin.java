package model;

import org.json.JSONObject;
import persistance.Writable;

// represents pinned locations on the map
public interface Pin extends Writable {

    // REQUIRES: Status must be valid( one from broken, unavailable, working, available)
    // MODIFIES: this
    // EFFECTS: change status of a pin
    void setStatus(String status);

    // MODIFIES: this
    // EFFECTS: change directions of a pin
    void setDirection(String direction);

    // REQUIRES: location must be valid building code from ubc
    // MODIFIES: this
    // EFFECTS: change location of a pin
    void setLocation(String location);

    // MODIFIES: this
    // EFFECTS: change tag of a pin
    void setTag(String tag);

    // EFFECTS: returns string representation of this thingy
    String toString();

    String getTag();

    String getLocation();


    String getStatus();

    String getId();

    String getDirections();
}
