package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a patient with a name, age, diagnosis (if available) & prescription (if available)
public class Patient implements Writable {

    private String name;
    private int age;
    private String diagnosis;
    private String prescription;

    /*
     * REQUIRES: patientName has a non-zero length;
     *           patientAge >= 0
     * EFFECTS: name of patient is set to patientName;
     *          age of patient is set to patientAge;
     *          diagnosis is set to null;
     *          prescription is set to null
     */
    public Patient(String patientName, int patientAge) {
        name = patientName;
        age = patientAge;
        diagnosis = null;
        prescription = null;
    }

    /*
     * REQUIRES: diagnosis has a non-zero length;
     * MODIFIES: this
     * EFFECTS: diagnosis of patient is set to the given diagnosis;
     */
    public void addDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /*
     * REQUIRES: prescription has a non-zero length;
     * MODIFIES: this
     * EFFECTS: prescription of patient is set to the given prescription;
     */
    public void addPrescription(String prescription) {
        this.prescription = prescription;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getPrescription() {
        return prescription;
    }

    // EFFECTS: returns string representation of the patient
    public String toString() {
        return name + ": " + age;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("age", age);
        json.put("diagnosis", diagnosis);
        json.put("prescription", prescription);
        return json;
    }
}
