package tools;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableManage {
        public static void refreshTable(JTable table){
            DefaultTableModel refreshModel = new DefaultTableModel();
            refreshModel.setColumnIdentifiers(new String[]{
                    "Имя",
                    "IP-адрес",
                    "Дата последнего входа"
            });
            DBHandler.openConnection();
            ResultSet resultSet = DBHandler.query("SELECT * FROM user");
            try {
                while (resultSet.next()){
                    refreshModel.addRow(new String[]{
                            resultSet.getString(2),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    });
                }
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
            DBHandler.closeConnection();
            table.setModel(refreshModel);
        }
}
