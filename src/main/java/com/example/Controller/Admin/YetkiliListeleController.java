package com.example.Controller.Admin;

import com.example.Controller.BaseController;
import com.example.DataAccess.OgrenciDAO;
import com.example.DataAccess.YetkiliDAO;
import com.example.Models.Yetkili;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class YetkiliListeleController extends BaseController {

    @FXML
    private Button btnGeriDon;

    public void geriDon(){
        ilgiliSayfayaGit("/com/example/Application/Admin/yetkiliIslemleri-view.fxml",btnGeriDon);
    }

    @FXML
    private TextField lblAd;
    @FXML
    private TextField lblSoyad;
    @FXML
    private TextField lblMail;
    @FXML
    private TextField lblSifre;

    private Yetkili selectedYetkili;

    @FXML
    private TableView<Yetkili> yetkiliTable;
    @FXML
    private TableColumn<Yetkili, Integer> idColumn;

    @FXML
    private TableColumn<Yetkili, String> adColumn;

    @FXML
    private TableColumn<Yetkili, String> soyadColumn;
    @FXML
    private TableColumn<Yetkili, String> mailColumn;
    @FXML
    private TableColumn<Yetkili, String> sifreColumn;

    private YetkiliDAO yetkiliDAO;

    public YetkiliListeleController(){
        yetkiliDAO = new YetkiliDAO();
    }

    @FXML
    public void initialize(){

        idColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        adColumn.setCellValueFactory(cellData -> cellData.getValue().getAd());
        soyadColumn.setCellValueFactory(cellData -> cellData.getValue().getSoyad());
        mailColumn.setCellValueFactory(cellData -> cellData.getValue().getMail());
        sifreColumn.setCellValueFactory(cellData -> cellData.getValue().getSifre());


        List<Yetkili> yetkililer = yetkiliDAO.yetkiliListele();
        ObservableList<Yetkili> observableYetkililer = FXCollections.observableArrayList(yetkililer);

        yetkiliTable.setItems(observableYetkililer);


        yetkiliTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedYetkili = newValue;
            if (selectedYetkili != null) {
                selectedYetkili = newValue;

                lblAd.setText(selectedYetkili.getAd().get());
                lblSoyad.setText(selectedYetkili.getSoyad().get());
                lblMail.setText(selectedYetkili.getMail().get());
                lblSifre.setText(selectedYetkili.getSifre().get());
            }
        });



    }


    @FXML
    public void yetkiliGuncelle(){
        String yeniAd ;
        String yeniSoyad ;
        String yeniMail;
        String yeniSifre;
        if (selectedYetkili!=null){
            yeniAd = lblAd.getText();
            yeniSoyad = lblSoyad.getText();
            yeniMail = lblMail.getText();
            yeniSifre = lblSifre.getText();

            if (selectedYetkili.getAd().get().equals(yeniAd) && selectedYetkili.getSoyad().get().equals(yeniSoyad) && selectedYetkili.getMail().get().equals(yeniMail) && selectedYetkili.getSifre().get().equals(yeniSifre)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Uyarı");
                alert.setHeaderText(null);
                alert.setContentText("Lütfen En Az 1 Bir Bilgi Değiştirin!");
                alert.showAndWait();
                return;
            }

            selectedYetkili.setAd(new SimpleStringProperty(yeniAd));
            selectedYetkili.setSoyad(new SimpleStringProperty(yeniSoyad));
            selectedYetkili.setMail(new SimpleStringProperty(yeniMail));
            selectedYetkili.setSifre(new SimpleStringProperty(yeniSifre));

            boolean basarili = yetkiliDAO.yetkiliGuncelle(selectedYetkili);

            if (basarili){
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Başarılı");
                successAlert.setHeaderText("Yetkili bilgisi güncellendi.");
                successAlert.showAndWait();
                clear();
                yetkiliTable.refresh();
            }
            else{
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Hata");
                errorAlert.setHeaderText("Yetkili bilgisi güncellenemedi!");
                errorAlert.showAndWait();
            }


        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uyarı");
            alert.setHeaderText(null);
            alert.setContentText("Lütfen güncellenecek yetkiliyi seçin!");
            alert.showAndWait();
        }
    }

    @FXML
    private Button btnYetkiliSil;
    @FXML
    public void yetkiliSil(){
        if (selectedYetkili !=null){
            YetkiliDAO yetkiliDAO = new YetkiliDAO();

            boolean basarili = yetkiliDAO.yetkiliSil(selectedYetkili.getId().get());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (basarili) {
                alert.setTitle("Başarılı");
                alert.setHeaderText(null);
                alert.setContentText("Öğrenci başarıyla silindi!");

                yetkiliTable.getItems().remove(selectedYetkili);
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
    public void clear(){
        lblAd.clear();
        lblSoyad.clear();
        lblMail.clear();
        lblSifre.clear();
    }



}







