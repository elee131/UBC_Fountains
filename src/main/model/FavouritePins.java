package model;

import java.util.List;

public class FavouritePins implements PinList {

    // TODO
    public FavouritePins() {

    }

    // TODO
    // REQUIRES: tag must be a valid tag defined within the program
    // EFFECTS: returns a list of pins with matching tag
    @Override
    public List<Pin> searchTag(String tag) {
        return null;
    }


    // TODO
    // REQUIRES: location must be a valid building code within UBC
    // EFFECTS: returns a list of pins with matching location
    @Override
    public List<Pin> searchLocation(String location) {
        return null;
    }


    // TODO
    // EFFECTS: adds a pin to the list of favourite pins and return true. if it already exists in the list,
    // return false and do not add to the list
    @Override
    public boolean addPin(Pin pin) {
        return false;
    }

    // TODO
    // EFFECTS: removes the given pin from the list and returns true.
    // If it does not exist, return false and do not add to the list
    public boolean removePin(Pin pin) {
        return false;
    }

    // TODO
    public boolean removeAllBroken() {
        return false;
    }

    // TODO
    public List<Pin> searchAllOpen() {
        return null;
    }

}
