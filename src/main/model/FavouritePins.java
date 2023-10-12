package model;

import java.util.ArrayList;
import java.util.List;

public class FavouritePins extends AllPins {

    List<Pin> favPins;

    // TODO
    public FavouritePins() {
        favPins = new ArrayList<>();
    }

    // TODO
    // MODIFIES: this
    // EFFECTS: if any pins are labelled "broken" or "unavailable" remove all of those pins and return true
    // return false otherwise
    public boolean removeAllUnavailable() {
        boolean containsUnavailable = false;
        for (Pin pin : favPins) {
            if ((pin.getStatus() == "Broken") || (pin.getStatus() == "Unavailable")) {
                favPins.remove(pin);
                containsUnavailable = true;
            }
        }
        return containsUnavailable;
    }

    public List<Pin> getFavPins() {
        return favPins;
    }
}
