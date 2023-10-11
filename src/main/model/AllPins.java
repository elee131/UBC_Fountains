package model;

import java.util.ArrayList;
import java.util.List;

public class AllPins implements PinList {

    List<Pin> allPins;

    public AllPins() {
        allPins = new ArrayList<>();
    }

    // TODO
    // EFFECTS: return pin with matching tag, sorted in alphabetical order
    @Override
    public List<Pin> searchTag(String str) {
        return null;
    }


    // TODO
    // EFFECTS: return pin with matching location
    @Override
    public List<Pin> searchLocation(String location) {
        return null;
    }


    // TODO
    // MODIFIES: this
    // EFFECTS: adds pin to list if it does not exist already in the list
    @Override
    public boolean addPin(Pin pin) {
        return false;
    }

    // TODO
    public int getSize() {
        return -1;
    }
}
