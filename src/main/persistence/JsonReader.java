package persistence;

import model.Patient;
import model.PatientList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PatientList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private PatientList parseWorkRoom(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        PatientList patientList = new PatientList(name);
        addPatients(patientList, jsonObject);
        return patientList;
    }

    // MODIFIES: patientList
    // EFFECTS: parses patients from JSON object and adds them to patientList
    private void addPatients(PatientList patientList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Patients");
        for (Object json : jsonArray) {
            JSONObject nextPatient = (JSONObject) json;
            addPatient(patientList, nextPatient);
        }
    }

    // MODIFIES: patientList
    // EFFECTS: parses patient from JSON object and adds it to patientList
    private void addPatient(PatientList patientList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        String diagnosis = jsonObject.getString("diagnosis");
        String prescription = jsonObject.getString("prescription");
        Patient patient = new Patient(name, age);
        patientList.addPatient(patient);
        patient.addDiagnosis(diagnosis);
        patient.addPrescription(prescription);
    }
}
