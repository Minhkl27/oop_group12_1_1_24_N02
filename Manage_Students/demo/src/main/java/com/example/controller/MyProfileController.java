package com.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.entity.Score;

public class MyProfileController {

    @FXML
    private Label studentIDLabel;

    @FXML
    private Label studentNameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private TableView<Score> scoreTable;

    @FXML
    private TableColumn<Score, String> subjectIDColumn;

    @FXML
    private TableColumn<Score, Float> scoreColumn;

    private ObservableList<Score> scoreList = FXCollections.observableArrayList();

    // Giả định sinh viên hiện tại có StudentID là "S001"
    private final String currentStudentID = "22010049";

    public void initialize() {
        // Gán dữ liệu cho các cột trong TableView
        subjectIDColumn.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        // Lấy thông tin cá nhân và điểm từ cơ sở dữ liệu
        loadStudentProfile(currentStudentID);
        loadStudentScores(currentStudentID);
    }

    // Phương thức nạp thông tin cá nhân từ cơ sở dữ liệu
    private void loadStudentProfile(String studentID) {
        String query = "SELECT * FROM student WHERE studentID = '" + studentID + "'";

        try (Connection conn = ConnectJDBC.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                studentIDLabel.setText(rs.getString("studentID"));
                studentNameLabel.setText(rs.getString("name"));
                emailLabel.setText(rs.getString("email"));
                phoneNumberLabel.setText(rs.getString("phoneNumber"));
            }

        } catch (SQLException e) {
            System.out.println("Error loading student profile: " + e.getMessage());
        }
    }

    // Phương thức nạp điểm số từ cơ sở dữ liệu
    private void loadStudentScores(String studentID) {
        String query = "SELECT * FROM scores WHERE studentID = '" + studentID + "'";

        try (Connection conn = ConnectJDBC.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String subjectID = rs.getString("subject_id");
                float score = rs.getFloat("score");

                scoreList.add(new Score(studentID, subjectID, score));
            }

        } catch (SQLException e) {
            System.out.println("Error loading student scores: " + e.getMessage());
        }

        // Hiển thị điểm số trong TableView
        scoreTable.setItems(scoreList);
    }
}
