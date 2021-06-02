package forms;

import tools.DBHandler;
import tools.FormConfigurator;
import tools.TableManage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserForm extends JFrame {
    private JPanel userPanel;
    private JLabel typeField;
    private JButton backButton;
    private JPanel labPanel;
    private JButton biomaterialButton;
    private JButton reportButton;
    private JButton analizatorButton;
    private JPanel scientistPanel;
    private JPanel accountingPanel;
    private JButton reportsButton;
    private JButton makeAccountButton;
    private JPanel adminPanel;
    private JButton makeReportButton;
    private JButton historyButton;
    private JButton materialsButton;
    private JScrollPane type1;
    private JScrollPane type2;
    private JScrollPane type3;
    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel patientPanel;
    private JButton patientButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton redButton;
    private JButton sortButton;
    private JTextField searchField;

    public UserForm(int type, String name) {
        FormConfigurator.config(this, userPanel, "User", 600, 800, WindowConstants.DISPOSE_ON_CLOSE);
        DefaultTableModel userModel = new DefaultTableModel();
        DBHandler.openConnection();
        switch (type) {
            case 0:
                typeField.setText("Admin: " + name);
                typeField.setIcon(new ImageIcon(getClass().getResource("/admin.png")));
                adminPanel.setVisible(true);
                break;
            case 1:
                typeField.setText("Laborant: " + name);
                typeField.setIcon(new ImageIcon(getClass().getResource("/laborant_1.png")));
                panel1.setVisible(true);
                TableManage.refreshTable(table1);
                break;
            case 2:
                typeField.setText("laborant-2: " + name);
                typeField.setIcon(new ImageIcon(getClass().getResource("/laborant_2.png")));
                scientistPanel.setVisible(true);
                break;
            case 3:
                typeField.setText("Бухгалтер: " + name);
                typeField.setIcon(new ImageIcon(getClass().getResource("/accounting.png")));
                accountingPanel.setVisible(true);
                break;
        }
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        patientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientForm patientForm = new PatientForm(name);
                patientForm.setVisible(true);
                patientForm.pack();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (table1.getSelectedRow() >= 0) {
                        if (JOptionPane.showConfirmDialog(null,
                                "Вы действительно хотите удалить эту строку?",
                                "Внимание", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                            DBHandler.openConnection();
                            DBHandler.query("DELETE from user WHERE Name='" + table1.getValueAt(table1.getSelectedRow(), 0) + "'");
                            DBHandler.closeConnection();
                        }
                    } else
                        JOptionPane.showMessageDialog(null, "Необходимо выбрать хотя бы одну строку", "Ошибка", JOptionPane.WARNING_MESSAGE);
            }
        });
        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null,
                        "Вы действительно хотите редактровать эту строку?",
                        "Внимание", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    String name = (String) table1.getValueAt(table1.getSelectedRow(),0);
                    String ip = (String) table1.getValueAt(table1.getSelectedRow(),1);
                    String dateEnter = (String) table1.getValueAt(table1.getSelectedRow(),2);
                    RedForm redForm = new RedForm(name, ip, dateEnter);
                    redForm.setVisible(true);
                    redForm.pack();
                }

            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableManage.refreshTable(table1);
            }
        });
        DBHandler.closeConnection();
    }

}
