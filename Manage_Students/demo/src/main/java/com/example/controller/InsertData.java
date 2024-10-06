package com.example.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    public static void main(String[] args) {
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();

        // SQL query để chèn dữ liệu vào bảng
        String sql = "INSERT INTO student (StudentID, Password, Name, Email, PhoneNumber) VALUES (?, ?, ?, ?, ?)";

        try {

            // Tạo PreparedStatement để thực thi câu lệnh SQL
            PreparedStatement statement = conn.prepareStatement(sql);

            // Thiết lập giá trị cho các tham số
            statement.setInt(1, 1); // StudentID
            statement.setString(2, "password123"); // Password
            statement.setString(3, "Nguyen Van A"); // Name
            statement.setString(4, "nguyenvana@example.com"); // Email
            statement.setString(5, "0123456789"); // PhoneNumber

            // Thực thi câu lệnh
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Chèn dữ liệu thành công!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
