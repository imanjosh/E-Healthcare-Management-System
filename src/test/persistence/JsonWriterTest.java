package persistence;

import model.Patient;
import model.PatientList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Code from JsonSerializationDemo was used to help create this class
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            PatientList patientList = new PatientList("My List of saved Patients");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            PatientList patientList = new PatientList("My List of saved Patients");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPatientList.json");
            writer.open();
            writer.write(patientList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPatientList.json");
            patientList = reader.read();
            assertEquals("My List of saved Patients", patientList.getName());
            assertEquals(0, patientList.numPatients());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Patient patient1 = new Patient("Iman", 19);
            patient1.addDiagnosis("None");
            patient1.addPrescription("None");
            Patient patient2 = new Patient("Gogo", 10);
            patient2.addDiagnosis("Cancer");
            patient2.addPrescription("Chemotherapy");
            PatientList patientList = new PatientList("My List of saved Patients");
            patientList.addPatient(patient1);
            patientList.addPatient(patient2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPatientList.json");
            writer.open();
            writer.write(patientList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPatientList.json");
            patientList = reader.read();
            assertEquals("My List of saved Patients", patientList.getName());
            List<Patient> patients = patientList.getPatients();
            assertEquals(2, patients.size());
            checkPatient("Iman", 19, "None", "None", patients.get(0));
            checkPatient("Gogo", 10, "Cancer", "Chemotherapy", patients.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}