package forms;

import tools.DBHandler;
import tools.FormConfigurator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EnterForm extends JFrame {
    private JTextField loginField;
    private JPanel mainPanel;
    private JPasswordField passwordField;
    private JButton enterButton;

    public EnterForm(){
        FormConfigurator.config(this, mainPanel, "Auth", 300, 400, WindowConstants.EXIT_ON_CLOSE);
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DBHandler.openConnection();
                    ResultSet resultSet = DBHandler.query("SELECT * from user WHERE Login='" + loginField.getText() + "' AND Password='" + passwordField.getText() + "'");
                    while (resultSet.next()){
                        if (resultSet.getInt("ID")!=0){
                            UserForm userForm = new UserForm(resultSet.getInt("Type"), resultSet.getString("Name"));
                            userForm.setVisible(true);
                            userForm.pack();
                        } else JOptionPane.showMessageDialog(null, "Неверный логин или пароль", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                    DBHandler.closeConnection();
                }catch (SQLException throwables){
                    throwables.printStackTrace();
                }

            }
        });
    }
}
