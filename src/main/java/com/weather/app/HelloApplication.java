package com.weather.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String loginScreenCSS = this.getClass().getResource("loginScreen.css").toExternalForm();
        scene.getStylesheets().add(loginScreenCSS);

        stage.setTitle("Weather app");
        Path cwd = Path.of("").toAbsolutePath();
        System.out.println(cwd);
        Image titleIcon = new Image("file:Media\\Icons\\TitleBarIcon.png");
        stage.getIcons().add(titleIcon);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}