package com.example.controller;

import java.io.IOException;
import com.example.entity.AccountManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField nameTextField;

    @FXML
    TextField passwordTextField;

    private String ACCOUNTadmin = "MinhNguyen";
    private String PASSWORDadmin = "minh12345";

    String filePath = "File/loginAccount.txt";
    AccountManager accountManager = new AccountManager(filePath);

    @FXML
    public void btnSignInOnClicked(ActionEvent event) throws IOException {
        try {
            String username = nameTextField.getText();
            String password = passwordTextField.getText();

            if (username.equals(ACCOUNTadmin) && password.equals(PASSWORDadmin)) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/index/HomepageAD.fxml"));
                root = loader.load();
                Homepage homepage = loader.getController();
                homepage.displayname(username);
                // root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else if (accountManager.checkLogin(username, password)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/index/HomepageUser.fxml"));
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

    }
}