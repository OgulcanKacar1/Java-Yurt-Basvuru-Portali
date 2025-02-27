module com.example.yurtportali {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;
    requires java.naming;
    requires javafx.base;

    exports com.example.Application;
    opens com.example.Application to javafx.fxml;
    exports com.example.Controller;
    opens com.example.Controller to javafx.fxml;
    exports com.example.Controller.Admin;
    opens com.example.Controller.Admin to javafx.fxml;
    exports com.example.Controller.Ogrenci;
    opens com.example.Controller.Ogrenci to javafx.fxml;
    exports com.example.Controller.Yetkili;
    opens com.example.Controller.Yetkili to javafx.fxml;

}