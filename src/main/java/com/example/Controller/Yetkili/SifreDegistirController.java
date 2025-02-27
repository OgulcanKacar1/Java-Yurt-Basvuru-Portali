package com.example.Controller.Yetkili;

import com.example.Controller.BaseController;
import com.example.DataAccess.YetkiliDAO;
import com.example.DataAccess.YetkiliLoginDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SifreDegistirController extends BaseController {
    @FXML
    private Button btnGeriDon;
    @FXML
    private TextField txtMail;
    @FXML
    private PasswordField txtEskiSifre;
    @FXML
    private PasswordField txtYeniSifre;
    @FXML
    private Button btnSifreDegistir;

    @FXML
    public void geriDon(){
       ilgiliSayfayaGit("/com/example/Application/Yetkili/yetkiliHome-view.fxml", btnGeriDon);
    }

    private final YetkiliLoginDAO yetkiliLoginDAO = new YetkiliLoginDAO();

    @FXML
    public void sifreDegistir(){
        String mail = txtMail.getText();
        String eskiSifre = txtEskiSifre.getText();
        String yeniSifre = txtYeniSifre.getText();

        if (mail.isBlank() || eskiSifre.isBlank() || yeniSifre.isBlank()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uyarı");
            alert.setHeaderText("Boş Alan Bırakmayınız.");
            alert.showAndWait();
            return;
        }else {
            boolean basarili = yetkiliLoginDAO.sifreDegistir(mail, eskiSifre, yeniSifre);
            if (basarili) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Başarılı");
                alert.setHeaderText("Şifre Değiştirme İşlemi Başarılı.");
                alert.showAndWait();
                temizle();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hata");
                alert.setHeaderText("Şifre Değiştirme İşlemi Başarısız.");
                alert.showAndWait();
            }
        }
    }

    public void temizle(){
        txtMail.clear();
        txtEskiSifre.clear();
        txtYeniSifre.clear();
    }
}
