package ui;


import model.Patient;
import model.PatientList;

import java.util.Scanner;

// E-Healthcare Management System Application
// Code used from TellerApp.java to help create this HealthcareApp
public class HealthcareApp {
    private Scanner input;
    private PatientList patientList;

    // EFFECTS: runs the E-Healthcare Management System Application
    public HealthcareApp() {
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
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: adds a new patient with name, age, diagnosis, & prescription to the list
    private void addPatientUi() {
        System.out.print("Enter the name of the patient:");
        String name = input.next();
        System.out.print("Enter the age of the patient:");
        int age = input.nextInt();
        System.out.print("Provide the diagnosis for the associated patient:");
        String diagnosis = input.next();
        System.out.print("Provide the prescription for the associated patient:");
        String prescription = input.next();
        Patient patient = new Patient(name, age);
        patient.addDiagnosis(diagnosis);
        patient.addPrescription(prescription);
        patientList.addPatient(patient);
    }

    // MODIFIES: this
    // EFFECTS: prints all patient's name, age, diagnosis, & prescription in the list;
    //          prints "No patients added yet. Press a to start." when list is empty
    private void getPatientsUi() {
        if (patientList.getPatients().isEmpty()) {
            System.out.print("No patients added yet. Press a to start.");
        } else {
            for (Patient patient : patientList.getPatients()) {
                System.out.print("Name: " + patient.getName() + " Age: " + patient.getAge() + " Diagnosis: ");
                System.out.print(patient.getDiagnosis() + " Prescription: " + patient.getPrescription() + "\n");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prints the number of beds taken out of 500
    private void getNumOfBedsUi() {
        System.out.print("Number of beds taken " + patientList.getNumOfBeds() + "/500");
    }

    // MODIFIES: this
    // EFFECTS: initializes patientList & input
    private void init() {
        patientList = new PatientList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

}

