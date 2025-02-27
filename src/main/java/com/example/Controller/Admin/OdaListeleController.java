package com.example.Controller.Admin;

import com.example.Controller.BaseController;
import com.example.DataAccess.OdaDAO;
import com.example.Models.Oda;
import com.example.Models.Ogrenci;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class OdaListeleController extends BaseController {
    @FXML
    private Button btnGeriDon;
    @FXML
    public void geriDon(){
        ilgiliSayfayaGit("/com/example/Application/Admin/odaIslemleri-view.fxml",btnGeriDon);
    }

    private Oda selectedOda;
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
    private TextField lblOdaNo;
    @FXML
    private TextField lblKat;
    @FXML
    private TextField lblKapasite;

    @FXML
    private TableColumn<Oda, String> kategoriColumn;


    private OdaDAO odaDAO = new OdaDAO();

    @FXML
    public void initialize(){
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        odaNoColumn.setCellValueFactory(cellData -> cellData.getValue().getOdaNo());
        katColumn.setCellValueFactory(cellData -> cellData.getValue().getKat().asObject());
        kapasiteColumn.setCellValueFactory(cellData -> cellData.getValue().getKapasite().asObject());
        dolulukColumn.setCellValueFactory(cellData -> cellData.getValue().getMevcutDoluluk().asObject());
        kategoriColumn.setCellValueFactory(cellData -> cellData.getValue().kategoriAdiProperty());

        List<Oda> odalar = odaDAO.odaListele();
        ObservableList<Oda> observableOdalar = FXCollections.observableArrayList(odalar);

        odaTable.setItems(observableOdalar);

        odaTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            selectedOda = newValue;
            if (selectedOda!=null){
                selectedOda = newValue;
                lblOdaNo.setText(selectedOda.getOdaNo().get());
                lblKat.setText(String.valueOf(selectedOda.getKat().get()));
                lblKapasite.setText(String.valueOf(selectedOda.getKapasite().get()));
            }
        });
    }


    @FXML
    private Button btnOdaSil;
    @FXML
    public void odaSil(){
        if(selectedOda != null){
            OdaDAO odaDAO = new OdaDAO();

            boolean basarili = odaDAO.odaSil(selectedOda.getId().get());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (basarili){
                alert.setTitle("Başarılı");
                alert.setHeaderText(null);
                alert.setContentText("Oda başarıyla silindi");
                clear();
                odaTable.getItems().remove(selectedOda);
            } else {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("Hata");
                alert.setHeaderText(null);
                alert.setContentText("Oda silinirken bir hata oluştu");
            }
            alert.showAndWait();

        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uyarı");
            alert.setHeaderText(null);
            alert.setContentText("Lütfen bir oda seçin");
            alert.showAndWait();
        }

    }

    @FXML
    private Button btnOdaGuncelle;

    @FXML
    private void odaGuncelle(){
        String yeniOdaNo;
        int yeniKat;
        int yeniKapasite;
        Oda selectedOda = odaTable.getSelectionModel().getSelectedItem();

        if (selectedOda!=null){
            yeniOdaNo = lblOdaNo.getText();
            yeniKat = Integer.parseInt(lblKat.getText());
            yeniKapasite = Integer.parseInt(lblKapasite.getText());

            if (selectedOda.getOdaNo().get().equals(yeniOdaNo) && selectedOda.getKat().get() == yeniKat && selectedOda.getKapasite().get() == yeniKapasite){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Uyarı");
                alert.setHeaderText(null);
                alert.setContentText("Değişiklik yapmadınız");
                alert.showAndWait();
                return;
            }

            selectedOda.setOdaNo(new SimpleStringProperty(yeniOdaNo));
            selectedOda.setKat(new SimpleIntegerProperty(yeniKat));
            selectedOda.setKapasite(new SimpleIntegerProperty(yeniKapasite));

            boolean basarili = odaDAO.odaGuncelle(selectedOda);

            if (basarili){
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Başarılı");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Oda başarıyla güncellendi");
                successAlert.showAndWait();
                clear();
                odaTable.refresh();
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Hata");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Oda güncellenirken bir hata oluştu");
                errorAlert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Uyarı");
            alert.setHeaderText(null);
            alert.setContentText("Lütfen güncellenecek odayı seçin");
            alert.showAndWait();
        }
    }

    @FXML
    public void clear(){
        lblOdaNo.clear();
        lblKat.clear();
        lblKapasite.clear();
    }

}
