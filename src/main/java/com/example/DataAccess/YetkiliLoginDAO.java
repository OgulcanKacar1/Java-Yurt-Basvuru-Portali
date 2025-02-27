package com.example.DataAccess;

import com.example.Models.Yetkili;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class YetkiliLoginDAO {

    public Yetkili getYetkiliByUsername(String mail) {
        String query = "SELECT * FROM YurtYetkilisi WHERE mail = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, mail);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("gorevliID");
                String ad = resultSet.getString("ad");
                String soyad = resultSet.getString("soyad");
                String sifre = resultSet.getString("sifre");

                LoggedInYetkili.setAd(ad);
                return new Yetkili(id, ad, soyad, mail, sifre);



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean yetkiliGiris(String mail, String sifre) {
        Yetkili yetkili = getYetkiliByUsername(mail);

        if (yetkili != null) {
            String dbSifre = yetkili.getSifre().get();
            return dbSifre.equals(sifre);
        }
        return false;
    }

    public boolean sifreDegistir(String mail, String eskiSifre, String yeniSifre) {
        Yetkili yetkili = getYetkiliByUsername(mail);

        if (yetkili != null) {
            if (yetkili.getSifre().get().equals(eskiSifre)){
                String query = "UPDATE YurtYetkilisi SET sifre = ? WHERE mail = ?";

                try (Connection connection = DBConnection.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                    preparedStatement.setString(1, yeniSifre);
                    preparedStatement.setString(2, mail);

                    int result = preparedStatement.executeUpdate();
                    if (eskiSifre.equals(yeniSifre)) {
                        return false;
                    } else {
                        return result > 0;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}