package model;

import java.util.List;

// list of pinned locations in the application
public interface PinList {

    // EFFECTS: search for pins with the given tag and return list of pins, empty if none match
    List<Pin> searchTag(String tag);

    // EFFECTS: search for pins with the given location and return list of pins,empty if none match
    List<Pin> searchLocation(String location);

    // EFFECTS: search for a pin with given ID. return null if no match
    Pin searchID(String id);

    // MODIFIES: this
    // EFFECTS: adds pin to the list
    boolean addPin(Pin pin);


}
