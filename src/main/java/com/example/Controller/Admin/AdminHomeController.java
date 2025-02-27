package com.example.Controller.Admin;

import com.example.Controller.BaseController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminHomeController  extends BaseController {
    @FXML
    private Button btnOgrenciIslemi;
    @FXML
    private Button btnYetkiliIslemi;
    @FXML
    private Button btnOdaIslemi;


    @FXML
    private Button btnAnaSayfa;


    @FXML
    public void ogrenciIslemiSayfasi(){
        ilgiliSayfayaGit("/com/example/Application/Admin/ogrenciIslemleri-view.fxml", btnOgrenciIslemi);
    }

    @FXML
    public void yetkiliIslemiSayfasi(){
        ilgiliSayfayaGit("/com/example/Application/Admin/yetkiliIslemleri-view.fxml", btnYetkiliIslemi);
    }

    @FXML
    public void odaIslemiSayfasi(){
        ilgiliSayfayaGit("/com/example/Application/Admin/odaIslemleri-view.fxml", btnOdaIslemi);
    }

    @FXML
    public void cikisYap(){
        Platform.exit();
    }

    @FXML
    public void goAnaSayfa(){
        ilgiliSayfayaGit("/com/example/Application/hello-view.fxml", btnAnaSayfa);
    }
}
