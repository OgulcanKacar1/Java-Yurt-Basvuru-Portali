package com.example.Models;

import javafx.beans.property.*;

import java.sql.Date;
import java.time.LocalDate;

public class Basvuru {
    private IntegerProperty id;
    private IntegerProperty ogrenciID;
    private StringProperty yurtTuru;
    private StringProperty durum;
    private ObjectProperty<LocalDate> basvuruTarihi;
    private StringProperty aciklama;
    private StringProperty ogrenciAd;
    private StringProperty ogrenciSoyad;
    private  StringProperty ogrenciOkulNo;

    public Basvuru(){
        this.id = new SimpleIntegerProperty(0);
        this.ogrenciID = new SimpleIntegerProperty(0);
        this.yurtTuru = new SimpleStringProperty("");
        this.durum = new SimpleStringProperty("");
        this.aciklama = new SimpleStringProperty("");
        this.basvuruTarihi = new SimpleObjectProperty<>(LocalDate.now());
    }

    public Basvuru(int id, int ogrenciID, String yurtTuru, String durum, LocalDate basvuruTarihi, String aciklama) {
        this.id = new SimpleIntegerProperty(id);
        this.ogrenciID = new SimpleIntegerProperty(ogrenciID);
        this.yurtTuru = new SimpleStringProperty(yurtTuru);
        this.durum = new SimpleStringProperty(durum);
        this.basvuruTarihi = new SimpleObjectProperty<>(basvuruTarihi);
        this.aciklama = new SimpleStringProperty(aciklama);
    }

    public Basvuru(int id, int ogrenciID, String yurtTuru, String Durum, LocalDate basvuruTarihi, String aciklama, String ogrenciAd, String ogrenciSoyad, String ogrenciOkulNo) {
        this.id = new SimpleIntegerProperty(id);
        this.ogrenciID = new SimpleIntegerProperty(ogrenciID);
        this.yurtTuru = new SimpleStringProperty(yurtTuru);
        this.durum = new SimpleStringProperty(Durum);
        this.basvuruTarihi = new SimpleObjectProperty<>(basvuruTarihi);
        this.aciklama = new SimpleStringProperty(aciklama);
        this.ogrenciAd = new SimpleStringProperty(ogrenciAd);
        this.ogrenciSoyad = new SimpleStringProperty(ogrenciSoyad);
        this.ogrenciOkulNo = new SimpleStringProperty(ogrenciOkulNo);

    }



    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getOgrenciID() {
        return ogrenciID.get();
    }

    public IntegerProperty ogrenciIDProperty() {
        return ogrenciID;
    }

    public void setOgrenciID(int ogrenciID) {
        this.ogrenciID.set(ogrenciID);
    }

    public String getYurtTuru() {
        return yurtTuru.get();
    }

    public StringProperty yurtTuruProperty() {
        return yurtTuru;
    }

    public void setYurtTuru(String yurtTuru) {
        this.yurtTuru.set(yurtTuru);
    }

    public String getDurum() {
        return durum.get();
    }

    public StringProperty durumProperty() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum.set(durum);
    }

    public ObjectProperty<LocalDate> basvuruTarihiProperty() {
        return basvuruTarihi;
    }

    public LocalDate getBasvuruTarihi() {
        return basvuruTarihi.get();
    }

    public void setBasvuruTarihi(LocalDate basvuruTarihi) {
        this.basvuruTarihi.set(basvuruTarihi);
    }

    public String getAciklama() {
        return aciklama.get();
    }

    public StringProperty aciklamaProperty() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama.set(aciklama);
    }

    public String getOgrenciAd() {
        return ogrenciAd.get();
    }

    public StringProperty ogrenciAdProperty() {
        return ogrenciAd;
    }

    public void setOgrenciAd(String ogrenciAd) {
        this.ogrenciAd.set(ogrenciAd);
    }

    public String getOgrenciSoyad() {
        return ogrenciSoyad.get();
    }

    public StringProperty ogrenciSoyadProperty() {
        return ogrenciSoyad;
    }

    public void setOgrenciSoyad(String ogrenciSoyad) {
        this.ogrenciSoyad.set(ogrenciSoyad);
    }

    public String getOgrenciOkulNo() {
        return ogrenciOkulNo.get();
    }

    public StringProperty ogrenciOkulNoProperty() {
        return ogrenciOkulNo;
    }

    public void setOgrenciOkulNo(String ogrenciOkulNo) {
        this.ogrenciOkulNo.set(ogrenciOkulNo);
    }
}
