package com.example.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class HelloController extends BaseController {

    @FXML
    private Button btnAdminGirisi;
    @FXML
    private Button btnYetkiliGirisi;
    @FXML
    private Button btnOgrenciGirisi;

    @FXML
    public void adminGirisSayfasi(ActionEvent actionEvent){
        ilgiliSayfayaGit("/com/example/Application/Admin/adminLogin-view.fxml", btnAdminGirisi);
    }

    public void ogrenciGirisSayfasi(ActionEvent actionEvent){
        ilgiliSayfayaGit("/com/example/Application/Ogrenci/ogrenciLogin-view.fxml", btnOgrenciGirisi);
    }

    public void yetkiliGirisSayfasi(ActionEvent actionEvent){
        ilgiliSayfayaGit("/com/example/Application/Yetkili/yetkiliLogin-view.fxml", btnYetkiliGirisi);
    }

}