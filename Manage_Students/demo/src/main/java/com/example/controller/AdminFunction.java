package com.example.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.entity.Student;

public class AdminFunction {
    public static void addStudentToDB(Student student) {
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();
        if (conn != null) {
            String sql = "INSERT INTO student (studentID, password, name, email, phoneNumber) VALUES (?, ?, ?, ?, ?)";

            try {
                // Tạo đối tượng PreparedStatement để thực thi câu lệnh SQL
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, student.getStudentID());
                pstmt.setString(2, student.getPass());
                pstmt.setString(3, student.getName());
                pstmt.setString(4, student.getEmail());
                pstmt.setString(5, student.getPhoneNumber());

                // Thực thi câu lệnh SQL
                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new student was inserted successfully!");
                }

                // Đóng kết nối
                pstmt.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Student getStudent(String studentId) {
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();
        Student student = null;

        if (conn != null) {
            String sql = "SELECT * FROM student WHERE studentID = ?";

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, studentId);

                // Thực hiện truy vấn
                var rs = pstmt.executeQuery();

                // Nếu có kết quả, lấy thông tin sinh viên
                if (rs.next()) {
                    String id = rs.getString(studentId);
                    String pass = rs.getString("password");
                    String Name = rs.getString("name");
                    String Email = rs.getString("email");
                    String Phone = rs.getString("phoneNumber");

                    // Tạo đối tượng Student từ dữ liệu nhận được
                    student = new Student(id, pass, Name, Email, Phone);
                }

                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return student;
    }

    public static void updateStudent(String studentID, Student updatedStudent) {
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();

        if (conn != null) {
            String sql = "UPDATE student SET name = ?, password = ?, email = ?, phoneNumber = ? WHERE studentID = ?";

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, updatedStudent.getName());
                pstmt.setString(2, updatedStudent.getPass());
                pstmt.setString(3, updatedStudent.getEmail());
                pstmt.setString(4, updatedStudent.getPhoneNumber());
                pstmt.setString(5, studentID);

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Student information updated successfully.");
                } else {
                    System.out.println("No student found with the given ID.");
                }

                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteStudent(String studentID) {
        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();

        if (conn != null) {
            String sql = "DELETE FROM student WHERE studentID = ?";

            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, studentID);

                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Student deleted successfully.");
                } else {
                    System.out.println("No student found with the given ID.");
                }

                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Tạo đối tượng học sinh
        // Student student1 = new Student("22010049", "nvminh12345", "Nguyen Van Minh",
        // "22010049@st.phenikaa-uni.edu.vn","0359253285");

        // Lưu đối tượng học sinh vào cơ sở dữ liệu
        // addStudentToDB(student1);

        // Cập nhật thông tin học sinh
        Student updatedStudent = new Student("", "minh12345", "Nguyen Van Minh2", "nguyenvanminhbh2004@gmail.com",
                "0859621886");
        updateStudent("22010049", updatedStudent);

        // Xóa học sinh khỏi database
        // deleteStudent(1);
    }

}
