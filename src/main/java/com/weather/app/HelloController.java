package com.weather.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void atLogOn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("weather-screen.fxml"));
    }


}