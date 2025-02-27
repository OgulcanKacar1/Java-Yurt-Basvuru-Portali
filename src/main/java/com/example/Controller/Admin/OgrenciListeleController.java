package com.example.Controller.Admin;

import com.example.Controller.BaseController;
import com.example.DataAccess.OgrenciDAO;
import com.example.Models.Ogrenci;
import com.example.Models.Yetkili;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;


public class OgrenciListeleController extends BaseController {

    private Ogrenci selectedOgrenci;
    @FXML
    private TableView<Ogrenci> ogrenciTable;

    @FXML
    private TableColumn<Ogrenci, Integer> idColumn;

    @FXML
    private TableColumn<Ogrenci, String> adColumn;

    @FXML
    private TableColumn<Ogrenci, String> soyadColumn;
    @FXML
    private TableColumn<Ogrenci, String> mailColumn;
    @FXML
    private TableColumn<Ogrenci, String> sifreColumn;
    @FXML
    private TableColumn<Ogrenci, String> okulNoColumn;
    @FXML
    private TableColumn<Ogrenci, Boolean> yurtDurumuColumn;
    @FXML
    private TableColumn<Ogrenci, Integer> odaIDColumn;

    private OgrenciDAO ogrenciDAO;

    @FXML
    private Button btnGeriDon;

    @FXML
    public void geriDon(){
        ilgiliSayfayaGit("/com/example/Application/Admin/OgrenciIslemleri-view.fxml",btnGeriDon);
    }


    public OgrenciListeleController() {
        ogrenciDAO = new OgrenciDAO();
    }


    @FXML
    public void initialize(){

        idColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        adColumn.setCellValueFactory(cellData -> cellData.getValue().getAd());
        soyadColumn.setCellValueFactory(cellData -> cellData.getValue().getSoyad());
        okulNoColumn.setCellValueFactory(cellData -> cellData.getValue().getOkulNo());
        sifreColumn.setCellValueFactory(cellData -> cellData.getValue().getSifre());
        mailColumn.setCellValueFactory(cellData -> cellData.getValue().getMail());
        yurtDurumuColumn.setCellValueFactory(cellData -> cellData.getValue().isYurtDurumu());
        odaIDColumn.setCellValueFactory(cellData -> cellData.getValue().getOdaID().asObject());  // IntegerProperty için asObject() kullanılır.

        List<Ogrenci> ogrenciler = ogrenciDAO.ogrenciListele();
        ObservableList<Ogrenci> observableOgrenciler = FXCollections.observableArrayList(ogrenciler);

        ogrenciTable.setItems(observableOgrenciler);


        ogrenciTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedOgrenci = newValue;
            if (selectedOgrenci!=null){
                selectedOgrenci = newValue;
                lblAd.setText(selectedOgrenci.getAd().get());
                lblSoyad.setText(selectedOgrenci.getSoyad().get());
                lblMail.setText(selectedOgrenci.getMail().get());
                lblOkulNo.setText(selectedOgrenci.getOkulNo().get());
                lblSifre.setText(selectedOgrenci.getSifre().get());
            }
        });

    }


    @FXML
    private Button btnOgrenciSil;
    @FXML
    public void OgrenciSil(){
        if (selectedOgrenci != null) {
            OgrenciDAO ogrenciDAO = new OgrenciDAO();

            boolean basarili = ogrenciDAO.ogrenciSil(selectedOgrenci.getId().get());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (basarili) {
                alert.setTitle("Başarılı");
                alert.setHeaderText(null);
                alert.setContentText("Öğrenci başarıyla silindi!");

                ogrenciTable.getItems().remove(selectedOgrenci);
                clear();
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Hata");
                alert.setHeaderText(null);
                alert.setContentText("Öğrenci silinirken bir hata oluştu!");
            }
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uyarı");
            alert.setHeaderText(null);
            alert.setContentText("Lütfen silinecek öğrenciyi seçin!");
            alert.showAndWait();
        }
    }



    @FXML
    private TextField lblAd;
    @FXML
    private TextField lblSoyad;
    @FXML
    private TextField lblMail;
    @FXML
    private TextField lblSifre;
    @FXML
    private TextField lblOkulNo;


    @FXML
    private void ogrenciGuncelle() {
        String yeniAd ;
        String yeniSoyad ;
        String yeniMail;
        String yeniSifre;
        String yeniOkulNo;

        Ogrenci selectedOgrenci = ogrenciTable.getSelectionModel().getSelectedItem();
        if (selectedOgrenci !=null){
            yeniAd = lblAd.getText();
            yeniSoyad = lblSoyad.getText();
            yeniMail = lblMail.getText();
            yeniSifre = lblSifre.getText();
            yeniOkulNo = lblOkulNo.getText();

            if (selectedOgrenci.getAd().get().equals(yeniAd) && selectedOgrenci.getSoyad().get().equals(yeniSoyad) && selectedOgrenci.getMail().get().equals(yeniMail) && selectedOgrenci.getSifre().get().equals(yeniSifre) && selectedOgrenci.getOkulNo().get().equals(yeniOkulNo)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Uyarı");
                alert.setHeaderText(null);
                alert.setContentText("Lütfen En Az 1 Bir Bilgi Değiştirin!");
                alert.showAndWait();
                return;
            }

            selectedOgrenci.setAd(new SimpleStringProperty(yeniAd));
            selectedOgrenci.setSoyad(new SimpleStringProperty(yeniSoyad));
            selectedOgrenci.setMail(new SimpleStringProperty(yeniMail));
            selectedOgrenci.setOkulNo(new SimpleStringProperty(yeniOkulNo));
            selectedOgrenci.setSifre(new SimpleStringProperty(yeniSifre));

            boolean basarili = ogrenciDAO.ogrenciGuncelle(selectedOgrenci);

            if (basarili) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Başarılı");
                successAlert.setHeaderText("Öğrenci bilgisi güncellendi.");
                successAlert.showAndWait();

                ogrenciTable.refresh();
                clear();

            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Hata");
                errorAlert.setHeaderText("Öğrenci bilgisi güncellenemedi!");
                errorAlert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uyarı");
            alert.setHeaderText(null);
            alert.setContentText("Lütfen güncellenecek öğrenciyi seçin!");
            alert.showAndWait();
        }


    }

    @FXML
    public void clear(){
        lblAd.clear();
        lblSoyad.clear();
        lblMail.clear();
        lblSifre.clear();
        lblOkulNo.clear();
    }

}
