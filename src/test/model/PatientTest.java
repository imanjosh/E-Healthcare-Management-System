package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {

    private Patient testPatient;

    @BeforeEach
    void runBefore() {
        testPatient = new Patient("Imanjosh Chahal", 19);
    }

    @Test
    void testConstructor() {
        assertEquals("Imanjosh Chahal", testPatient.getName());
        assertEquals(19, testPatient.getAge());
        assertNull(testPatient.getDiagnosis());
        assertNull(testPatient.getPrescription());
    }

    @Test
    void testAddDiagnosis() {
        testPatient.addDiagnosis("COVID-19");
        assertEquals("COVID-19", testPatient.getDiagnosis());
    }

    @Test
    void testAddPrescription() {
        testPatient.addPrescription("Nirmatrelvir and Ritonavir");
        assertEquals("Nirmatrelvir and Ritonavir", testPatient.getPrescription());
    }

}
