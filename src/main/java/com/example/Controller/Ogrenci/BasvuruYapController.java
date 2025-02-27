package com.example.Controller.Ogrenci;

import com.example.Controller.BaseController;
import com.example.DataAccess.BasvuruDAO;
import com.example.DataAccess.LoggedInStudent;
import com.example.DataAccess.OdaDAO;
import com.example.DataAccess.YurtDAO;
import com.example.Models.Basvuru;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Date;

public class BasvuruYapController extends BaseController {
    @FXML
    private Button btnGeriDon;
    @FXML
    private Button btnBasvuruYap;

    @FXML
    public void geriDon(){
        ilgiliSayfayaGit("/com/example/Application/Ogrenci/ogrenciHome-view.fxml", btnGeriDon);
    }

    @FXML
    private ComboBox<String> comboYurtTuru;
    @FXML
    private TextArea areaAciklama;
    @FXML
    private TextField txtFiyat;

    private YurtDAO yurtDAO = new YurtDAO();
    private OdaDAO odaDAO = new OdaDAO();

    @FXML
    public void initialize(){
        comboYurtTuru.getItems().addAll(yurtDAO.getYurtTurleri());

        comboYurtTuru.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int fiyat = yurtDAO.getYurtFiyat(newValue);
                txtFiyat.setText(String.valueOf(fiyat)+" TL");
            } else {
                txtFiyat.setText("");
            }
        });

    }

    @FXML
    public void basvuruEkle(){
        if (comboYurtTuru.getValue()==null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Eksik Bilgi");
            alert.setHeaderText(null);
            alert.setContentText("Lütfen tüm alanları doldurun!");
            alert.showAndWait();
            return;
        }

        Basvuru basvuru = new Basvuru();
        basvuru.setOgrenciID(LoggedInStudent.getOgrenciID());
        basvuru.setYurtTuru(comboYurtTuru.getValue());
        basvuru.setDurum("BEKLEMEDE");
        basvuru.setBasvuruTarihi(new Date(System.currentTimeMillis()).toLocalDate());
        basvuru.setAciklama(areaAciklama.getText());

        txtFiyat.setText(String.valueOf(yurtDAO.getYurtFiyat(comboYurtTuru.getValue())));

        BasvuruDAO basvuruDAO = new BasvuruDAO();
        boolean basarili = basvuruDAO.basvuruEkle(basvuru);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (basarili) {
            alert.setTitle("Başarılı");
            alert.setHeaderText(null);
            alert.setContentText("Başvurunuz alınmıştır.");
        } else {
            alert.setTitle("Hata");
            alert.setHeaderText(null);
            alert.setContentText("Başvurunuz alınamadı.");
        }
        alert.showAndWait();

        ilgiliSayfayaGit("/com/example/Application/Ogrenci/ogrenciHome-view.fxml", btnBasvuruYap);
    }

}
