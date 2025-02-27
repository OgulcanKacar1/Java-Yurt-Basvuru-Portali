package com.example.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Yurt {
    private IntegerProperty id;
    private StringProperty kategoriAdi;
    private IntegerProperty fiyat;

    public Yurt(){

    }

    public Yurt(int id, String kategoriAdi, int fiyat){
        this.id = new SimpleIntegerProperty(id);
        this.kategoriAdi = new SimpleStringProperty(kategoriAdi);
        this.fiyat = new SimpleIntegerProperty(fiyat);
    }

    public IntegerProperty getId(){
        return id;
    }
    public void setId(IntegerProperty id){

    }

    public StringProperty getKategoriAdi(){
        return kategoriAdi;
    }
    public void setKategoriAdi(StringProperty kategoriAdi){
        this.kategoriAdi = kategoriAdi;
    }

    public IntegerProperty getFiyat(){
        return fiyat;
    }
    public void setFiyat(IntegerProperty fiyat){
        this.fiyat = fiyat;
    }
}
