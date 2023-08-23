package by.clevertec.CleverBank.dao.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectStorage {
    public static Statement connect() {
        Statement stmt;
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            System.out.println("Error loading properties file");
        }
        try {
            Class.forName(props.getProperty("jdbc.driverClassName"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection con = DriverManager.getConnection(props.getProperty("jdbc.url"),
                    props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
            stmt = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stmt;
    }
}
