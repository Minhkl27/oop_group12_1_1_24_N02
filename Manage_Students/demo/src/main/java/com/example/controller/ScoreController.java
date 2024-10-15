package com.example.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.entity.Score;

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

public class ScoreController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField studentIDInput;
    @FXML
    private TextField subjectIDInput;
    @FXML
    private TextField scoreInput;
    @FXML
    private TableView<Score> table;
    @FXML
    private TableColumn<Score, String> studentIDColumn;
    @FXML
    private TableColumn<Score, String> subjectIDColumn;
    @FXML
    private TableColumn<Score, Integer> scoreColumn;

    private ObservableList<Score> scoreList;

    @FXML
    public void initialize() {
        // Khởi tạo danh sách và liên kết dữ liệu với bảng
        scoreList = FXCollections.observableArrayList();

        // Thiết lập giá trị cột
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        subjectIDColumn.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        // Thiết lập danh sách cho bảng
        table.setItems(scoreList);
    }

    private ObservableList<Score> scoreData = FXCollections.observableArrayList();

    public void loadScore(ActionEvent event) {

        String query = "SELECT studentID, subject_id, score FROM scores";

        try (Connection conn = ConnectJDBC.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String studentID = rs.getString("studentID");
                String subjectID = rs.getString("subject_id");
                float score = rs.getFloat("score");

                scoreData.add(new Score(studentID, subjectID, score));
            }

            table.setItems(scoreData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addScore(ActionEvent event) {
        // Lấy dữ liệu từ các trường nhập liệu
        String studentID = studentIDInput.getText();
        String subjectID = subjectIDInput.getText();
        float score = Float.parseFloat(scoreInput.getText());

        // Tạo đối tượng Score và thêm vào bảng (TableView)
        Score newScore = new Score(studentID, subjectID, score);
        scoreList.add(newScore);

        // Lưu điểm số vào cơ sở dữ liệu
        saveScoreToDatabase(newScore);

        // Xóa các trường nhập liệu sau khi thêm
        clearInputs();
    }

    private void saveScoreToDatabase(Score score) {
        String query = "INSERT INTO scores (studentID, subject_id, score) VALUES (?, ?, ?)";

        try (Connection conn = ConnectJDBC.connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Gán giá trị cho câu lệnh SQL
            pstmt.setString(1, score.getStudentID());
            pstmt.setString(2, score.getSubjectID());
            pstmt.setFloat(3, score.getScore());

            // Thực thi câu lệnh SQL
            pstmt.executeUpdate();

            System.out.println("Score added to database successfully!");

        } catch (SQLException e) {
            System.out.println("Error adding score: " + e.getMessage());
        }
    }

    @FXML
    private void editScore(ActionEvent event) {
        // Lấy điểm số được chọn trong bảng
        Score selectedScore = table.getSelectionModel().getSelectedItem();

        if (selectedScore != null) {
            try {
                // Cập nhật thông tin điểm từ các trường nhập liệu
                selectedScore.setStudentID(studentIDInput.getText());
                selectedScore.setSubjectID(subjectIDInput.getText());
                selectedScore.setScore(Float.parseFloat(scoreInput.getText()));

                // Cập nhật lại bảng
                table.refresh();

                // Cập nhật thông tin điểm trong cơ sở dữ liệu
                updateScoreInDatabase(selectedScore);

                // Xóa các trường nhập liệu sau khi cập nhật
                clearInputs();
            } catch (Exception e) {
                System.out.println("Invalid data! Please enter valid information.");
            }
        }
    }

    private void updateScoreInDatabase(Score score) {
        String query = "UPDATE scores SET score = ? WHERE studentID = ? AND subject_id = ?";

        try (Connection conn = ConnectJDBC.connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Gán giá trị cho câu lệnh SQL
            pstmt.setFloat(1, score.getScore());
            pstmt.setString(2, score.getStudentID());
            pstmt.setString(3, score.getSubjectID());

            // Thực thi câu lệnh SQL
            pstmt.executeUpdate();

            System.out.println("Score updated in database successfully!");

        } catch (SQLException e) {
            System.out.println("Error updating score: " + e.getMessage());
        }
    }

    private void clearInputs() {
        studentIDInput.clear();
        subjectIDInput.clear();
        scoreInput.clear();
    }

    @FXML
    public void clickback(ActionEvent event) throws IOException {
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
