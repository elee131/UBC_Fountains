package persistence;

import model.Map;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Pin;
import model.UserPin;
import model.WaterFountain;
import org.json.*;

// Code influenced by the JsonSerizalizationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads map from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads map from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Map read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMap(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses map from JSON object and returns it
    private Map parseMap(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Map map = new Map(name);
        addFavourites(map, jsonObject);
        addAll(map, jsonObject);
        return map;
    }

    // MODIFIES: map
    // EFFECTS: parses favPins list and adds it to map
    private void addFavourites(Map map, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("favouritePins");
        for (Object json : jsonArray) {
            JSONObject nextPin = (JSONObject) json;
            addPinToFav(map, nextPin);
        }

    }

    // MODIFIES: map
    // EFFECTS: parses favPins list and adds it to map
    private void addAll(Map map, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("allPins");
        for (Object json : jsonArray) {
            JSONObject nextPin = (JSONObject) json;
            addPinToAll(map, nextPin);
        }
    }

    // MODIFIES: map
    // EFFECTS: parses through allPins from JSON object and adds it to map
    private void addPinToAll(Map map, JSONObject jsonObject) {
        Pin pin;
        String location = jsonObject.getString("location");
        String tag = jsonObject.getString("tag");
        String status = jsonObject.getString("status");
        String directions = jsonObject.getString("direction");

        if (tag.equals("Water Fountain")) {
            pin = new WaterFountain(location);
        } else {
            pin = new UserPin(location, tag);
        }
        pin.setStatus(status);
        pin.setDirection(directions);
        map.addPinToAll(pin);
    }

    // MODIFIES: map
    // EFFECTS: parses through favPins from JSON object and adds it to map

    private void addPinToFav(Map map, JSONObject jsonObject) {
        Pin pin;
        String location = jsonObject.getString("location");
        String tag = jsonObject.getString("tag");
        String status = jsonObject.getString("status");
        String directions = jsonObject.getString("direction");

        if (tag.equals("Water Fountain")) {
            pin = new WaterFountain(location);
        } else {
            pin = new UserPin(location, tag);
        }
        pin.setStatus(status);
        pin.setDirection(directions);
        map.addPinToFav(pin);
    }
}
