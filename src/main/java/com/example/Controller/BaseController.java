package com.example.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.io.IOException;

public class BaseController {

    private Button btn;


    public void ilgiliSayfayaGit(String fxmlpath, Button btn){
        try {
            FXMLLoader fxml = new FXMLLoader(getClass().getResource(fxmlpath));
            Parent root = fxml.load();

            Scene scene = btn.getScene();
            scene.setRoot(root);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void sifreUnuttumPopUp(){
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle("Bilgilendirme");
        alert.setHeaderText(null);
        alert.setContentText("Lütfen Destek Hizmetleri İle İletişime Geçiniz");
        alert.showAndWait();
    }
}
