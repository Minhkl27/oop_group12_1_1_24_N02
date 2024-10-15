package com.example.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectJDBC {
    private static String hostName = "localhost:3306";
    private static String dbName = "student_management";
    private static String username = "root";
    private static String password = "";

    private static String connectionURL = "jdbc:mysql://" + hostName + "/" + dbName;

    public static Connection connect() {
        // Tạo đối tượng Connection
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(connectionURL, username, password);
            System.out.println("Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
