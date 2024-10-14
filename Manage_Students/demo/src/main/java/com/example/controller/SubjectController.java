package com.example.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.entity.Subject;

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

public class SubjectController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField subjectIDInput;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField creditsInput;
    @FXML
    private TableView<Subject> table;
    @FXML
    private TableColumn<Subject, String> subjectIDColumn;
    @FXML
    private TableColumn<Subject, String> nameColumn;
    @FXML
    private TableColumn<Subject, Integer> creditsColumn;

    private ObservableList<Subject> subjectList;

    @FXML
    public void initialize() {
        // Khởi tạo danh sách và kết nối dữ liệu với bảng
        subjectList = FXCollections.observableArrayList();

        // Thiết lập cột cho bảng
        subjectIDColumn.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        creditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));

        // Thiết lập danh sách hiển thị trong bảng
        table.setItems(subjectList);
    }

    @FXML
    private void addSubject(ActionEvent event) {
        // Lấy dữ liệu từ các trường nhập liệu
        String subjectID = subjectIDInput.getText();
        String name = nameInput.getText();
        int credits = Integer.parseInt(creditsInput.getText());

        // Tạo đối tượng Subject và thêm vào bảng (TableView)
        Subject subject = new Subject(subjectID, name, credits);
        subjectList.add(subject);

        // Lưu dữ liệu vào cơ sở dữ liệu
        saveSubjectToDatabase(subject);

        // Xóa các trường nhập liệu sau khi thêm
        clearInputs();
    }

    private void saveSubjectToDatabase(Subject subject) {
        String query = "INSERT INTO subject (subject_id, name, credits) VALUES (?, ?, ?)";

        try (Connection conn = ConnectJDBC.connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Gán giá trị cho câu lệnh SQL
            pstmt.setString(1, subject.getSubjectID());
            pstmt.setString(2, subject.getName());
            pstmt.setInt(3, subject.getCredits());

            // Thực thi câu lệnh SQL
            pstmt.executeUpdate();

            System.out.println("Subject added to database successfully!");

        } catch (SQLException e) {
            System.out.println("Error inserting subject: " + e.getMessage());
        }
    }

    private void updateSubjectInDatabase(Subject subject) {
        String query = "UPDATE subject SET name = ?, credits = ? WHERE subject_id = ?";

        try (Connection conn = ConnectJDBC.connect();
                PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Gán giá trị cho câu lệnh SQL
            pstmt.setString(1, subject.getName());
            pstmt.setInt(2, subject.getCredits());
            pstmt.setString(3, subject.getSubjectID());

            // Thực thi câu lệnh SQL
            pstmt.executeUpdate();

            System.out.println("Subject updated in database successfully!");

        } catch (SQLException e) {
            System.out.println("Error updating subject: " + e.getMessage());
        }
    }

    @FXML
    private void editSubject(ActionEvent event) {
        Subject selectedSubject = table.getSelectionModel().getSelectedItem();
        if (selectedSubject != null) {
            try {
                // Cập nhật thông tin trong đối tượng Subject
                selectedSubject.setSubjectID(subjectIDInput.getText());
                selectedSubject.setName(nameInput.getText());
                selectedSubject.setCredits(Integer.parseInt(creditsInput.getText()));

                // Cập nhật lại bảng
                table.refresh();

                // Cập nhật thông tin trong cơ sở dữ liệu
                updateSubjectInDatabase(selectedSubject);

                // Xóa các trường nhập liệu sau khi cập nhật
                clearInputs();
            } catch (Exception e) {
                System.out.println("Invalid data! Please enter valid information.");
            }
        }
    }

    private void clearInputs() {
        subjectIDInput.clear();
        nameInput.clear();
        creditsInput.clear();
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
