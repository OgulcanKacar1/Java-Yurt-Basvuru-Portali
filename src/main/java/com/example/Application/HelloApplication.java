package com.example.Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;


public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Yurt Portalı");
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/com/example/Application/style.css").toExternalForm());
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}