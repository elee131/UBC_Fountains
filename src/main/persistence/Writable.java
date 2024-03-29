package persistence;

import org.json.JSONObject;

// Code influenced by the JsonSerizalizationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// represents objects writable into Json
public interface Writable {

    // EFFECT: returns this as JSON object
    JSONObject toJson();


}
