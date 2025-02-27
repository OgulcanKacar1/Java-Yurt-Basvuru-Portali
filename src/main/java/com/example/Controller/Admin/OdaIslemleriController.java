package com.example.Controller.Admin;

import com.example.Controller.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OdaIslemleriController extends BaseController {
    @FXML
    private Button btnGeriDon;
    @FXML
    private Button btnOdaOlustur;
    @FXML
    private Button btnOdaListele;

    @FXML
    public void geriDon(){
        ilgiliSayfayaGit("/com/example/Application/Admin/adminHome-view.fxml",btnGeriDon);
    }


    @FXML
    public void goOdaOlustur(){
        ilgiliSayfayaGit("/com/example/Application/Admin/odaEkle-view.fxml",btnOdaOlustur);
    }

    @FXML
    public void goOdaListele(){
        ilgiliSayfayaGit("/com/example/Application/Admin/odaListeleme-view.fxml",btnOdaListele);
    }

}
