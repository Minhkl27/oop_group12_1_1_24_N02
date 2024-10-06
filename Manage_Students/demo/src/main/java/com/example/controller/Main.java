package com.example.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();

        String query = "SELECT * FROM student";

        Statement stm = null;
        try {
            // Tạo đối tượng Statement
            stm = conn.createStatement();

            // Thực thi truy vấn và trả về đối tượng ResultSet
            ResultSet rs = stm.executeQuery(query);

            // Duyệt kết quả trả về
            while (rs.next()) { // Di chuyển con trỏ xuống bản ghi kế tiếp
                int StudentID = rs.getInt("studentID");
                String Password = rs.getString("password");
                String Name = rs.getString("name");
                String Email = rs.getString("email");
                String PhoneNumber = rs.getString("phoneNumber");
                System.out.println(StudentID + " - " + Password + " - " + Name + " - " + Email + " - " + PhoneNumber);
            }
            // Đóng kết nối
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}