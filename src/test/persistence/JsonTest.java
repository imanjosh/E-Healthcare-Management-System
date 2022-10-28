package persistence;

import model.Patient;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Code from JsonSerializationDemo was used to help create this class
public class JsonTest {
    protected void checkPatient(String name, Integer age, String diagnosis, String prescription, Patient patient) {
        assertEquals(name, patient.getName());
        assertEquals(age, patient.getAge());
        assertEquals(diagnosis, patient.getDiagnosis());
        assertEquals(prescription, patient.getPrescription());
    }
}
