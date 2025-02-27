package com.example.DataAccess;

import com.example.Models.Ogrenci;
import com.example.Models.Yetkili;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class YetkiliDAO {
    public boolean yetkiliEke(Yetkili yetkili){
        String query = "INSERT INTO YurtYetkilisi (ad, soyad, mail, sifre) VALUES (?, ?, ?, ?)";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)

        ) {
            preparedStatement.setString(1, yetkili.getAd().get());
            preparedStatement.setString(2, yetkili.getSoyad().get());
            preparedStatement.setString(3, yetkili.getMail().get());
            preparedStatement.setString(4, yetkili.getSifre().get());

            int result = preparedStatement.executeUpdate();
            return result>0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Yetkili> yetkiliListele(){
        List<Yetkili> yetkililer = new ArrayList<>();
        String query = "SELECT * FROM YurtYetkilisi";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("gorevliID");
                String ad = resultSet.getString("ad");
                String soyad = resultSet.getString("soyad");
                String mail = resultSet.getString("mail");
                String sifre = resultSet.getString("sifre");

                Yetkili yetkili = new Yetkili(id, ad, soyad, mail,sifre);
                yetkililer.add(yetkili);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return yetkililer;
    }

    public boolean yetkiliGuncelle(Yetkili yetkili) {
        String query = "UPDATE YurtYetkilisi SET ad = ?, soyad = ?, mail = ?, sifre = ? WHERE gorevliID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, yetkili.getAd().get());
            preparedStatement.setString(2, yetkili.getSoyad().get());
            preparedStatement.setString(3, yetkili.getMail().get());
            preparedStatement.setString(4, yetkili.getSifre().get());
            preparedStatement.setInt(5, yetkili.getId().get());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean yetkiliSil(int id){
        String query = "DELETE FROM YurtYetkilisi WHERE gorevliID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean mailKontrol(String mail) {
        String sql = "SELECT COUNT(*) FROM YurtYetkilisi WHERE mail = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mail);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
