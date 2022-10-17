package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PatientListTest {

    private PatientList testPatientList;
    private Patient patient1;
    private Patient patient2;
    private Patient patient3;

    @BeforeEach
    void runBefore() {
        testPatientList = new PatientList();
        patient1 = new Patient("Kia Abdullah", 19);
        patient2 = new Patient("Gurpal Chahal", 44);
        patient3 = new Patient("Amitoj Singh", 18);
    }

    @Test
    void testEmptyPatientList() {
        assertTrue(testPatientList.getPatients().isEmpty());
        assertEquals(0, testPatientList.getNumOfBeds());
    }

    @Test
    void testPatientListWithOneItem() {
        testPatientList.addPatient(patient1);
        assertTrue(testPatientList.getPatients().contains(patient1));
        assertEquals(1, testPatientList.getNumOfBeds());
    }

    @Test
    void testPatientListWithManyItem() {
        testPatientList.addPatient(patient1);
        testPatientList.addPatient(patient2);
        testPatientList.addPatient(patient3);
        assertTrue(testPatientList.getPatients().contains(patient1));
        assertTrue(testPatientList.getPatients().contains(patient2));
        assertTrue(testPatientList.getPatients().contains(patient3));
        assertEquals(3, testPatientList.getNumOfBeds());
    }

}
