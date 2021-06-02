package tools;

import javax.swing.*;
import java.sql.*;

public class DBHandler extends JFrame {
        public static Connection connection;
        public static boolean openConnection(){
            try {
                connection = DriverManager.getConnection(Constructor.DBURL, Constructor.DBUSER, Constructor.DBPASSWORD);
                return true;
            }catch (SQLException throwables){
                throwables.printStackTrace();
                return false;
            }
        }
        public static boolean closeConnection(){
            try {
                connection.close();
                return true;
            }catch (SQLException throwables){
                throwables.printStackTrace();
                return false;
            }
        }
        public static ResultSet query (String query){
            ResultSet resultSet = null;
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                if (query.contains("SELECT")){
                    resultSet = preparedStatement.executeQuery();
                } else preparedStatement.executeUpdate();
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
            return resultSet;
        }
}