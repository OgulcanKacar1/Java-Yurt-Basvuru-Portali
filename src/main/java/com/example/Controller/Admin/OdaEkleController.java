package com.example.Controller.Admin;

import com.example.Controller.BaseController;
import com.example.DataAccess.DBConnection;
import com.example.DataAccess.OdaDAO;
import com.example.DataAccess.YurtDAO;
import com.example.Models.Oda;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class OdaEkleController extends BaseController {
    @FXML
    private ComboBox<String> comboYurtKategori;
    @FXML
    private Spinner<Integer> spinnerKat;
    @FXML
    private TextField lblOdaNo;
    @FXML
    private Spinner<Integer> spinnerKapasite;
    @FXML
    private Button btnOdaEkle;
    @FXML
    private Button btnGeriDon;

    @FXML
    public void geriDon(){
        ilgiliSayfayaGit("/com/example/Application/Admin/odaIslemleri-view.fxml", btnGeriDon);
    }

    @FXML
    public void initialize(){
        YurtDAO yurtDAO = new YurtDAO();
        comboYurtKategori.getItems().addAll(yurtDAO.getYurtTurleri());

        spinnerKat.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4, 0));
        spinnerKapasite.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 4, 0));
    }

    private int setYurtIdFromKategori(Oda oda) {
        String kategoriAdi = comboYurtKategori.getValue();

        String query = "SELECT yurtID FROM Yurt WHERE kategoriAdi = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, kategoriAdi);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int yurtID = resultSet.getInt("yurtID");
                return yurtID;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @FXML
    public void odaEkle(){
        if (comboYurtKategori.getValue() == null || comboYurtKategori.getValue().isEmpty() ||
                lblOdaNo.getText() == null || lblOdaNo.getText().isEmpty() ||
                spinnerKapasite.getValue() == null || spinnerKapasite.getValue() == 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Eksik Bilgi");
            alert.setHeaderText(null);
            alert.setContentText("Lütfen tüm alanları doldurun!");
            alert.showAndWait();
            return;
        }
        OdaDAO odaDAO = new OdaDAO();
        if (odaDAO.odaNoVeYurtKontrol(lblOdaNo.getText(), comboYurtKategori.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hata");
            alert.setHeaderText("Bu oda numarası bu yurtta zaten mevcut!");
            alert.showAndWait();
            return;
        }

        Oda oda = new Oda();
        oda.setOdaNo(new SimpleStringProperty(lblOdaNo.getText()));
        oda.setKat(new SimpleIntegerProperty(spinnerKat.getValue()));
        oda.setKapasite(new SimpleIntegerProperty(spinnerKapasite.getValue()));
        oda.setYurtID(new SimpleIntegerProperty(setYurtIdFromKategori(oda)));

        boolean basarili = odaDAO.odaEkle(oda);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (basarili) {
            alert.setTitle("Başarılı");
            alert.setHeaderText(null);
            alert.setContentText("Oda başarıyla eklendi!");
        } else {
            alert.setTitle("Hata");
            alert.setHeaderText(null);
            alert.setContentText("Oda eklenirken bir hata oluştu!");
        }
        alert.showAndWait();
        temizleform();

    }

    public void temizleform(){
        lblOdaNo.clear();
        spinnerKat.getValueFactory().setValue(0);
        spinnerKapasite.getValueFactory().setValue(0);
        comboYurtKategori.setValue("");
    }
}
