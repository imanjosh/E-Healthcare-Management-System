package persistence;

import org.json.JSONObject;

// Code from JsonSerializationDemo was used to help create this class
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}