package com.example.Controller.Yetkili;

import com.example.Controller.BaseController;
import com.example.DataAccess.BasvuruDAO;
import com.example.DataAccess.OdaDAO;
import com.example.DataAccess.OgrenciDAO;
import com.example.Models.Basvuru;
import com.example.Models.Oda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BasvurulariGoruntuleController extends BaseController {
    @FXML
    private Button btnGeriDon;
    @FXML
    public void geriDon(){
        ilgiliSayfayaGit("/com/example/Application/Yetkili/yetkiliHome-view.fxml",btnGeriDon);
    }

    private BasvuruDAO basvuruDAO = new BasvuruDAO();
    private OdaDAO odaDao = new OdaDAO();
    private OgrenciDAO  ogrenciDAO = new OgrenciDAO();

    @FXML
    private TableView<Basvuru> basvuruTable;
    @FXML
    private TableColumn<Basvuru, Integer> basvuruIDColumn;
    @FXML
    private TableColumn<Basvuru, String> ogrenciAdColumn;
    @FXML
    private TableColumn<Basvuru, String> ogrenciSoyadColumn;
    @FXML
    private TableColumn<Basvuru, String> ogrenciNoColumn;
    @FXML
    private TableColumn<Basvuru, String> yurtTuruColumn;
    @FXML
    private TableColumn<Basvuru, LocalDate> tarihColumn;
    @FXML
    private TableColumn<Basvuru, String> aciklamaColumn;


    @FXML
    private TableView<Oda> odaTable;

    @FXML
    private TableColumn<Oda, Integer> odaIDColumn;
    @FXML
    private TableColumn<Oda, String> odaNoColumn;
    @FXML
    private TableColumn<Oda, Integer> odaKatColumn;
    @FXML
    private TableColumn<Oda, Integer> odaKapasiteColumn;
    @FXML
    private TableColumn<Oda, Integer> odaDolulukColumn;
    @FXML
    private TableColumn<Oda, String> odaYurtTuruColumn;

    @FXML
    public void initialize(){
        basvuruIDColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        ogrenciAdColumn.setCellValueFactory(cellData -> cellData.getValue().ogrenciAdProperty());
        ogrenciSoyadColumn.setCellValueFactory(cellData -> cellData.getValue().ogrenciSoyadProperty());
        ogrenciNoColumn.setCellValueFactory(cellData -> cellData.getValue().ogrenciOkulNoProperty());
        yurtTuruColumn.setCellValueFactory(cellData -> cellData.getValue().yurtTuruProperty());
        tarihColumn.setCellValueFactory(cellData -> cellData.getValue().basvuruTarihiProperty());
        aciklamaColumn.setCellValueFactory(cellData -> cellData.getValue().aciklamaProperty());

        List<Basvuru> basvurular = basvuruDAO.basvuruListele();
        ObservableList<Basvuru> basvuruObservableList = FXCollections.observableArrayList(basvurular);
        basvuruTable.setItems(basvuruObservableList);

        odaTable.setPlaceholder(new Label("Goruntulenecek Oda Yok"));

        odaIDColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        odaNoColumn.setCellValueFactory(cellData -> cellData.getValue().odaNoProperty());
        odaKatColumn.setCellValueFactory(cellData -> cellData.getValue().katProperty().asObject());
        odaKapasiteColumn.setCellValueFactory(cellData -> cellData.getValue().kapasiteProperty().asObject());
        odaDolulukColumn.setCellValueFactory(cellData -> cellData.getValue().mevcutDolulukProperty().asObject());
        odaYurtTuruColumn.setCellValueFactory(cellData -> cellData.getValue().kategoriAdiProperty());

        basvuruTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String yurtTuru = newValue.getYurtTuru();
                List<Oda> odalar = odaDao.atanacakOdalariListele(yurtTuru);
                ObservableList<Oda> observableOdalar = FXCollections.observableArrayList(odalar);
                odaTable.setItems(observableOdalar);
            }
        });

    }
    @FXML
    private Button btnOdaAta;

    @FXML
    public void odaAtamaYap(){
        Basvuru selectedBasvuru = basvuruTable.getSelectionModel().getSelectedItem();
        Oda selectedOda = odaTable.getSelectionModel().getSelectedItem();

        if (selectedOda == null || selectedBasvuru == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hata");
            alert.setHeaderText("Lütfen bir oda ve bir başvuru seçiniz.");
            alert.showAndWait();
        }

        int ogrenciID = selectedBasvuru.getOgrenciID();
        int odaID = selectedOda.getId().get();

        boolean atamaBasarili = ogrenciDAO.ogrenciyiOdayaYerlestir(ogrenciID, odaID);

        if (atamaBasarili){
            boolean silmeBasarili = basvuruDAO.basvuruSil(ogrenciID);
            if (silmeBasarili){
                basvuruTable.getItems().remove(selectedBasvuru);
                basvuruTable.refresh();
                if (selectedOda.getMevcutDoluluk().get() == selectedOda.getKapasite().get()){
                    odaTable.getItems().remove(selectedOda);
                    odaTable.refresh();
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Başarılı");
                alert.setHeaderText("Öğrenci başarıyla odaya yerleştirildi.");
                alert.showAndWait();
                initialize();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hata");
                alert.setHeaderText("Başvuru silinemedi.");
                alert.showAndWait();
            }
            }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hata");
            alert.setHeaderText("Öğrenci odaya yerleştirilemedi.");
            alert.showAndWait();
        }
    }



}
