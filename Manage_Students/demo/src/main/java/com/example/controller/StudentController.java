package com.example.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.entity.Student;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class StudentController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField studentIDInput;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField phoneNumberInput;
    @FXML
    private TableView<Student> table;
    @FXML
    private TableColumn<Student, String> studentIDColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;
    @FXML
    private TableColumn<Student, String> phoneNumberColumn;

    private ObservableList<Student> studentList;

    @FXML
    public void initialize() {
        try {
            studentList = FXCollections.observableArrayList();

            // Thiết lập cột cho bảng
            studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

            // Thiết lập danh sách sinh viên
            table.setItems(studentList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addStudent(ActionEvent event) throws IOException {
        try {
            // Lấy dữ liệu từ các trường nhập liệu
            String studentID = studentIDInput.getText();
            String name = nameInput.getText();
            String email = emailInput.getText();
            String phoneNumber = phoneNumberInput.getText();

            // Tạo đối tượng Student và thêm vào bảng (TableView)
            Student student = new Student(studentID, name, email, phoneNumber);
            studentList.add(student);

            // Lưu dữ liệu vào cơ sở dữ liệu
            saveStudentToDatabase(student);

            // Xóa các trường nhập liệu sau khi thêm
            clearInputs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveStudentToDatabase(Student student) {
        String query = "INSERT INTO student (studentID, name, email, phoneNumber) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectJDBC.connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Gán giá trị cho câu lệnh SQL
            pstmt.setString(1, student.getStudentID());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getEmail());
            pstmt.setString(4, student.getPhoneNumber());

            // Thực thi câu lệnh SQL
            pstmt.executeUpdate();

            System.out.println("Student added to database successfully!");

        } catch (SQLException e) {
            System.out.println("Error inserting student: " + e.getMessage());
        }
    }

    private void updateStudentInDatabase(Student student) {
        String query = "UPDATE student SET name = ?, email = ?, phoneNumber = ? WHERE studentID = ?";

        try (Connection conn = ConnectJDBC.connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Gán giá trị cho câu lệnh SQL
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setString(3, student.getPhoneNumber());
            pstmt.setString(4, student.getStudentID());

            // Thực thi câu lệnh SQL
            pstmt.executeUpdate();

            System.out.println("Student updated in database successfully!");

        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    @FXML
    private void editStudent(ActionEvent event) {
        Student selectedStudent = table.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            try {
                // Cập nhật thông tin trong đối tượng Student
                selectedStudent.setStudentID(studentIDInput.getText());
                selectedStudent.setName(nameInput.getText());
                selectedStudent.setEmail(emailInput.getText());
                selectedStudent.setPhoneNumber(phoneNumberInput.getText());

                // Cập nhật lại bảng
                table.refresh();

                // Cập nhật thông tin trong cơ sở dữ liệu
                updateStudentInDatabase(selectedStudent);

                // Xóa các trường nhập liệu sau khi cập nhật
                clearInputs();
            } catch (Exception e) {
                System.out.println("Invalid data! Please enter valid information.");
            }
        }
    }

    @FXML
    private void deleteStudent(ActionEvent event) {
        // Lấy sinh viên được chọn trong bảng
        Student selectedStudent = table.getSelectionModel().getSelectedItem();

        if (selectedStudent != null) {
            // Xóa sinh viên khỏi cơ sở dữ liệu
            deleteStudentFromDatabase(selectedStudent);

            // Xóa sinh viên khỏi danh sách trong giao diện
            studentList.remove(selectedStudent);

            // Xóa các trường nhập liệu
            clearInputs();
        } else {
            System.out.println("No student selected to delete.");
        }
    }

    private void deleteStudentFromDatabase(Student student) {
        String query = "DELETE FROM student WHERE studentID = ?";

        try (Connection conn = ConnectJDBC.connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Gán giá trị cho câu lệnh SQL
            pstmt.setString(1, student.getStudentID());

            // Thực thi câu lệnh SQL
            pstmt.executeUpdate();

            System.out.println("Student deleted from database successfully!");

        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e.getMessage());
        }
    }

    private void clearInputs() {
        studentIDInput.clear();
        nameInput.clear();
        emailInput.clear();
        phoneNumberInput.clear();
    }

    @FXML
    public void btnOnClickBack(ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(getClass().getResource("/com/example/index/HomepageAD.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
