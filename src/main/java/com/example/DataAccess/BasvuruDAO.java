package com.example.DataAccess;

import com.example.Models.Basvuru;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.sql.Date;

public class BasvuruDAO {


    public boolean basvuruEkle(Basvuru basvuru) {
        String query = "INSERT INTO Basvuru (ogrenciID, yurtTuru, durum, basvuruTarihi, aciklama) VALUES (?, ?, ?, ?,?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, basvuru.getOgrenciID());
            preparedStatement.setString(2, basvuru.getYurtTuru());
            preparedStatement.setString(3, basvuru.getDurum());
            preparedStatement.setDate(4, Date.valueOf(basvuru.getBasvuruTarihi()));
            preparedStatement.setString(5, basvuru.getAciklama());

            int result = preparedStatement.executeUpdate();
            return result > 0;

        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean basvuruKontrol(int ogrenciID) {
        String query = "SELECT COUNT(*) FROM Basvuru WHERE ogrenciID = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, ogrenciID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Eğer sonuç 0'dan büyükse, başvuru var demektir.
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Basvuru basvuruGoruntule(int ogrenciID) {
        Basvuru basvuru = null;
        String query = "SELECT * FROM Basvuru WHERE ogrenciID = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, ogrenciID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                int basvuruID = resultSet.getInt("basvuruID");
                String yurtTuru = resultSet.getString("yurtTuru");
                String durum = resultSet.getString("durum");
                LocalDate basvuruTarihi = resultSet.getDate("basvuruTarihi").toLocalDate();
                String aciklama = resultSet.getString("aciklama");
                basvuru = new Basvuru(basvuruID,ogrenciID, yurtTuru, durum, basvuruTarihi, aciklama);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return basvuru;
    }

    public boolean basvuruGuncelle(Basvuru basvuru){
        String query = "UPDATE Basvuru SET yurtTuru = ?, aciklama = ? WHERE ogrenciID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setString(1, basvuru.getYurtTuru());
            preparedStatement.setString(2, basvuru.getAciklama());
            preparedStatement.setInt(3, basvuru.getOgrenciID());

            int result = preparedStatement.executeUpdate();
            return result > 0;
    }catch (SQLException e){
        e.printStackTrace();
        return false;
        }
    }

    public boolean basvuruSil(int ogrenciID) {
        String query = "DELETE FROM Basvuru WHERE ogrenciID = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, ogrenciID);

            int result = preparedStatement.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ObservableList<Basvuru> basvuruListele() {
        ObservableList<Basvuru> basvurular = FXCollections.observableArrayList();
        String query = "SELECT b.basvuruID, yurtTuru, durum, basvuruTarihi, aciklama, o.ogrenciID, o.ad, o.soyad,o.okulNo FROM Basvuru b JOIN Ogrenci o ON b.ogrenciID = o.ogrenciID";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int basvuruID = resultSet.getInt("basvuruID");
                int ogrenciID = resultSet.getInt("ogrenciID");
                String yurtTuru = resultSet.getString("yurtTuru");
                String durum = resultSet.getString("durum");
                Date basvuruTarihi = Date.valueOf(resultSet.getDate("basvuruTarihi").toLocalDate());
                String aciklama = resultSet.getString("aciklama");
                String ad = resultSet.getString("ad");
                String soyad = resultSet.getString("soyad");
                String okulNo = resultSet.getString("okulNo");

                Basvuru basvuru = new Basvuru(basvuruID, ogrenciID, yurtTuru, durum, basvuruTarihi.toLocalDate(), aciklama, ad, soyad,okulNo);
                basvurular.add(basvuru);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return basvurular;
    }




}
