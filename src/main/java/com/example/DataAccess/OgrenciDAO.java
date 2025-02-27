package com.example.DataAccess;

import com.example.Models.Ogrenci;
import javafx.beans.property.StringProperty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OgrenciDAO {
    public boolean ogrenciEkle(Ogrenci ogrenci) {
        String query = "INSERT INTO Ogrenci (ad, soyad, sifre, mail, okulNo, yurtDurumu, odaID) VALUES (?, ?, ?, ?, ?, null, null)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, ogrenci.getAd().get());
            preparedStatement.setString(2, ogrenci.getSoyad().get());
            preparedStatement.setString(3, ogrenci.getSifre().get());
            preparedStatement.setString(4, ogrenci.getMail().get());
            preparedStatement.setString(5, ogrenci.getOkulNo().get());


            int result = preparedStatement.executeUpdate();
            return result > 0;


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ogrenciSil(int id) {
        String query = "DELETE FROM Ogrenci WHERE ogrenciID = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1,id);

            int result = preparedStatement.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Ogrenci> ogrenciListele() {
        List<Ogrenci> ogrenciler = new ArrayList<>();
        String query = "SELECT * FROM Ogrenci"; 

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ogrenciID");
                String ad = resultSet.getString("ad");
                String soyad = resultSet.getString("soyad");
                String sifre = resultSet.getString("sifre");
                String mail = resultSet.getString("mail");
                String okulNo = resultSet.getString("okulNo");
                Boolean yurtDurumu = resultSet.getBoolean("yurtDurumu");  // Yurt durumu alınıyor
                Integer odaID = resultSet.getInt("odaID");

                Ogrenci ogrenci = new Ogrenci(id, ad, soyad, mail,sifre,okulNo,yurtDurumu,odaID);
                ogrenciler.add(ogrenci);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ogrenciler;
    }


    public boolean ogrenciGuncelle(Ogrenci ogrenci){
        String query = "UPDATE Ogrenci SET ad=?, soyad = ?, mail = ?, okulNo = ? WHERE ogrenciID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, ogrenci.getAd().get());
            preparedStatement.setString(2, ogrenci.getSoyad().get());
            preparedStatement.setString(3, ogrenci.getMail().get());
            preparedStatement.setString(4, ogrenci.getOkulNo().get());
            preparedStatement.setInt(5,ogrenci.getId().get());

            int result = preparedStatement.executeUpdate();
            return result > 0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    public Map<String, String> getOgrenciYurtBilgileri(int ogrenciID){
        Map<String, String> bilgiler = new HashMap<>();
        String query = """
            SELECT 
                ad AS ogrenciAd,
                soyad AS ogrenciSoyad,
                okulNo AS ogrenciNo,
                odaNo AS odaNo,
                kategoriAdi AS yurtKategoriAdi
            FROM 
                Ogrenci o
            LEFT JOIN 
                Oda od ON o.odaID = od.odaID
            LEFT JOIN 
                Yurt y ON od.yurtID = y.yurtID
            WHERE 
                o.ogrenciID = ?;
        """;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, ogrenciID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                bilgiler.put("ogrenciAd", resultSet.getString("ogrenciAd"));
                bilgiler.put("ogrenciSoyad", resultSet.getString("ogrenciSoyad"));
                bilgiler.put("ogrenciNo", resultSet.getString("ogrenciNo"));
                bilgiler.put("odaNo", resultSet.getString("odaNo") != null ? resultSet.getString("odaNo") : "Oda Bilgisi Bulunamadı");
                bilgiler.put("yurtKategoriAdi", resultSet.getString("yurtKategoriAdi") != null ? resultSet.getString("yurtKategoriAdi") : "Yurt Bilgisi Bulunamadı");

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return bilgiler;
    }

    public boolean mailKontrol(String mail) {
        String sql = "SELECT COUNT(*) FROM Ogrenci WHERE mail = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, mail);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean ogrenciYurtDurumuGuncelle(Ogrenci ogrenci){
        String query = "UPDATE Ogrenci SET yurtDurumu = ?, odaID = NULL WHERE ogrenciID = ?";
        String query1  = "UPDATE Oda SET mevcutDoluluk = mevcutDoluluk - 1 WHERE odaID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
            PreparedStatement statement1 = connection.prepareStatement(query1)){

            statement.setBoolean(1, ogrenci.isYurtDurumu().get());
            statement.setInt(2, ogrenci.getId().get());
            statement1.setInt(1, ogrenci.getOdaID().get());
            int result = statement.executeUpdate();
            int result1 = statement1.executeUpdate();
            return (result > 0 && result1 > 0);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean ogrenciYurtDurumuGuncelle(int odaID){
        String query = "UPDATE Ogrenci SET yurtDurumu = false, odaID = NULL WHERE odaID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, odaID);

            int result = statement.executeUpdate();
            return result > 0;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean ogrenciyiOdayaYerlestir(int ogrenciID, int odaID){
        String ogrenciQuery = "UPDATE Ogrenci SET yurtDurumu = 1, odaID = ? WHERE ogrenciID = ?";
        String odaQuery ="UPDATE Oda SET mevcutDoluluk = mevcutDoluluk + 1 WHERE odaID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement1 = connection.prepareStatement(ogrenciQuery);
             PreparedStatement statement2 = connection.prepareStatement(odaQuery)){

            statement1.setInt(1, odaID);
            statement1.setInt(2, ogrenciID);
            statement2.setInt(1, odaID);

            int result1 = statement1.executeUpdate();
            int result2 = statement2.executeUpdate();
            return (result1 > 0 && result2 > 0);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean isOgrenciOdaDurumu(int ogrenciID){
        String query = "SELECT COUNT(*) FROM Ogrenci WHERE ogrenciID = ? AND yurtDurumu = 1";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1, ogrenciID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                return resultSet.getInt(1) > 0;
            }
    }catch (SQLException e){
        e.printStackTrace();
    }
        return false;
    }
}
