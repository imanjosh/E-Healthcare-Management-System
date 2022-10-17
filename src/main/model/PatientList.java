package model;

import java.util.ArrayList;

// Represents a list of patients with a name, age, diagnosis (if available) & prescription (if available)
public class PatientList {

    private ArrayList<Patient> patients;
    private int numOfBeds;
    private static final int maxBeds = 500;

    /*
     * EFFECTS: creates a new empty list of Patient and sets number of beds taken to 0
     */
    public PatientList() {
        patients = new ArrayList<>();
        numOfBeds = 0;
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
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public int getNumOfBeds() {
        return numOfBeds;
    }
}
