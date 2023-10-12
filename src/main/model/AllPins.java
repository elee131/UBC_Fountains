package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllPins implements PinList {

    List<Pin> allPins;

    public AllPins() {
        allPins = new ArrayList<>();

    }

    // TODO
    // EFFECTS: return pins with matching tag, in no particular order
    @Override
    public List<Pin> searchTag(String tag) {
        List<Pin> pinsWithTag = new ArrayList<Pin>();

        for (Pin pin : allPins) {
            if (pin.getTag() == tag) {
                pinsWithTag.add(pin);
            }
        }
        return pinsWithTag;
    }


    // TODO
    // EFFECTS: return pin with matching location, in no particular order
    @Override
    public List<Pin> searchLocation(String location) {

        List<Pin> pinsInLocation = new ArrayList<Pin>();

        for (Pin pin : allPins) {
            if (pin.getLocation() == location) {
                pinsInLocation.add(pin);
            }
        }
        return pinsInLocation;
    }



    // MODIFIES: this
    // EFFECTS: adds pin to list and return true if it does not exist already in the list
    // return false otherwise
    @Override
    public boolean addPin(Pin pin) {
        if (!allPins.contains(pin)) {
            allPins.add(pin);
            return true;
        }
        return false;
    }



    // MODIFIES: this
    // EFFECTS: removes the given pin from the list and returns true.
    // If it does not exist, return false and do nothing
    public boolean removePin(Pin pin) {
        if (allPins.contains(pin)) {
            allPins.remove(pin);
            return true;
        }
        return false;
    }

    public List<Pin> getAllPins() {
        return allPins;
    }
}
