package com.example.Controller.Yetkili;

import com.example.Controller.BaseController;
import com.example.DataAccess.LoggedInYetkili;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class YetkiliHomeController extends BaseController {
    @FXML
    private Button btnAnaSayfa;
    @FXML
    private Button btnSifreDegistir;
    @FXML
    private Button btnOdalariGoruntule;
    @FXML
    private Button btnBasvurulariGoruntule;
    @FXML
    private Label txtHosgeldin;

    @FXML
    public void initialize(){
        txtHosgeldin.setText("Hoşgeldin, " + LoggedInYetkili.getAd());
    }

    @FXML
    public void goAnaSayfa(){
        ilgiliSayfayaGit("/com/example/Application/hello-view.fxml", btnAnaSayfa);
    }

    @FXML
    public void cikisYap(){
        Platform.exit();
    }

    @FXML
    public void goSifreDegistir(){
        ilgiliSayfayaGit("/com/example/Application/Yetkili/sifreDegistir-view.fxml", btnSifreDegistir);
    }

    @FXML
    public void goOdalariGoruntule(){
        ilgiliSayfayaGit("/com/example/Application/Yetkili/odalariGoruntule-view.fxml", btnOdalariGoruntule);
    }

    @FXML
    public void goBasvurulariGoruntule(){
        ilgiliSayfayaGit("/com/example/Application/Yetkili/basvurulariGoruntule-view.fxml", btnBasvurulariGoruntule);
    }

}
