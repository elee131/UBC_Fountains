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

    // EFFECTS: constructs a map of UBC campus with a list of favourite pins and list of all pins
    public Map(String name) {
        this.name = name;
        allPins = new ArrayList<>();
        favPins = new ArrayList<>();
    }

    // EFFECTS: writes the map into a json file with a name, allPins, and favPins
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("favouritePins", favToJson());
        json.put("allPins", allToJson());
        return json;
    }

    // EFFECTS: adds the list of favourite pins into JSON file
    private JSONArray allToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pin p : allPins) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: adds the list of favourite pins into JSON file
    private JSONArray favToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pin p : favPins) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    public void addListOfPinToFav(List<Pin> pinList) {
        for (Pin pin : pinList) {
            addPinToFav(pin);
        }
    }

    public void addListOfPinToAll(List<Pin> pinList) {
        for (Pin pin : pinList) {
            addPinToAll(pin);
        }
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
