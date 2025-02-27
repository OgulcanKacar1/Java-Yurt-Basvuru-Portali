package com.example.Models;

public class Admin {
    private String kullanici_adi;
    private String sifre;

    public Admin(String kullanici_adi, String sifre){
        this.kullanici_adi = kullanici_adi;
        this.sifre = sifre;
    }
    public Admin(){

    }

    public String getKullanici_adi() {
        return kullanici_adi;
    }

    public void setKullanici_adi(String kullanici_adi) {
        this.kullanici_adi = kullanici_adi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}
