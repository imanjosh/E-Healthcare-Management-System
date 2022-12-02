package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class PatientListTest {

    private PatientList testPatientList;
    private Patient patient1;
    private Patient patient2;
    private Patient patient3;

    private EventLog el;
    private Iterator<Event> itr;

    @BeforeEach
    void runBefore() {
        testPatientList = new PatientList("List of saved Patients");
        patient1 = new Patient("Kia Abdullah", 19);
        patient2 = new Patient("Gurpal Chahal", 44);
        patient3 = new Patient("Amitoj Singh", 18);

        el = EventLog.getInstance();
        el.clear();
    }

    @Test
    void testConstructor() {
        assertTrue(testPatientList.getPatients().isEmpty());
        assertEquals(0, testPatientList.getNumOfBeds());
    }

    @Test
    void testPatientListWithOnePatient() {
        testPatientList.addPatient(patient1);
        assertTrue(testPatientList.getPatients().contains(patient1));
        assertEquals(1, testPatientList.getNumOfBeds());
    }

    @Test
    void testPatientListWithManyPatients() {
        testPatientList.addPatient(patient1);
        testPatientList.addPatient(patient2);
        testPatientList.addPatient(patient3);
        assertTrue(testPatientList.getPatients().contains(patient1));
        assertTrue(testPatientList.getPatients().contains(patient2));
        assertTrue(testPatientList.getPatients().contains(patient3));
        assertEquals(3, testPatientList.getNumOfBeds());
    }

    @Test
    void testPatientListWithMoreThan500Patients() {
        for (int i = 0; i < 500; i++) {
            testPatientList.addPatient(patient1);
        }
        assertEquals(500, testPatientList.getNumOfBeds());

        testPatientList.addPatient(patient2);
        testPatientList.addPatient(patient3);
        assertEquals(500, testPatientList.getNumOfBeds());
    }

    @Test
    void testRemovePatient() {
        testPatientList.addPatient(patient1);
        testPatientList.addPatient(patient2);
        testPatientList.addPatient(patient3);
        testPatientList.removePatient(0);
        assertEquals(2, testPatientList.getNumOfBeds());
        assertFalse(testPatientList.getPatients().contains(patient1));
        assertTrue(testPatientList.getPatients().contains(patient2));
        assertTrue(testPatientList.getPatients().contains(patient3));
    }

    @Test
    void testAddPatientEventLog() {
        testPatientList.addPatient(patient1);
        itr = el.iterator();

        assertTrue(itr.hasNext());
        assertEquals("Event log cleared.", itr.next().getDescription());
        assertTrue(itr.hasNext());
        assertEquals("Patient Added.", itr.next().getDescription());
        assertFalse(itr.hasNext());
    }

    @Test
    void testRemovePatientEventLog() {
        testPatientList.addPatient(patient1);
        testPatientList.removePatient(0);
        itr = el.iterator();

        assertTrue(itr.hasNext());
        assertEquals("Event log cleared.", itr.next().getDescription());

        assertTrue(itr.hasNext());
        assertEquals("Patient Added.", itr.next().getDescription());

        assertTrue(itr.hasNext());
        assertEquals("Patient Removed.", itr.next().getDescription());
        assertFalse(itr.hasNext());
    }

}
