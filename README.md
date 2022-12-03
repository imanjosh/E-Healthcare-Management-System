# E-Healthcare Management System

**E-Healthcare Management System** is an application that seeks to provide effective management of medical data of 
patients in hospitals and/or clinics. This application will be used by the medical staff of a hospital or clinic to 
record medical information such as bed occupation ratio, diagnoses, medicines, etc.

When I thought of this project, I knew I had to do this. It is very applicable to the real world and is also very 
helpful in digitalizing important medical information and eliminating the risk of errors and losing information. 
This project is also very much aligned with my major *(Computer Science and Biology)*. I am glad I have this amazing
opportunity to apply computer science in the field of biology.

## User Stories:

- As a user, I want to be able to add a patient to the list of patients
- As a user, I want to be able to view the list of patients on the list
- As a user, I want to be able to add medical information such as diagnoses and medicines to the associated patient
- As a user, I want to be able to view and change the bed occupation ratio
- As a user, I want to be able to save my list of patients to file
- As a user, I want to be able to load my list of patients from file

# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by clicking the button labelled "Add Patient"
- You can generate the second required event related to adding Xs to a Y by clicking the button labelled "Remove Patient"
- You can locate my visual component by minimizing the window and looking at the icon in the docks bar (MacOS)
- You can save the state of my application by clicking the button labelled "Save Patients"
- You can reload the state of my application by clicking the button labelled "Load Patients"

# Phase 4: Task 2
Thu Dec 01 23:35:27 PST 2022
Patient Added.

Thu Dec 01 23:36:37 PST 2022
Patient Added.

Thu Dec 01 23:36:42 PST 2022
Patient Removed.


Process finished with exit code 0

# Phase 4: Task 3
Reflecting on the UML class diagram for this application,
there are a couple refactorings I would have done to improve the design if I had more time to work on the project. They
are as follows:
- Increase cohesion by dividing my large GUI class into several other classes while still maintaining low coupling
by not overdoing it (for example: adding another class whose only responsibilty is doing ActionListener events)
- Exception handling
- Use singleton design so that there's only ever one instance of PatientList