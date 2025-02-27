package com.example.Models;

import com.example.DataAccess.DBConnection;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Oda {
    private IntegerProperty id;
    private StringProperty odaNo;
    private IntegerProperty kat;
    private IntegerProperty kapasite;
    private IntegerProperty mevcutDoluluk;
    private IntegerProperty yurtID;
    private StringProperty kategoriAdi;

    public Oda(int id, String odaNo, int kat, int kapasite, int mevcutDoluluk, int yurtID) {
        this.id = new SimpleIntegerProperty(id);
        this.odaNo = new SimpleStringProperty(odaNo);
        this.kat = new SimpleIntegerProperty(kat);
        this.kapasite = new SimpleIntegerProperty(kapasite);
        this.mevcutDoluluk = new SimpleIntegerProperty(mevcutDoluluk);
        this.yurtID = new SimpleIntegerProperty(yurtID);
    }
    public Oda(){

    }

    public Oda(int id, String odaNo, int kat, int kapasite,int mevcutDoluluk, int yurtID, String kategoriAdi) {
        this.id = new SimpleIntegerProperty(id);
        this.odaNo = new SimpleStringProperty(odaNo);
        this.kat = new SimpleIntegerProperty(kat);
        this.kapasite = new SimpleIntegerProperty(kapasite);
        this.mevcutDoluluk = new SimpleIntegerProperty(mevcutDoluluk);
        this.yurtID = new SimpleIntegerProperty(yurtID);
        this.kategoriAdi = new SimpleStringProperty(kategoriAdi);
    }



    public IntegerProperty  getId() {
        return id;
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(IntegerProperty  id) {
        this.id= id;
    }

    public StringProperty getOdaNo() {
        return odaNo;
    }

    public StringProperty odaNoProperty() {
        return odaNo;
    }

    public void setOdaNo(StringProperty odaNo) {
        this.odaNo= odaNo;
    }

    public IntegerProperty  getKat() {
        return kat;
    }

    public IntegerProperty katProperty() {
        return kat;
    }

    public void setKat(IntegerProperty  kat) {
        this.kat= kat;
    }

    public IntegerProperty  getKapasite() {
        return kapasite;
    }

    public IntegerProperty kapasiteProperty() {
        return kapasite;
    }

    public void setKapasite(IntegerProperty  kapasite) {
        this.kapasite= kapasite;
    }

    public IntegerProperty  getMevcutDoluluk() {
        return mevcutDoluluk;
    }

    public IntegerProperty mevcutDolulukProperty() {
        return mevcutDoluluk;
    }

    public void setMevcutDoluluk(IntegerProperty  mevcutDoluluk) {
        this.mevcutDoluluk= mevcutDoluluk;
    }

    public IntegerProperty  getYurtID() {
        return yurtID;
    }

    public IntegerProperty yurtIDProperty() {
        return yurtID;
    }

    public void setYurtID(IntegerProperty  yurtID) {
        this.yurtID= yurtID;
    }

    public String getKategoriAdi() {
        return kategoriAdi.get();
    }

    public StringProperty kategoriAdiProperty() {
        return kategoriAdi;
    }

    public void setKategoriAdi(String kategoriAdi) {
        this.kategoriAdi.set(kategoriAdi);
    }

}
