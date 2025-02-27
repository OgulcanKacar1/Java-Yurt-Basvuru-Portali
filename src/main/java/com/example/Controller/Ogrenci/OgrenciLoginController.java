package com.example.Controller.Ogrenci;

import com.example.Controller.BaseController;
import com.example.DataAccess.LoggedInStudent;
import com.example.DataAccess.OgrenciLoginDAO;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class OgrenciLoginController extends BaseController {

    @FXML
    private Button btnGirisGeriTusu;
    @FXML
    private Button btnGiris;
    @FXML
    private TextField txtKullaniciAdi;
    @FXML
    private TextField txtSifre;
    @FXML
    private Label girisBasarisizLabel;


    @FXML
    public void goHello(){
        ilgiliSayfayaGit("/com/example/Application/hello-view.fxml", btnGirisGeriTusu);

    }


    private OgrenciLoginDAO ogrenciLoginDAO = new OgrenciLoginDAO();

    @FXML
    public void ogrenciGiris(){
        String kullaniciAdi = txtKullaniciAdi.getText();
        String sifre = txtSifre.getText();
        if (kullaniciAdi.isBlank() || sifre.isBlank()){
            girisBasarisizLabel.setText("Lütfen Kullanıcı veya Şifre Alanını Boş Bırakmayınız. ");
        } else if (ogrenciLoginDAO.ogrenciGiris(kullaniciAdi,sifre)) {
            girisBasarisizLabel.setText("Giriş Başarılı!");
            girisBasarisizLabel.setStyle("-fx-text-fill: green;");
            PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(2));
            pause.setOnFinished(e->{ilgiliSayfayaGit("/com/example/Application/Ogrenci/ogrenciHome-view.fxml",btnGiris);});
            pause.play();
        }else {
            girisBasarisizLabel.setText("Kullanıcı Adı veya Şifre Hatalı!");
            girisBasarisizLabel.setStyle("-fx-text-fill: red;");
        }
    }

}
