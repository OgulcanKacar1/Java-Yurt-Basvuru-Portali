package com.example.Controller.Yetkili;

import java.util.List;

import com.example.DataAccess.OgrenciDAO;
import com.example.Controller.BaseController;
import com.example.DataAccess.OdaDAO;
import com.example.Models.Oda;
import com.example.Models.Ogrenci;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class odalariGoruntuleController extends BaseController {
    private Oda selectedOda;
    @FXML
    private ComboBox<String> comboYurtTuru;
    @FXML
    private TableView<Oda> odaTable;
    @FXML
    private TableColumn<Oda, Integer> idColumn;
    @FXML
    private TableColumn<Oda, String> odaNoColumn;
    @FXML
    private TableColumn<Oda, Integer> katColumn;
    @FXML
    private TableColumn<Oda, Integer> kapasiteColumn;
    @FXML
    private TableColumn<Oda, Integer> dolulukColumn;
    @FXML
    private TableColumn<Oda, String> yurtTuruColumn;

    private OdaDAO odaDAO = new OdaDAO();
    private OgrenciDAO ogrenciDAO = new OgrenciDAO();

    private Ogrenci selectedOgrenci;

    @FXML
    private TableView<Ogrenci> ogrenciTable;
    @FXML
    private TableColumn<Ogrenci, Integer> ogrenciIdColumn;
    @FXML
    private TableColumn<Ogrenci, String> ogrenciAdColumn;
    @FXML
    private TableColumn<Ogrenci, String> ogrenciSoyadColumn;
    @FXML
    private TableColumn<Ogrenci, String> ogrenciMailColumn;
    @FXML
    private TableColumn<Ogrenci, String> ogrenciOkulNoColumn;
    @FXML
    private TableColumn<Ogrenci, String> ogrenciOdaNoColumn;
    @FXML
    private Button btnOgrenciSil;
    @FXML
    private Button btnGeriDon;


    @FXML
    public void geriDon(){
        ilgiliSayfayaGit("/com/example/Application/Yetkili/yetkiliHome-view.fxml",btnGeriDon);
    }


    private void filtreliOdalariGoster(String yurtTuru) {
        List<Oda> tumOdalar = odaDAO.odaListele();
        ObservableList<Oda> filtreliOdalar;
        
        if (yurtTuru.equals("Tümü")) {
            filtreliOdalar = FXCollections.observableArrayList(tumOdalar);
        } else {
            filtreliOdalar = FXCollections.observableArrayList(
                tumOdalar.stream()
                    .filter(oda -> oda.getKategoriAdi().equals(yurtTuru))
                    .toList()
            );
        }
        
        odaTable.setItems(filtreliOdalar);
    }

    @FXML
    public void initialize(){
        List<String> yurtTurleri = odaDAO.getYurtTurleri(); 
        yurtTurleri.add(0, "Tümü"); 
        comboYurtTuru.setItems(FXCollections.observableArrayList(yurtTurleri));
        comboYurtTuru.setValue("Tümü");

        comboYurtTuru.setOnAction(event -> {
            String secilenYurtTuru = comboYurtTuru.getValue();
            if (secilenYurtTuru != null) {
                filtreliOdalariGoster(secilenYurtTuru);
            }
        });
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        odaNoColumn.setCellValueFactory(cellData -> cellData.getValue().getOdaNo());
        katColumn.setCellValueFactory(cellData -> cellData.getValue().getKat().asObject());
        kapasiteColumn.setCellValueFactory(cellData -> cellData.getValue().getKapasite().asObject());
        dolulukColumn.setCellValueFactory(cellData -> cellData.getValue().getMevcutDoluluk().asObject());
        yurtTuruColumn.setCellValueFactory(cellData -> cellData.getValue().kategoriAdiProperty());

        List<Oda> odalar = odaDAO.odaListele();
        ObservableList<Oda> observableOdalar = FXCollections.observableArrayList(odalar);

        odaTable.setItems(observableOdalar);

        odaTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            selectedOda = newValue;
            if (selectedOda!=null){
                List<Ogrenci> odadakiOgrenciler = ogrenciDAO.ogrenciListele().stream()
                .filter(ogrenci -> ogrenci.getOdaID() != null && ogrenci.getOdaID().get() == selectedOda.getId().get()).toList();
        ogrenciTable.setItems(FXCollections.observableArrayList(odadakiOgrenciler));
    } else {
        ogrenciTable.getItems().clear();
            }
        });


        ogrenciIdColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        ogrenciAdColumn.setCellValueFactory(cellData -> cellData.getValue().getAd());
        ogrenciSoyadColumn.setCellValueFactory(cellData -> cellData.getValue().getSoyad());
        ogrenciMailColumn.setCellValueFactory(cellData -> cellData.getValue().getMail());
        ogrenciOkulNoColumn.setCellValueFactory(cellData -> cellData.getValue().getOkulNo());
        ogrenciOdaNoColumn.setCellValueFactory(cellData -> cellData.getValue().getOdaNo());


        ogrenciTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            selectedOgrenci = newValue;
        });


    }

    @FXML
    public void ogrenciSil(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(selectedOgrenci != null){
            selectedOgrenci.setOdaID(0);
            selectedOgrenci.setYurtDurumu(false);
            
            if(odaDAO.odaMevcutDolulukGuncelle(selectedOda)){
            ogrenciDAO.ogrenciYurtDurumuGuncelle(selectedOgrenci);

            selectedOda.getMevcutDoluluk().set(selectedOda.getMevcutDoluluk().get() - 1);
            odaDAO.odaMevcutDolulukGuncelle(selectedOda);
            ogrenciTable.getItems().remove(selectedOgrenci);
            ogrenciTable.refresh();
            odaTable.refresh();
            alert.setTitle("Başarılı");
            alert.setHeaderText(null);
            alert.setContentText("Öğrenci odadan başarıyla silindi");
            alert.showAndWait();

            
        }else{
            alert.setTitle("Hata");
            alert.setHeaderText(null);
            alert.setContentText("Öğrenci odadan silinemedi");
            alert.showAndWait();
        }
        
    }else{
        alert.setTitle("Hata");
        alert.setHeaderText(null);
        alert.setContentText("Öğrenci seçilmedi");
        alert.showAndWait();
        }
    
    }
}
