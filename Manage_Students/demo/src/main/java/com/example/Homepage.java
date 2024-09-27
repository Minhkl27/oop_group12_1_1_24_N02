package com.example;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Homepage {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label name;

    public void displayname(String username) {
        name.setText(username);
    }
}
