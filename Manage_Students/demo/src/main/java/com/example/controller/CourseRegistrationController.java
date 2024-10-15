package com.example.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.entity.Score;
import com.example.entity.Subject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CourseRegistrationController {
    @FXML
    private TableView<Subject> availableSubjectsTable;
    @FXML
    private TableView<Subject> registeredSubjectsTable;
    @FXML
    private TableColumn<Score, String> subjectIDColumn;
    @FXML
    private TableColumn<Score, String> subjectNameColumn;
    @FXML
    private TableColumn<Score, String> subjectCreditsColumn;
    @FXML
    private TableColumn<Score, String> registeredSubjectIDColumn;
    @FXML
    private TableColumn<Score, String> registeredSubjectNameColumn;
    @FXML
    private TableColumn<Score, String> registeredSubjectCreditsColumn;
    @FXML
    private Button registerButton;
    @FXML
    private Button unregisterButton;

    private ObservableList<Subject> availableSubjects = FXCollections.observableArrayList();
    private ObservableList<Subject> registeredSubjects = FXCollections.observableArrayList();

    @FXML
    private void initialize() {

        subjectIDColumn.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
        subjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        subjectCreditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));

        registeredSubjectIDColumn.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
        registeredSubjectNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        registeredSubjectCreditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));

        loadAvailableSubjects();
        loadRegisteredSubjects();

        registerButton.setOnAction(e -> registerSubject());
        unregisterButton.setOnAction(e -> unregisterSubject());
    }

    // Tải các môn học có thể đăng ký từ cơ sở dữ liệu
    private void loadAvailableSubjects() {
        String query = "SELECT * FROM subject ";// WHERE subject_id NOT IN (SELECT subject_id FROM registrations)
        try (Connection conn = ConnectJDBC.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            // stmt.setString(1, getSubjectID()); // Lấy mã sinh viên từ session hoặc method
            // khác
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String subjectID = rs.getString("subject_id");
                String name = rs.getString("name");
                int credits = rs.getInt("credits");
                availableSubjects.add(new Subject(subjectID, name, credits));
            }

            availableSubjectsTable.setItems(availableSubjects);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Tải các môn học đã đăng ký từ cơ sở dữ liệu
    private void loadRegisteredSubjects() {
        String query = "SELECT s.subject_id, s.name FROM subject s JOIN registrations r ON s.subject_id = r.subject_id WHERE r.studentID = ?";
        try (Connection conn = ConnectJDBC.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, getStudentID());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String subjectID = rs.getString("subject_id");
                String name = rs.getString("name");
                int credits = rs.getInt("credits");
                registeredSubjects.add(new Subject(subjectID, name, credits));
            }

            registeredSubjectsTable.setItems(registeredSubjects);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Đăng ký môn học
    private void registerSubject() {
        Subject selectedSubject = availableSubjectsTable.getSelectionModel().getSelectedItem();
        if (selectedSubject != null) {
            String insertQuery = "INSERT INTO registrations (studentID, subject_id) VALUES (?, ?)";
            try (Connection conn = ConnectJDBC.connect();
                    PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
                stmt.setString(1, getStudentID());
                stmt.setString(2, selectedSubject.getSubjectID());
                stmt.executeUpdate();

                registeredSubjects.add(selectedSubject);
                availableSubjects.remove(selectedSubject);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Hủy đăng ký môn học
    private void unregisterSubject() {
        Subject selectedSubject = registeredSubjectsTable.getSelectionModel().getSelectedItem();
        if (selectedSubject != null) {
            String deleteQuery = "DELETE FROM registrations WHERE studentID = ? AND subject_id = ?";
            try (Connection conn = ConnectJDBC.connect();
                    PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {
                stmt.setString(1, getStudentID());
                stmt.setString(2, selectedSubject.getSubjectID());
                stmt.executeUpdate();

                availableSubjects.add(selectedSubject);
                registeredSubjects.remove(selectedSubject);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Lấy mã sinh viên từ session hoặc từ dữ liệu hiện tại
    private String getStudentID() {
        // Implement logic để lấy mã sinh viên hiện tại (ví dụ từ session)
        return "22010049"; // Sửa lại cho đúng mã sinh viên thực tế
    }
}
