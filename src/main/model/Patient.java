package model;

// Represents a patient with a name, age, diagnosis (if available) & prescription (if available)
public class Patient {

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
}
