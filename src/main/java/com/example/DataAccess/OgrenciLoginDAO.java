package com.example.DataAccess;

import com.example.Models.Ogrenci;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OgrenciLoginDAO {
    public Ogrenci getOgrenciByUsername(String mail) {
        String query = "SELECT * FROM Ogrenci WHERE mail = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, mail);
            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                int id = resultSet.getInt("ogrenciID");
                String sifre = resultSet.getString("sifre");
                String ad = resultSet.getString("ad");
                String soyad = resultSet.getString("soyad");
                String okulNo = resultSet.getString("okulNo");
                Boolean yurtDurumu = resultSet.getBoolean("yurtDurumu");
                Integer odaID = resultSet.getInt("odaID");
                LoggedInStudent.setOgrenciID(id);
                LoggedInStudent.setAd(ad);
                return new Ogrenci(id, ad, soyad, mail,sifre, okulNo, yurtDurumu, odaID);

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public boolean ogrenciGiris(String mail, String sifre) {
        Ogrenci ogrenci = getOgrenciByUsername(mail);

        if (ogrenci != null) {
            return ogrenci.getSifre().get().equals(sifre);
        }
        return false;
    }


    public boolean sifreDegistir(String mail, String eskiSifre, String yeniSifre) {
        Ogrenci ogrenci = getOgrenciByUsername(mail);

        if (ogrenci != null) {
            if (ogrenci.getSifre().get().equals(eskiSifre)){
                String query = "UPDATE Ogrenci SET sifre = ? WHERE mail = ?";

                try (Connection connection = DBConnection.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                    preparedStatement.setString(1, yeniSifre);
                    preparedStatement.setString(2, mail);

                    int result = preparedStatement.executeUpdate();

                    if (eskiSifre.equals(yeniSifre)){
                        return false;
                    }
                    return result > 0;

                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false;
    }
}

