package model;

import java.util.ArrayList;
import java.util.List;

// a collection of pins the user has added to favourites
public class FavouritePins implements PinList {

    List<Pin> favPins;

    // EFFECT: constructs a list for favourites
    public FavouritePins() {
        favPins = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: if any pins are labelled "broken" or "unavailable" remove all of those pins and return true
    // return false otherwise
    public boolean removeAllUnavailable() {
        boolean containsUnavailable = false;
        List<Pin> notBrokenPins = new ArrayList<>();


        for (Pin pin : favPins) {
            String pinStatus = pin.getStatus();
            if (pinStatus.equals("Broken") || (pinStatus.equals("Unavailable"))) {
                containsUnavailable = true;

            } else {
                notBrokenPins.add(pin);

            }
        }
        favPins = notBrokenPins;
        return containsUnavailable;
    }

    public List<Pin> getFavPins() {
        return favPins;
    }

    // EFFECTS: return pins with matching tag, in no particular order
    @Override
    public List<Pin> searchTag(String tag) {
        String str = "Did not find any pin with such tag within favourite pins";
        List<Pin> pinsWithTag = new ArrayList<>();

        for (Pin pin : favPins) {
            String pinTag = pin.getTag();

            if (pinTag.equals(tag)) {
                pinsWithTag.add(pin);
                str = "found pin(s) with matching tag within favourite pins";
            }
        }
        EventLog.getInstance().logEvent(new Event(str));
        return pinsWithTag;
    }


    // EFFECTS: return pins with matching location, in no particular order
    // if no pins match the location, return empty list
    @Override
    public List<Pin> searchLocation(String location) {
        String str = "Did not find any pin with such location within list of favourite pins";

        List<Pin> pinsInLocation = new ArrayList<>();

        for (Pin pin : favPins) {
            String pinLocation = pin.getLocation();
            if (pinLocation.equals(location)) {
                pinsInLocation.add(pin);
                str = "found pin(s) with matching location within list of favourite pins";
            }
        }
        EventLog.getInstance().logEvent(new Event(str));
        return pinsInLocation;
    }

    // EFFECTS: return pin with matching id, null if none match
    @Override
    public Pin searchID(String id) {
        Pin matchingPin = null;
        String str = "Did not find any pin with that ID within list of all pins";

        for (Pin pin : favPins) {
            String pinID = pin.getId();
            if (pinID.equals(id)) {
                matchingPin = pin;
                str = "Found pin with matching ID within list of all pins";
            }
        }
        EventLog.getInstance().logEvent(new Event(str));
        return matchingPin;
    }

    // MODIFIES: this
    // EFFECTS: adds the list of pins to allPins
    public void addPins(List<Pin> pinList) {
        String str = "Successfully updated favourites to given list";
        favPins.clear();

        for (Pin pin : pinList) {
            addPin(pin);

        }
        EventLog.getInstance().logEvent(new Event(str));

    }

    // MODIFIES: this
    // EFFECTS: adds pin to favourites and return true if it does not exist already in the list
    // return false otherwise
    @Override
    public boolean addPin(Pin pin) {
        String str = "The pin already exists in the list of favourite pins";
        if (!favPins.contains(pin)) {
            favPins.add(pin);
            str = "Successfully added pin to favourites";
            return true;
        }
        EventLog.getInstance().logEvent(new Event(str));

        return false;
    }

    // MODIFIES: this
    // EFFECTS: removes pin from favourites and return true if it existed in the list
    // return false otherwise
    public boolean removePin(Pin pin) {
        String str = "Tried to remove non-existent pin from favourites";

        if (favPins.contains(pin)) {
            favPins.remove(pin);
            str = "successfully removed pin from favourites";
            return true;
        }

        EventLog.getInstance().logEvent(new Event(str));
        return false;
    }
}
