package com.example.DataAccess;

import com.example.Models.Oda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OdaDAO {
    private DBConnection connection;

    public OdaDAO() {
        DBConnection.getConnection();
    }

    public boolean odaEkle(Oda oda) {
        String query = "INSERT INTO Oda (odaNo, kat, kapasite,mevcutDoluluk,yurtID) VALUES (?,?,?,0,?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, oda.getOdaNo().get());
            preparedStatement.setInt(2, oda.getKat().get());
            preparedStatement.setInt(3, oda.getKapasite().get());
            preparedStatement.setInt(4, oda.getYurtID().get());

            int result = preparedStatement.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Oda> odaListele() {
        List<Oda> odalar = new ArrayList<>();
        String query = "SELECT o.*, y.kategoriAdi as yurtKategorisi " +
                "FROM Oda o " +
                "JOIN Yurt y ON o.yurtID = y.yurtID";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("odaID");
                String odaNo = resultSet.getString("odaNo");
                int kat = resultSet.getInt("kat");
                int kapasite = resultSet.getInt("kapasite");
                int mevcutDoluluk = resultSet.getInt("mevcutDoluluk");
                int yurtID = resultSet.getInt("yurtID");
                String yurtKategorisi = resultSet.getString("yurtKategorisi");

                Oda oda = new Oda(id, odaNo, kat, kapasite, mevcutDoluluk, yurtID, yurtKategorisi);
                odalar.add(oda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return odalar;
    }

    public boolean odaSil(int id) {
        String query = "DELETE FROM Oda WHERE odaID = ?";

        OgrenciDAO ogrenciDAO = new OgrenciDAO();

        ogrenciDAO.ogrenciYurtDurumuGuncelle(id);

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);


            int result = preparedStatement.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean odaGuncelle(Oda oda) {
        String query = "UPDATE Oda SET odaNo = ?, kat = ?, kapasite = ? WHERE odaID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, oda.getOdaNo().get());
            preparedStatement.setInt(2, oda.getKat().get());
            preparedStatement.setInt(3, oda.getKapasite().get());
            preparedStatement.setInt(4, oda.getId().get());

            int result = preparedStatement.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getKapasite() {
        List<String> kapasiteler = new ArrayList<>();
        String query = "SELECT DISTINCT kapasite FROM Oda";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int kapasite = resultSet.getInt("kapasite");
                kapasiteler.add(String.valueOf(kapasite));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kapasiteler;
    }

    public boolean odaNoVeYurtKontrol(String odaNo, String kategoriAdi) {
        String sql = "SELECT COUNT(*) \n" +
                "FROM Oda o\n" +
                "JOIN Yurt y ON o.yurtID = y.yurtID\n" +
                "WHERE o.odaNo = ? AND y.kategoriAdi = ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, odaNo);
            pstmt.setString(2, kategoriAdi);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> getYurtTurleri() {
        List<String> yurtTurleri = new ArrayList<>();
        String query = "SELECT DISTINCT kategoriAdi FROM Yurt";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String yurtTuru = resultSet.getString("kategoriAdi");
                yurtTurleri.add(yurtTuru);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return yurtTurleri;
    }

    public Oda odaGetir(Integer odaID) {
        String query = "SELECT * FROM Oda WHERE odaID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, odaID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Oda(resultSet.getInt("odaID"), resultSet.getString("odaNo"), resultSet.getInt("kat"), resultSet.getInt("kapasite"), resultSet.getInt("mevcutDoluluk"), resultSet.getInt("yurtID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean odaMevcutDolulukGuncelle(Oda oda) {
        String query = "UPDATE Oda SET mevcutDoluluk = ? WHERE odaID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, oda.getMevcutDoluluk().get());
            statement.setInt(2, oda.getId().get());
            int result = statement.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Oda> atanacakOdalariListele(String yurtTuru) {
        String query = "SELECT * FROM Oda o JOIN Yurt y ON o.yurtID = y.yurtID WHERE y.kategoriAdi = ? AND o.mevcutDoluluk < o.kapasite";
        List<Oda> odalar = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, yurtTuru);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("odaID");
                String odaNo = resultSet.getString("odaNo");
                int kat = resultSet.getInt("kat");
                int kapasite = resultSet.getInt("kapasite");
                int mevcutDoluluk = resultSet.getInt("mevcutDoluluk");
                int yurtID = resultSet.getInt("yurtID");
                String kategoriAdi = resultSet.getString("kategoriAdi");

                Oda oda = new Oda(id, odaNo, kat, kapasite, mevcutDoluluk, yurtID, kategoriAdi);
                odalar.add(oda);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return odalar;
    }
}