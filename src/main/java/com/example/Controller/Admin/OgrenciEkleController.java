package com.example.Controller.Admin;

import com.example.Controller.BaseController;
import com.example.DataAccess.OgrenciDAO;
import com.example.Models.Ogrenci;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class OgrenciEkleController extends BaseController {

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
    @FXML
    private TextField lblOkulNo;

    private OgrenciDAO ogrenciDAO = new OgrenciDAO();

    @FXML
    public void geriDon(){
        ilgiliSayfayaGit("/com/example/Application/Admin/OgrenciIslemleri-view.fxml", btnGeriDon);
    }

    @FXML
    public void ogrenciEkle(){
        if (lblAd.getText().isEmpty() || lblSoyad.getText().isEmpty() || lblMail.getText().isEmpty() ||
                lblSifre.getText().isEmpty() || lblOkulNo.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Eksik Bilgi");
            alert.setHeaderText(null);
            alert.setContentText("Lütfen tüm alanları doldurun!");
            alert.showAndWait();
            return;
        }else if (ogrenciDAO.mailKontrol(lblMail.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hata");
            alert.setHeaderText("Bu mail adresi zaten bir öğrenciye ait!");
            alert.showAndWait();
            return;
        }



        Ogrenci ogrenci = new Ogrenci();
        ogrenci.setAd(new SimpleStringProperty(lblAd.getText()));
        ogrenci.setSoyad(new SimpleStringProperty(lblSoyad.getText()));
        ogrenci.setMail(new SimpleStringProperty(lblMail.getText()));
        ogrenci.setSifre(new SimpleStringProperty(lblSifre.getText()));
        ogrenci.setOkulNo(new SimpleStringProperty(lblOkulNo.getText()));

        boolean basarili = ogrenciDAO.ogrenciEkle(ogrenci);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (basarili){
            alert.setTitle("Başarılı");
            alert.setHeaderText(null);
            alert.setContentText("Öğrenci başarıyla eklendi!");
        }
        else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Hata");
            alert.setHeaderText(null);
            alert.setContentText("Öğrenci eklenirken bir hata oluştu!");
        }
        alert.showAndWait();
        temizleForm();
    }

    private void temizleForm() {
        lblAd.clear();
        lblSoyad.clear();
        lblSifre.clear();
        lblMail.clear();
        lblOkulNo.clear();
    }

}
