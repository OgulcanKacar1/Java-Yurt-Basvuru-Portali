package com.example.Controller.Ogrenci;

import com.example.Controller.BaseController;
import com.example.DataAccess.BasvuruDAO;
import com.example.DataAccess.LoggedInStudent;
import com.example.DataAccess.OgrenciDAO;
import com.example.Models.Ogrenci;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class OgrenciHomeController extends BaseController {
    @FXML
    private Button btnAnaSayfa;
    @FXML
    private Button btnYurtBilgilerim;
    @FXML
    private Button btnSifreDegistir;
    @FXML
    private Button btnBasvuruYap;
    @FXML
    private Button btnBasvurularim;

    @FXML
    private Label txtHosgeldin;


    private OgrenciDAO ogrenciDAO = new OgrenciDAO();

    @FXML
    public void initialize(){
        txtHosgeldin.setText("Hoşgeldin, " + LoggedInStudent.getAd());
    }


    public boolean isOgrenciYurtDurumu(int ogrenciID){
        System.out.println(ogrenciDAO.isOgrenciOdaDurumu(ogrenciID));
        return ogrenciDAO.isOgrenciOdaDurumu(ogrenciID);
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
    public void goYurtBilgilerim(){
        ilgiliSayfayaGit("/com/example/Application/Ogrenci/yurtBilgilerim-view.fxml", btnYurtBilgilerim);
    }

    @FXML
    public void goSifreDegistir(){
        ilgiliSayfayaGit("/com/example/Application/Ogrenci/sifreDegistir-view.fxml", btnSifreDegistir);
    }

    @FXML
    public void goBasvurularim(){
        if (isOgrenciBasvuruVar(LoggedInStudent.getOgrenciID())){
            ilgiliSayfayaGit("/com/example/Application/Ogrenci/basvuruGoruntule-view.fxml", btnBasvurularim);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mevcut Başvuru Bulunmamakta!");
            alert.setHeaderText(null);
            alert.setContentText("Mevcut başvurunuz bulunmamaktadır. Başvuru yapmak için 'Başvuru Yap' sekmesine tıklayınız.");
            alert.showAndWait();
        }

    }

    private boolean isOgrenciBasvuruVar(int ogrenciID) {
        BasvuruDAO basvuruDAO = new BasvuruDAO();
        return basvuruDAO.basvuruKontrol(ogrenciID);
    }


    @FXML
    public void goBasvuruYap(){
        if (isOgrenciBasvuruVar(LoggedInStudent.getOgrenciID())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mevcut Başvuru Bulunmakta!");
            alert.setHeaderText(null);
            alert.setContentText("Mevcut başvurunuz bulunmaktadır. Başvurunuzun durumunu görmek için 'Başvurularım' sekmesine tıklayınız.");
            alert.showAndWait();
        } else if (isOgrenciYurtDurumu(LoggedInStudent.getOgrenciID())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Zaten Bir Odada Kaliyorsunuz!");
            alert.setHeaderText(null);
            alert.setContentText("Mevcut yurt durumunuz bulunmaktadır. Yurt bilgilerinizi görmek için 'Yurt Bilgilerim' sekmesine tıklayınız.");
            alert.showAndWait();
        } else{
            ilgiliSayfayaGit("/com/example/Application/Ogrenci/basvuruYap-view.fxml", btnBasvuruYap);
        }
    }

}
