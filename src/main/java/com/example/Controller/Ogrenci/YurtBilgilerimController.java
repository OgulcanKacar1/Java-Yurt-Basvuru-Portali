package com.example.Controller.Ogrenci;

import com.example.Controller.BaseController;
import com.example.DataAccess.LoggedInStudent;
import com.example.DataAccess.OgrenciDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.util.Map;

public class YurtBilgilerimController extends BaseController {
    @FXML
    private Button btnGeriDon;
    @FXML
    public void geriDon(){
        ilgiliSayfayaGit("/com/example/Application/Ogrenci/ogrenciHome-view.fxml", btnGeriDon);
    }

    @FXML
    private TextField txtAd;
    @FXML
    private TextField txtSoyad;
    @FXML
    private TextField txtOkulNo;
    @FXML
    private TextField txtOdaNo;
    @FXML
    private TextField txtYurtKategori;

    private OgrenciDAO ogrenciDAO = new OgrenciDAO();

    @FXML
    public void initialize(){
        int ogrenciID = LoggedInStudent.getOgrenciID();

        Map<String ,String> bilgiler = ogrenciDAO.getOgrenciYurtBilgileri(ogrenciID);
        txtAd.setText(bilgiler.get("ogrenciAd"));
        txtSoyad.setText(bilgiler.get("ogrenciSoyad"));
        txtOkulNo.setText(bilgiler.get("ogrenciNo"));
        txtYurtKategori.setText(bilgiler.getOrDefault("yurtKategoriAdi","Yurt Bilgisi Bulunamadı"));
        txtOdaNo.setText(bilgiler.getOrDefault("odaNo","Oda Bilgisi Bulunamadı"));
    }

}
