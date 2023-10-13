package model;

import java.util.ArrayList;
import java.util.List;

// a collection of pins the user has added to favourites
public class FavouritePins implements PinList {

    List<Pin> favPins;


    public FavouritePins() {
        favPins = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: if any pins are labelled "broken" or "unavailable" remove all of those pins and return true
    // return false otherwise
    public boolean removeAllUnavailable() {
        boolean containsUnavailable = false;
        List<Pin> brokenPins = new ArrayList<>();

        for (Pin pin : favPins) {
            if ((pin.getStatus() == "Broken") || (pin.getStatus() == "Unavailable")) {
                containsUnavailable = true;
                brokenPins.add(pin);
            }
        }
        for (Pin pin : brokenPins) {
            if (favPins.contains(pin)) {
                favPins.remove(pin);
            }
        }
        return containsUnavailable;
    }

    public List<Pin> getFavPins() {
        return favPins;
    }

    // EFFECTS: return pins with matching tag, in no particular order
    @Override
    public List<Pin> searchTag(String tag) {
        List<Pin> pinsWithTag = new ArrayList<Pin>();

        for (Pin pin : favPins) {
            String pinTag = pin.getTag();

            if (pinTag.equals(tag)) {
                pinsWithTag.add(pin);
            }
        }
        return pinsWithTag;
    }


    // EFFECTS: return pins with matching location, in no particular order
    // if no pins match the location, return empty list
    @Override
    public List<Pin> searchLocation(String location) {

        List<Pin> pinsInLocation = new ArrayList<Pin>();

        for (Pin pin : favPins) {
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
        for (Pin pin : favPins) {
            String pinID = pin.getId();
            if (pinID.equals(id)) {
                matchingPin = pin;
            }
        }
        return matchingPin;
    }

    // MODIFIES: this
    // EFFECTS: adds pin to favourites
    @Override
    public boolean addPin(Pin pin) {
        if (!favPins.contains(pin)) {
            favPins.add(pin);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: removes pin from favourites
    public boolean removePin(Pin pin) {
        if (favPins.contains(pin)) {
            favPins.remove(pin);
            return true;
        }
        return false;
    }
}
