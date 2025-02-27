package com.example.Controller.Ogrenci;

import com.example.Controller.BaseController;
import com.example.DataAccess.BasvuruDAO;
import com.example.DataAccess.LoggedInStudent;
import com.example.DataAccess.YurtDAO;
import com.example.Models.Basvuru;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BasvuruGoruntuleController extends BaseController {

    @FXML
    public Button btnGeriDon;

    @FXML
    public void geriDon(){
        ilgiliSayfayaGit("/com/example/Application/Ogrenci/ogrenciHome-view.fxml", btnGeriDon);
    }

    private BasvuruDAO basvuruDAO = new BasvuruDAO();

    @FXML
    private ComboBox<String> comboYurtTuru;
    @FXML
    private TextField txtFiyat;
    @FXML
    private TextArea areaAciklama;
    @FXML
    private TextField txtBasvuruDate;
    @FXML
    private Label txtBasvuruDurumu;

    @FXML
    public void initialize() {
        int ogrenciID = LoggedInStudent.getOgrenciID();

        Basvuru basvuru = basvuruDAO.basvuruGoruntule(ogrenciID);
        YurtDAO yurtDAO = new YurtDAO();

        if (basvuru != null) {
            comboYurtTuru.setValue(basvuru.getYurtTuru());
            comboYurtTuru.getItems().addAll(yurtDAO.getYurtTurleri());
            int fiyat = yurtDAO.getYurtFiyat(comboYurtTuru.getValue());
            comboYurtTuru.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    int yeniFiyat = yurtDAO.getYurtFiyat(newValue);
                    txtFiyat.setText(String.valueOf(yeniFiyat) + " TL");
                }
            });

            txtFiyat.setText(String.valueOf(fiyat) + " TL");
            areaAciklama.setText(basvuru.getAciklama());
            txtBasvuruDate.setText(basvuru.getBasvuruTarihi().toString());
            txtBasvuruDurumu.setText(basvuru.getDurum());
            if (txtBasvuruDurumu.getText().equals("BEKLEMEDE")) {
                txtBasvuruDurumu.setStyle("-fx-background-radius: 5;-fx-background-color: orange");
            } else {
                txtBasvuruDurumu.setStyle("-fx-background-radius: 5;-fx-background-color: green");
            }

        }

    }

    @FXML
    public void basvuruGuncelle(){
        String yeniYurtTuru = comboYurtTuru.getValue();
        String yeniAciklama = areaAciklama.getText();
        int ogrenciID = LoggedInStudent.getOgrenciID();
        Basvuru basvuru = basvuruDAO.basvuruGoruntule(ogrenciID);
        if (basvuru.getYurtTuru().equals(yeniYurtTuru) && basvuru.getAciklama().equals(yeniAciklama)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Başvuru Güncellenemedi");
            alert.setHeaderText(null);
            alert.setContentText("Basvuru Güncellemek İçin Lütfen Bilgi Değişikliği Yapınız.");
            alert.showAndWait();
        }
        else{
            basvuru.setOgrenciID(ogrenciID);
            basvuru.setYurtTuru(yeniYurtTuru);
            basvuru.setAciklama(yeniAciklama);

            boolean basarili = basvuruDAO.basvuruGuncelle(basvuru);
            if (basarili){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Başvuru Güncellendi");
                alert.setHeaderText(null);
                alert.setContentText("Başvurunuz Başarıyla Güncellendi.");
                alert.showAndWait();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Başvuru Güncellenemedi");
                alert.setHeaderText(null);
                alert.setContentText("Başvurunuz Güncellenirken Bir Hata Oluştu.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private Button btnBasvuruSil;

    @FXML
    public void basvuruSil(){
        int ogrenciID = LoggedInStudent.getOgrenciID();
        Basvuru basvuru = new Basvuru();
        basvuru.setOgrenciID(ogrenciID);
        boolean basarili = basvuruDAO.basvuruSil(ogrenciID);
        if (basarili){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Başvuru Silindi");
            alert.setHeaderText(null);
            alert.setContentText("Başvurunuz Başarıyla Silindi.");
            alert.showAndWait();

            ilgiliSayfayaGit("/com/example/Application/Ogrenci/ogrenciHome-view.fxml", btnBasvuruSil);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Başvuru Silinemedi");
            alert.setHeaderText(null);
            alert.setContentText("Başvurunuz Silinirken Bir Hata Oluştu.");
            alert.showAndWait();
        }
    }
}
