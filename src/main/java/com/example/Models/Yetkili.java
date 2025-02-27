package com.example.Models;

import javafx.beans.property.*;

public class Yetkili {
    private IntegerProperty id;
    private StringProperty ad;
    private StringProperty soyad;
    private StringProperty sifre ;
    private StringProperty mail;

    public Yetkili(int id, String ad, String soyad, String mail, String sifre) {
        this.id = new SimpleIntegerProperty(id);
        this.ad = new SimpleStringProperty(ad);
        this.soyad = new SimpleStringProperty(soyad);
        this.mail = new SimpleStringProperty(mail);
        this.sifre = new SimpleStringProperty(sifre);

    }
    public Yetkili(){

    }

    public IntegerProperty getId() {
        return id;
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(IntegerProperty id) {
        this.id = id;
    }

    public StringProperty getAd() {
        return ad;
    }

    public StringProperty adProperty() {
        return ad;
    }

    public void setAd(StringProperty ad) {
        this.ad = ad;
    }

    public StringProperty getSoyad() {
        return soyad;
    }

    public StringProperty soyadProperty() {
        return soyad;
    }

    public void setSoyad(StringProperty soyad) {
        this.soyad = soyad;
    }

    public StringProperty getSifre() {
        return sifre;
    }

    public StringProperty sifreProperty() {
        return sifre;
    }

    public void setSifre(StringProperty sifre) {
        this.sifre = sifre;
    }

    public StringProperty getMail() {
        return mail;
    }

    public StringProperty mailProperty() {
        return mail;
    }

    public void setMail(StringProperty mail) {
        this.mail = mail;
    }
}
