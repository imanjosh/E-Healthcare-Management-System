package persistence;

import model.Patient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPatient(String name, Integer age, String diagnosis, String prescription, Patient patient) {
        assertEquals(name, patient.getName());
        assertEquals(age, patient.getAge());
        assertEquals(diagnosis, patient.getDiagnosis());
        assertEquals(prescription, patient.getPrescription());
    }
}
