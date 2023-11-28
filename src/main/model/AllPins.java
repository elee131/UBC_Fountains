package model;

import java.util.ArrayList;
import java.util.List;

// a collection of pins added to the application
public class AllPins implements PinList {

    List<Pin> allPins;

    // EFFECT: constructs a list for favourites
    public AllPins() {
        allPins = new ArrayList<>();
    }

    // EFFECTS: return pins with matching tag, in no particular order
    @Override
    public List<Pin> searchTag(String tag) {
        String str = "Did not find any pin with such tag within list of all pins";
        List<Pin> pinsWithTag = new ArrayList<>();

        for (Pin pin : allPins) {
            String pinTag = pin.getTag();

            if (pinTag.equals(tag)) {
                pinsWithTag.add(pin);
                str = "found pin(s) with matching tag within list of all pins";
            }
        }
        EventLog.getInstance().logEvent(new Event(str));
        return pinsWithTag;
    }


    // EFFECTS: return pins with matching location, in no particular order
    @Override
    public List<Pin> searchLocation(String location) {
        String str = "Did not find any pin with such location within list of all pins";
        List<Pin> pinsInLocation = new ArrayList<>();

        for (Pin pin : allPins) {
            String pinLocation = pin.getLocation();
            if (pinLocation.equals(location)) {
                pinsInLocation.add(pin);
                str = "found pin(s) with matching location within list of all pins";
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
        for (Pin pin : allPins) {
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
    // EFFECTS: adds pin to list and return true if it does not exist already in the list
    // return false otherwise
    @Override
    public boolean addPin(Pin pin) {
        String str = "The pin already exists in the list of all pins";
        if (!allPins.contains(pin)) {
            allPins.add(pin);
            str = "Successfully added pin to all";
            return true;
        }
        EventLog.getInstance().logEvent(new Event(str));
        return false;
    }

    // MODIFIES: this
    // EFFECTS: adds the list of pins to allPins
    public void addPins(List<Pin> pinList) {
        allPins.clear();

        for (Pin pin : pinList) {
            addPin(pin);
        }
        EventLog.getInstance().logEvent(new Event("Updated list of pins"));
    }



    // MODIFIES: this
    // EFFECTS: removes the given pin from the list and returns true.
    // If it does not exist, return false and do nothing
    public boolean removePin(Pin pin) {
        String str = "Tried to remove non-existent pin from all";

        if (allPins.contains(pin)) {
            allPins.remove(pin);
            str = "successfully removed pin from all";
            return true;
        }
        EventLog.getInstance().logEvent(new Event(str));
        return false;
    }



    // MODIFIES: this
    // EFFECTS: if any pins are labelled "broken" or "unavailable" remove all of those pins and return true
    // return false otherwise
    public boolean removeAllUnavailable() {
        boolean containsUnavailable = false;
        List<Pin> notBrokenPins = new ArrayList<>();
        String str = "no pins are broken or unavailable";

        for (Pin pin : allPins) {
            String pinStatus = pin.getStatus();
            if (pinStatus.equals("Broken") || (pinStatus.equals("Unavailable"))) {
                containsUnavailable = true;
                str = "removed broken/unavailable pin(s)";
            } else {
                notBrokenPins.add(pin);

            }
        }
        EventLog.getInstance().logEvent(new Event(str));
        allPins = notBrokenPins;
        return containsUnavailable;
    }

    public List<Pin> getAllPins() {
        return allPins;
    }
}
