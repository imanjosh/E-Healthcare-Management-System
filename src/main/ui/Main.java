package ui;

import java.io.FileNotFoundException;

// Code from JsonSerializationDemo was used to help create this class
public class Main {
    public static void main(String[] args) {
        try {
            new HealthcareApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
