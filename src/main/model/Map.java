package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// a save state of map with favourites and list of all pins saved
public class Map implements Writable {

    String name;

    List<Pin> allPins;

    List<Pin> favPins;

    List<Point> pointList;

    // EFFECTS: constructs a map of UBC campus with a list of favourite pins and list of all pins
    public Map(String name) {
        this.name = name;
        allPins = new ArrayList<>();
        favPins = new ArrayList<>();
        pointList = new ArrayList<>();

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

    // EFFECTS: adds the list of all pins into JSON file
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

    // EFFECTS: adds the list of favourite pins into JSON file
    private JSONArray pointsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Point p : pointList) {
            jsonArray.put(p.toString());
        }

        return jsonArray;
    }


    // MODIFIES: this
    // EFFECTS: changes favPins to match the given list
    public void updateFavToList(List<Pin> pinList) {
        favPins.clear();

        for (Pin pin : pinList) {
            addPinToFav(pin);
        }
    }

    // MODIFIES: this
    // EFFECTS: changes allPins to match the given list
    public void updateAllToList(List<Pin> pinList) {
        allPins.clear();

        for (Pin pin : pinList) {
            addPinToAll(pin);
        }
    }

    public void updatePointList(List<Point> pointList) {
        pointList.clear();

        this.pointList = pointList;
    }

    // MODIFIES: this
    // EFFECTS: adds pin to favPins
    public void addPinToFav(Pin pin) {
        favPins.add(pin);
    }

    // MODIFIES: this
    // EFFECTS: adds pin to allPins
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

    // EFFECTS: returns an unmodifiable list of all points
    public List<Point> getAllPoints() {
        return Collections.unmodifiableList(pointList);
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
