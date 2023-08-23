package by.clevertec.CleverBank.dao;


import by.clevertec.CleverBank.dao.api.ConnectStorage;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.UUID;


public class PostgreSQLJDBC {
    public static void main(String args[]) {
        Statement stmt = ConnectStorage.connect();

        String getAll = "SELECT uuid, name, db_create, db_last_update\n" +
                "\tFROM app.banks";
        try (ResultSet rs = stmt.executeQuery(getAll)) {
            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                System.out.println(rs.getObject("uuid", UUID.class));
                System.out.println(rs.getString("name"));
                System.out.println(rs.getObject("db_create", LocalDateTime.class));
                System.out.println(rs.getObject("db_last_update", LocalDateTime.class));

                System.out.println();
            }
            System.out.println();
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


        System.out.println("Opened database successfully");


    }
}
