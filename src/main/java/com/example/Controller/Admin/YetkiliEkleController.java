package com.example.Controller.Admin;

import com.example.Controller.BaseController;
import com.example.DataAccess.YetkiliDAO;
import com.example.Models.Yetkili;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class YetkiliEkleController extends BaseController {
    @FXML
    private Button btnGeriDon;
    @FXML
    private TextField lblAd;
    @FXML
    private TextField lblSoyad;
    @FXML
    private TextField lblSifre;
    @FXML
    private TextField lblMail;

    private YetkiliDAO yetkiliDAO = new YetkiliDAO();

    @FXML
    public void geriDon(){
        ilgiliSayfayaGit("/com/example/Application/Admin/yetkiliIslemleri-view.fxml",btnGeriDon);
    }


    @FXML
    public void yetkiliEkle() {
        if (lblAd.getText().isEmpty() || lblSoyad.getText().isEmpty() || lblMail.getText().isEmpty() ||
                lblSifre.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Eksik Bilgi");
            alert.setHeaderText(null);
            alert.setContentText("Lütfen Tüm Alanları Doldurun!");
            alert.showAndWait();
            return;
        }else if (yetkiliDAO.mailKontrol(lblMail.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hata");
            alert.setHeaderText("Bu mail adresi zaten bir yetkiliye ait!");
            alert.showAndWait();
            return;
        }

        Yetkili yetkili = new Yetkili();
        yetkili.setAd(new SimpleStringProperty(lblAd.getText()));
        yetkili.setSoyad(new SimpleStringProperty(lblSoyad.getText()));
        yetkili.setMail(new SimpleStringProperty(lblMail.getText()));
        yetkili.setSifre(new SimpleStringProperty(lblSifre.getText()));

        boolean basarili = yetkiliDAO.yetkiliEke(yetkili);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (basarili){
            alert.setTitle("Başarılı");
            alert.setHeaderText(null);
            alert.setContentText("Yetkili başarıyla eklendi!");
        }
        else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Hata");
            alert.setHeaderText(null);
            alert.setContentText("Yetkili eklenirken bir hata oluştu!");
        }
        alert.showAndWait();
        temizleForm();
    }
    private void temizleForm() {
        lblAd.clear();
        lblSoyad.clear();
        lblSifre.clear();
        lblMail.clear();

    }

}
