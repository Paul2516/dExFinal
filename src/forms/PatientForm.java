package forms;

import tools.FormConfigurator;
import tools.DBHandler;
import tools.FormConfigurator;
import tools.Constructor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class PatientForm extends JFrame {
    private JPanel patientPanel;
    private JTextField fullNameField;
    private JComboBox dayBox;
    private JComboBox monthBox;
    private JComboBox yearBox;
    private JSpinner seriaSpinner;
    private JSpinner numberSpinner;
    private JTextField phoneField;
    private JTextField emailField;
    private JSpinner securityNumberSpinner;
    private JComboBox securityTypeBox;
    private JTextField securityCompanyField;
    private JButton newPatientButton;
    private JButton dismissButton;

    public PatientForm(String name) {
    FormConfigurator.config(this, patientPanel, "Patient", 400, 500, WindowConstants.DISPOSE_ON_CLOSE);
    fullNameField.setText(name);
        for (int i = 1; i <32; i++) {
            dayBox.addItem(i);
        }
        for (int i = 1; i <13; i++) {
            monthBox.addItem(i);
        }
        for (int i = 1922; i <2022; i++) {
            yearBox.addItem(i);
        }
        securityTypeBox.addItem("dms");
        securityTypeBox.addItem("oms");

        newPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBHandler.openConnection();
                DBHandler.query("INSERT INTO patient (FullName, Birthday, PassportS, PassportN, Phone, Email, SocialSecNumber, SocialType, InsuranceName) VALUES (" +
                        "'" + fullNameField.getText() + "', " +
                        "'" + yearBox.getSelectedItem().toString() + "-" + monthBox.getSelectedItem().toString() + "-" + dayBox.getSelectedItem().toString() + "', "
                        + seriaSpinner.getValue().toString() + ", "
                        + numberSpinner.getValue().toString() + ", " +
                        "'" + phoneField.getText() + "', " +
                        "'" + emailField.getText() + "', "
                        + securityNumberSpinner.getValue().toString() + ", " +
                        "'" + securityTypeBox.getSelectedItem().toString() + "', " +
                        "'" + securityCompanyField.getText() + "');");
                JOptionPane.showMessageDialog(null, "Пациент добавлен", "Все шик", JOptionPane.INFORMATION_MESSAGE);
                DBHandler.closeConnection();
            }
        });
    }
}
