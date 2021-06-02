package forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import tools.DBHandler;
import tools.FormConfigurator;

public class RedForm extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton insertButton;
    private JButton bckButton;
    private JPanel insertPanel;

    public RedForm(String name, String ip, String date) {
        FormConfigurator.config(this, insertPanel, "Редактирование", 500, 400, WindowConstants.DISPOSE_ON_CLOSE);
        textField1.setText(name);
        textField2.setText(ip);
        textField3.setText(date);
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBHandler.openConnection();
                DBHandler.query("UPDATE user SET Name='" + name + "', IP='" + ip + "', LastEnter='" + date + "'");
                DBHandler.closeConnection();
            }
        });
    }
}
