package persistence;

import model.Patient;
import model.PatientList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PatientList patientList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPatientList.json");
        try {
            PatientList patientList = reader.read();
            assertEquals("My List of saved Patients", patientList.getName());
            assertEquals(0, patientList.numPatients());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPatientList.json");
        try {
            PatientList patientList = reader.read();
            assertEquals("My List of saved Patients", patientList.getName());
            List<Patient> patients = patientList.getPatients();
            assertEquals(2, patients.size());
            checkPatient("Iman", 19, "None", "None", patients.get(0));
            checkPatient("Gogo", 10, "Cancer", "Chemotherapy", patients.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}