package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a list with a name of itself, and contains patients with a name, age, diagnosis (if available)
// & prescription (if available)
public class PatientList implements Writable {

    private List<Patient> patients;
    private String name;
    private int numOfBeds;
    private static final int maxBeds = 500;

    /*
     * EFFECTS: constructs PatientList with a name, empty list of patients, and # of beds set to 0
     */
    public PatientList(String name) {
        this.name = name;
        patients = new ArrayList<>();
        numOfBeds = 0;
    }


    public String getName() {
        return name;
    }

    /*
     * MODIFIES: this
     * EFFECTS: if number of beds taken is less than 500, then
     *                   adds the given patient to the list of patients;
     *                   adds 1 to the number of beds taken;
     */
    public void addPatient(Patient patient) {
        if (numOfBeds < maxBeds) {
            patients.add(patient);
            numOfBeds = numOfBeds + 1;
        }
        EventLog.getInstance().logEvent(new Event("Patient Added."));
    }

    // EFFECTS: returns an unmodifiable list of patients in this PatientList
    public List<Patient> getPatients() {
        return Collections.unmodifiableList(patients);
    }

    // EFFECTS: returns number of patients in this list
    public int numPatients() {
        return patients.size();
    }

    public int getNumOfBeds() {
        return numOfBeds;
    }

    // EFFECTS: removes patient in the list at the given index and decreases numOfBeds by 1
    public void removePatient(int index) {
        patients.remove(index);
        numOfBeds = numOfBeds - 1;
        EventLog.getInstance().logEvent(new Event("Patient Removed."));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("Patients", patientsToJson());
        return json;
    }

    // EFFECTS: returns things in this PatientList as a JSON array
    private JSONArray patientsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Patient t : patients) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

}
