package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// a save state of map with favourites and list of all pins saved
public class Map implements Writable {

    String name;

    List<Pin> allPins;

    List<Pin> favPins;


    public Map(String name) {
        this.name = name;
        allPins = new ArrayList<>();
        favPins = new ArrayList<>();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("favouritePins", favToJson());
        json.put("allPins", allToJson());
        return json;
    }

    private JSONArray allToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pin p : allPins) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    private JSONArray favToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pin p : favPins) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: adds thingy to this workroom
    public void addPinToFav(Pin pin) {
        favPins.add(pin);
    }

    // MODIFIES: this
    // EFFECTS: adds thingy to this workroom
    public void addPinToAll(Pin pin) {
        allPins.add(pin);
    }

    public String getName() {
        return name;
    }

    // EFFECTS: returns an unmodifiable favourites in the map
    public List<Pin> getFavPins() {
        return Collections.unmodifiableList(favPins);
    }

    // EFFECTS: returns an unmodifiable list of all pins
    public List<Pin> getAllPins() {
        return Collections.unmodifiableList(allPins);
    }


    // EFFECTS: returns number of items in favPins
    public int numFavs() {
        return favPins.size();
    }

    // EFFECTS: returns number of items in allPins
    public int numAll() {
        return allPins.size();
    }

}
