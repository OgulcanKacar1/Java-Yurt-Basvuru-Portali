package com.example.DataAccess;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class YurtDAO {
    public List<String> getYurtTurleri() {
        List<String> yurtTurleri = new ArrayList<>();
        String query = "SELECT kategoriAdi FROM yurt";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String kategoriAdi = resultSet.getString("kategoriAdi");
                yurtTurleri.add(kategoriAdi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return yurtTurleri;
    }

    public int getYurtFiyat(String kategori){
        int fiyat = 0;
        String query = "SELECT fiyat FROM yurt WHERE kategoriAdi = ?";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1,kategori);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    fiyat = resultSet.getInt("fiyat");
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return fiyat;
    }
}