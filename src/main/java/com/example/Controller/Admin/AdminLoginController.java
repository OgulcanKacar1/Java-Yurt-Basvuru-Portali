package com.example.Controller.Admin;

import com.example.Controller.BaseController;
import com.example.DataAccess.AdminLoginDAO;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.IOException;

public class AdminLoginController extends BaseController {
    @FXML
    private Button btnAdminGiris;

    @FXML
    private TextField txtKullaniciAdi;
    @FXML
    private PasswordField txtSifre;
    @FXML
    private Label girisBasarisizLabel;

    @FXML
    private Button btnGirisGeriTusu;

    @FXML
    public void goHello(){
        ilgiliSayfayaGit("/com/example/Application/hello-view.fxml", btnGirisGeriTusu);
    }

    private AdminLoginDAO adminLoginDAO = new AdminLoginDAO();

    @FXML
    public void adminGiris(){
        String kullaniciAdi = txtKullaniciAdi.getText();
        String sifre = txtSifre.getText();
        if (kullaniciAdi.isBlank() || sifre.isBlank()){
            girisBasarisizLabel.setText("Lütfen Kullanıcı veya Şifre Alanını Boş Bırakmayınız. ");
        } else if (adminLoginDAO.adminGiris(kullaniciAdi,sifre)) {
            girisBasarisizLabel.setText("Giriş Başarılı!");
            girisBasarisizLabel.setStyle("-fx-text-fill: green;");

            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> {
                ilgiliSayfayaGit("/com/example/Application/Admin/adminHome-view.fxml", btnAdminGiris);
            });
            pause.play();
        } else{
            girisBasarisizLabel.setText("Kullanıcı adı veya şifre yanlış.");
            girisBasarisizLabel.setStyle("-fx-text-fill: red;"); // Hata mesajı
        }
    }

}
