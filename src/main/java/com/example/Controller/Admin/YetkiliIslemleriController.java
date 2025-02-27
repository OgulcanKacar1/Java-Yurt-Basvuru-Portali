package com.example.Controller.Admin;

import com.example.Controller.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class YetkiliIslemleriController extends BaseController {
    @FXML
    private Button btnGeriDon;
    @FXML
    public void geriDon(){
        ilgiliSayfayaGit("/com/example/Application/Admin/adminHome-view.fxml", btnGeriDon);
    }

    @FXML
    private Button btnYetkiliOlustur;
    @FXML
    private Button btnYetkiliListele;

    @FXML
    public void goYetkiliOlustur(){
        ilgiliSayfayaGit("/com/example/Application/Admin/yetkiliEkle-view.fxml",btnYetkiliOlustur);
    }

    @FXML
    public void goYetkiliListele(){
        ilgiliSayfayaGit("/com/example/Application/Admin/yetkiliListele-view.fxml",btnYetkiliListele);

    }


}
