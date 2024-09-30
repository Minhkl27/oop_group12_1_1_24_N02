package com.example.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField nameTextField;

    @FXML
    TextField password;

    private final String ACCOUNTadmin = "MinhNguyen";
    private final String PASSWORDadmin = "minh12345";

    private String userACCOUNT = "HieuVn";
    private String userPASSWORD = "hieu12345";

    private boolean handleLogin() {
        if (nameTextField.getText().equals(ACCOUNTadmin) && password.getText().equals(PASSWORDadmin)) {
            // Đăng nhập thành công
            showAlert(Alert.AlertType.INFORMATION, "Đăng Nhập Thành Công", "Admin access.");
            return true;
        } else if (nameTextField.getText().equals(userACCOUNT) && password.getText().equals(userPASSWORD)) {
            // Đăng nhập thành công
            showAlert(Alert.AlertType.INFORMATION, "Đăng Nhập Thành Công", "Bạn đã đăng nhập thành công.");
            return true;
        } else {
            // Đăng nhập thất bại
            showAlert(Alert.AlertType.ERROR, "Đăng Nhập Thất Bại", "Tên đăng nhập hoặc mật khẩu không chính xác.");
            return false;
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void btnSignInOnClicked(ActionEvent event) throws IOException {
        try {
            if (handleLogin()) {
                String username = nameTextField.getText();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/Homepage.fxml"));
                root = loader.load();

                Homepage homepage = loader.getController();
                homepage.displayname(username);
                // root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // finally {
        // String username = nameTextField.getText();

        // FXMLLoader loader = new FXMLLoader(getClass().getResource("Homepage.fxml"));
        // root = loader.load();

        // Homepage homepage = loader.getController();
        // homepage.displayname(username);
        // // root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
        // stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // scene = new Scene(root);
        // stage.setScene(scene);
        // stage.show();
        // }

    }
}