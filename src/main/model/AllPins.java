package model;

import java.util.ArrayList;
import java.util.List;

// a collection of pins added to the application
public class AllPins implements PinList {

    List<Pin> allPins;

    public AllPins() {
        allPins = new ArrayList<>();

    }

    // EFFECTS: return pins with matching tag, in no particular order
    @Override
    public List<Pin> searchTag(String tag) {
        List<Pin> pinsWithTag = new ArrayList<Pin>();

        for (Pin pin : allPins) {
            String pinTag = pin.getTag();

            if (pinTag.equals(tag)) {
                pinsWithTag.add(pin);
            }
        }
        return pinsWithTag;
    }


    // EFFECTS: return pins with matching location, in no particular order
    @Override
    public List<Pin> searchLocation(String location) {

        List<Pin> pinsInLocation = new ArrayList<Pin>();

        for (Pin pin : allPins) {
            String pinLocation = pin.getLocation();
            if (pinLocation.equals(location)) {
                pinsInLocation.add(pin);
            }
        }
        return pinsInLocation;
    }

    // EFFECTS: return pin with matching id, null if none match
    @Override
    public Pin searchID(String id) {
        Pin matchingPin = null;
        for (Pin pin : allPins) {
            String pinID = pin.getId();
            if (pinID.equals(id)) {
                matchingPin = pin;
            }
        }
        return matchingPin;
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

    // MODIFIES: this
    // EFFECTS: if any pins are labelled "broken" or "unavailable" remove all of those pins and return true
    // return false otherwise
    public boolean removeAllUnavailable() {
        boolean containsUnavailable = false;
        List<Pin> brokenPins = new ArrayList<>();

        for (Pin pin : allPins) {
            if ((pin.getStatus() == "Broken") || (pin.getStatus() == "Unavailable")) {
                containsUnavailable = true;
                brokenPins.add(pin);
            }
        }
        for (Pin pin : brokenPins) {
            if (allPins.contains(pin)) {
                allPins.remove(pin);
            }
        }
        return containsUnavailable;
    }

    public List<Pin> getAllPins() {
        return allPins;
    }
}
