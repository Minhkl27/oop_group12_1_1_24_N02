package com.example.controller;

import java.net.URL;

import com.example.Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class Loadmenu {

    private Pane view;

    public Pane getPage(String fileName) {
        try {
            URL fileUrl = Main.class.getResource("/com/example/index/" + fileName + ".fxml");
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("FXML file can not be found");
            }
            new FXMLLoader();
            view = FXMLLoader.load(fileUrl);
        } catch (Exception e) {
            System.out.println("No page " + fileName + " please check homepage.");
        }

        return view;
    }

}
