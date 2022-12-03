package ui;

import model.EventLog;
import model.Patient;
import model.PatientList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// E-Healthcare Management System Application (GUI)
// Code from ListDemo from https://docs.oracle.com/javase/tutorial/uiswing/components/list.html was used to help create this class
public class GUI extends JFrame implements ListSelectionListener {

    private JList list;
    private DefaultListModel listModel;

    private static final String addString = "Add Patient";
    private static final String removeString = "Remove Patient";
    private static final String saveString = "Save Patients";
    private static final String loadString = "Load Patients";
    private static final String JSON_STORE = "./data/patientList.json";

    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JTextField patientName;
    private JTextField patientAge;
    private JTextField patientDiagnosis;
    private JTextField patientPrescription;

    private PatientList patientList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Patient patient;

    Image icon = Toolkit.getDefaultToolkit().getImage("data/healthCross.jpeg");

    // EFFECTS: runs the GUI
    public GUI() {

        super("E-Healthcare Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        patientList = new PatientList("List of saved Patients");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setIconImage(icon);

        setBackground(Color.RED);

        listModel = new DefaultListModel();

        JScrollPane listScrollPane = createListAndScrollPane();

        JButton addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);

        addButton.setForeground(Color.GREEN);
        addButton.setEnabled(false);

        implementOtherButtons();

        createTextFields(addListener);

        JPanel buttonPane = createPanel(addButton);

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);

        printLog();

        //Display the window.
        pack();
        setVisible(true);
    }

    private void printLog() {
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {
                for (model.Event event : EventLog.getInstance()) {
                    System.out.println(event.toString() + "\n");
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
    }

    // MODIFIES: this
    // EFFECTS: implements all buttons except addButton (which is implemented separately)
    private void implementOtherButtons() {
        this.removeButton = new JButton(removeString);
        this.removeButton.setActionCommand(removeString);
        this.removeButton.addActionListener(new RemoveListener());

        removeButton.setForeground(Color.RED);
        removeButton.setEnabled(false);

        this.saveButton = new JButton(saveString);
        this.saveButton.setActionCommand(saveString);
        this.saveButton.addActionListener(new SaveListener());

        this.loadButton = new JButton(loadString);
        this.loadButton.setActionCommand(loadString);
        this.loadButton.addActionListener(new LoadListener());
    }

    // MODIFIES: this
    // EFFECTS: creates a panel that uses BoxLayout and adds elements to it
    private JPanel createPanel(JButton addButton) {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(this.removeButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(patientName);
        buttonPane.add(patientAge);
        buttonPane.add(patientDiagnosis);
        buttonPane.add(patientPrescription);
        buttonPane.add(addButton);

        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(saveButton);
        buttonPane.add(loadButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return buttonPane;
    }

    // MODIFIES: this
    // EFFECTS: creates text fields and associates them to AddListener
    private void createTextFields(AddListener addListener) {
        patientName = new JTextField("Name", 10);
        patientAge = new JTextField("Age", 3);
        patientDiagnosis = new JTextField("Diagnosis", 10);
        patientPrescription = new JTextField("Prescription", 10);
        patientName.addActionListener(addListener);
        patientAge.addActionListener(addListener);
        patientDiagnosis.addActionListener(addListener);
        patientPrescription.addActionListener(addListener);
        patientName.getDocument().addDocumentListener(addListener);
    }

    // MODIFIES: this
    // EFFECTS: creates the list and puts it in a scroll pane
    private JScrollPane createListAndScrollPane() {
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        return listScrollPane;
    }

    // ActionListener associated to removing a patient
    private class RemoveListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS: removes the selected patient from the list
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            listModel.remove(index);
            patientList.removePatient(index);

            int size = listModel.getSize();

            if (size == 0) { // nobody's left, disable removing
                removeButton.setEnabled(false);

            } else { // select an index
                if (index == listModel.getSize()) {
                    // removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    // ActionListener associated to adding a patient
    private class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        // MODIFIES: this
        // EFFECTS: updates the field button to parameter button
        public AddListener(JButton button) {
            this.button = button;
        }

        // MODIFIES: this
        // EFFECTS: adds the patient (based on the inputs provided) to the list if inputs are legal
        public void actionPerformed(ActionEvent e) {
            String name = patientName.getText();
            String age = patientAge.getText();
            String diagnosis = patientDiagnosis.getText();
            String prescription = patientPrescription.getText();


            if (illegalAddition(name, age, diagnosis, prescription)) {
                return;
            }

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            listModel.insertElementAt("Name: " + name + " Age: " + age + " Diagnosis: " + diagnosis
                    + " Prescription: " + prescription, index);

            resetTextFields();

            patient = new Patient(name, Integer.parseInt(age));
            patient.addPrescription(prescription);
            patient.addDiagnosis(diagnosis);
            patientList.addPatient(patient);

            // select the new item and make it visible
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        // EFFECTS: returns true if the inputs to add a patient are legal; false otherwise
        private boolean illegalAddition(String name, String age, String diagnosis, String prescription) {
            //User didn't type in a unique name...
            if (name.equals("Name") || age.equals("Age") || diagnosis.equals("Diagnosis")
                    || prescription.equals("Prescription")) {
                Toolkit.getDefaultToolkit().beep();
                patientName.requestFocusInWindow();
                patientName.selectAll();
                return true;
            }
            return false;
        }

        // MODIFIES: this
        // EFFECTS: resets the text fields
        private void resetTextFields() {
            patientName.requestFocusInWindow();
            patientName.setText("Name");
            patientAge.setText("Age");
            patientDiagnosis.setText("Diagnosis");
            patientPrescription.setText("Prescription");
        }

        //Required by DocumentListener
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // MODIFIES: this
        // EFFECTS: enables the button if not already enabled
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        // MODIFIES: this
        // EFFECTS: If the text field is empty, then disables the buttons
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    //this method is required by ListSelectionListener
    // MODIFIES: this
    // EFFECTS: disables remove button if no selection; enables otherwise
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

            if (list.getSelectedIndex() == -1) {
                // no selection, disable remove button
                removeButton.setEnabled(false);

            } else {
                // selection, enable the remove button
                removeButton.setEnabled(true);
            }
        }
    }

    // ActionListener associated to saving the list of patients
    private class SaveListener implements ActionListener {
        @Override
        // EFFECTS: saves the patientList to file
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(patientList);
                jsonWriter.close();
            } catch (FileNotFoundException f) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    // ActionListener associated to loading the list of patients
    private class LoadListener implements ActionListener {
        @Override
        // MODIFIES: this
        // EFFECTS: loads patientList from file
        public void actionPerformed(ActionEvent e) {
            try {
                patientList = jsonReader.read();

                for (Patient patient : patientList.getPatients()) {
                    listModel.addElement("Name: " + patient.getName() + " Age: " + patient.getAge()
                            + " Diagnosis: " + patient.getDiagnosis() + " Prescription: " + patient.getPrescription());
                }

            } catch (IOException f) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }
}