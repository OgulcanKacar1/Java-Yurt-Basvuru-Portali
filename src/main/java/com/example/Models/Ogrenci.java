package com.example.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.DataAccess.DBConnection;

import javafx.beans.property.*;

public class Ogrenci {
    private IntegerProperty id;
    private StringProperty ad;
    private StringProperty soyad;
    private StringProperty sifre;
    private StringProperty mail;
    private StringProperty okulNo;
    private BooleanProperty yurtDurumu;
    private IntegerProperty odaID;

    public Ogrenci(){

    }

    public Ogrenci(int id, String ad, String soyad, String mail, String sifre, String okulNo, Boolean yurtDurumu, Integer odaID) {
        this.id = new SimpleIntegerProperty(id);
        this.ad = new SimpleStringProperty(ad);
        this.soyad = new SimpleStringProperty(soyad);
        this.sifre = new SimpleStringProperty(sifre);
        this.mail = new SimpleStringProperty(mail);
        this.okulNo = new SimpleStringProperty(okulNo);
        this.yurtDurumu = new SimpleBooleanProperty(yurtDurumu);
        this.odaID = new SimpleIntegerProperty(odaID);
    }




    public IntegerProperty getId() {
        return id;
    }

    public void setId(IntegerProperty id) {
        this.id = id;
    }

    public StringProperty getAd() {
        return ad;
    }

    public void setAd(StringProperty ad) {
        this.ad = ad;
    }

    public StringProperty getSoyad() {
        return soyad;
    }

    public void setSoyad(StringProperty soyad) {
        this.soyad = soyad;
    }

    public StringProperty getSifre() {
        return sifre;
    }

    public void setSifre(StringProperty sifre) {
        this.sifre = sifre;
    }

    public StringProperty getMail() {
        return mail;
    }

    public void setMail(StringProperty mail) {
        this.mail = mail;
    }

    public StringProperty getOkulNo() {
        return okulNo;
    }

    public void setOkulNo(StringProperty okulNo) {
        this.okulNo = okulNo;
    }

    public BooleanProperty isYurtDurumu() {
        return yurtDurumu;
    }

    public void setYurtDurumu(BooleanProperty yurtDurumu) {
        this.yurtDurumu = yurtDurumu;
    }

    public IntegerProperty getOdaID() {
        return odaID;
    }

    public void setOdaID(IntegerProperty odaID) {
        this.odaID = odaID;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setAd(String ad) {
        this.ad.set(ad);
    }

    public void setSoyad(String soyad) {
        this.soyad.set(soyad);
    }

    public void setSifre(String sifre) {
        this.sifre.set(sifre);
    }

    public void setMail(String mail) {
        this.mail.set(mail);
    }

    public void setOkulNo(String okulNo) {
        this.okulNo.set(okulNo);
    }

    public void setYurtDurumu(boolean yurtDurumu) {
        this.yurtDurumu.set(yurtDurumu);
    }


    public void setOdaID(int odaID) {
        this.odaID.set(odaID);
    }


    public StringProperty getOdaNo() {
    String query = "SELECT odaNo FROM Oda WHERE odaID = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        pstmt.setInt(1, this.getOdaID().get());
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            return new SimpleStringProperty(rs.getString("odaNo"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return new SimpleStringProperty("");
}
}
