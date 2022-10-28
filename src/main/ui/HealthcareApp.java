package ui;


import model.Patient;
import model.PatientList;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// E-Healthcare Management System Application
// Code used from TellerApp.java to help create this HealthcareApp
public class HealthcareApp {
    private static final String JSON_STORE = "./data/patientList.json";
    private Scanner input;
    private PatientList patientList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: runs the E-Healthcare Management System Application
    public HealthcareApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        patientList = new PatientList("List of saved Patients");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runHealthcareApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runHealthcareApp() {
        boolean keepGoing = true;
        String command;

        init();

        System.out.println("Welcome to E-Healthcare Management System");
        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add Patient");
        System.out.println("\tv -> View list of Patients");
        System.out.println("\t# -> View number of beds taken");
        System.out.println("\ts -> Save list of patients to file");
        System.out.println("\tl -> Load list of patients from file");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addPatientUi();
        } else if (command.equals("v")) {
            getPatientsUi();
        } else if (command.equals("#")) {
            getNumOfBedsUi();
        } else if (command.equals("s")) {
            savePatientSavedList();
        } else if (command.equals("l")) {
            loadPatientSavedList();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: adds a new patient with name, age, diagnosis, & prescription to the list
    private void addPatientUi() {
        System.out.print("Enter the name of the patient: ");
        String name = input.next();
        System.out.print("Enter the age of the patient: ");
        int age = input.nextInt();
        System.out.print("Provide the diagnosis for the associated patient: ");
        String diagnosis = input.next();
        System.out.print("Provide the prescription for the associated patient: ");
        String prescription = input.next();
        Patient patient = new Patient(name, age);
        patient.addDiagnosis(diagnosis);
        patient.addPrescription(prescription);
        patientList.addPatient(patient);
    }

    // EFFECTS: prints all patients' name, age, diagnosis, & prescription in the list;
    //          prints "No patients added yet. Press a to start." when list is empty
    private void getPatientsUi() {
        if (patientList.getPatients().isEmpty()) {
            System.out.print("No patients added yet. Press a to add or l to load previous patients.");
        } else {
            for (Patient patient : patientList.getPatients()) {
                System.out.print("Name: " + patient.getName() + " Age: " + patient.getAge() + " Diagnosis: ");
                System.out.print(patient.getDiagnosis() + " Prescription: " + patient.getPrescription() + "\n");
            }
        }
    }

    // EFFECTS: prints the number of beds taken out of 500
    private void getNumOfBedsUi() {
        System.out.print("Number of beds taken " + patientList.getNumOfBeds() + "/500");
    }

    // MODIFIES: this
    // EFFECTS: initializes patientList & input
    private void init() {
        patientList = new PatientList("List of saved Patients");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: saves the patientList to file
    private void savePatientSavedList() {
        try {
            jsonWriter.open();
            jsonWriter.write(patientList);
            jsonWriter.close();
            System.out.println("Saved " + patientList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads patientList from file
    private void loadPatientSavedList() {
        try {
            patientList = jsonReader.read();
            System.out.println("Loaded " + patientList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}

