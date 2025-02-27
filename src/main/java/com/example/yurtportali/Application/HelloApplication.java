package com.example.yurtportali.Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;

import com.example.DataAccess.DBConnection;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Yurt Portalı");
        primaryStage.setScene(new Scene(root,800,500));
        //p0.rimaryStage.setResizable(false);
        primaryStage.show();

        DBConnection.getConnection();


    }

    public static void main(String[] args) {
        launch();
    }
}