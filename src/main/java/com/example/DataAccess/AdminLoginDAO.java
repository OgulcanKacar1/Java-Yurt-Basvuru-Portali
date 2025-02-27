package com.example.DataAccess;

import com.example.Models.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginDAO {
    public Admin getAdminByUsername(String kullanici_adi) {
        String query = "SELECT * FROM Admin WHERE kullanici_adi = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, kullanici_adi);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Eğer kullanıcı adı varsa, Admin nesnesi oluştur
            if (resultSet.next()) {
                String password = resultSet.getString("parola");
                return new Admin(kullanici_adi, password);  // Admin nesnesini döndür
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Kullanıcı adı bulunamazsa null döner
    }

    public boolean adminGiris(String kullanici_adi, String sifre) {
        Admin admin = getAdminByUsername(kullanici_adi);

        if (admin != null) {
            return admin.getSifre().equals(sifre);
        }
        return false;
    }


}
