package com.example.Controller.Admin;

import com.example.Controller.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class OgrenciIslemleriController extends BaseController {
    @FXML
    private Button btnGeriDon;
    @FXML
    private Button btnOgrenciOlustur;
    @FXML
    private Button btnOgrenciListele;
    @FXML
    private Button btnOgrenciGuncelle;
    @FXML
    private Button btnOgrenciSil;

    @FXML
    public void geriDon(){
        ilgiliSayfayaGit("/com/example/Application/Admin/adminHome-view.fxml", btnGeriDon);
    }

    @FXML
    public void goOgrenciEkle(){
        ilgiliSayfayaGit("/com/example/Application/Admin/ogrenciEkle-view.fxml", btnOgrenciOlustur);
    }

    @FXML
    public void goOgrenciListele(){
        ilgiliSayfayaGit("/com/example/Application/Admin/OgrenciListele-view.fxml", btnOgrenciListele);
    }
}
