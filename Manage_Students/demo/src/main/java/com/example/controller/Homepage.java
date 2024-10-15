package com.example.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Homepage {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    Label name;

    public void displayname(String username) {
        try {
            name.setText(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Signout(ActionEvent event) throws IOException {
        try {
            root = FXMLLoader.load(getClass().getResource("/com/example/index/SignIn.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    BorderPane mainpane;

    @FXML
    public void btnOnClickTrangchu(ActionEvent event) throws IOException {
        try {
            Loadmenu object = new Loadmenu();
            Pane view = object.getPage("Trangchu");
            mainpane.setCenter(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnOnClickSinhvien(ActionEvent event) throws IOException {
        try {
            Loadmenu object = new Loadmenu();
            Pane view = object.getPage("Student");
            mainpane.setCenter(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnOnClickMonhoc(ActionEvent event) throws IOException {
        try {
            Loadmenu object = new Loadmenu();
            Pane view = object.getPage("Them_mon");
            mainpane.setCenter(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnOnClickNhapdiem(ActionEvent event) throws IOException {
        try {
            Loadmenu object = new Loadmenu();
            Pane view = object.getPage("Nhap_diem");
            mainpane.setCenter(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnOnClickDangki(ActionEvent event) throws IOException {
        try {
            Loadmenu object = new Loadmenu();
            Pane view = object.getPage("Dangki");
            mainpane.setCenter(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnOnClickMyProfile(ActionEvent event) throws IOException {
        try {
            Loadmenu object = new Loadmenu();
            Pane view = object.getPage("MyProfile");
            mainpane.setCenter(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
