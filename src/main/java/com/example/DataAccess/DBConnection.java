package com.example.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/yurtdb";
    private static final String USER = "root";
    private static final String PASSWORD = "357159";

    public static Connection getConnection() {
        try {
            System.out.println("Baglanti Basariyla Olusturuldu");
            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Veritabanı bağlantısı başarısız!");
        }
    }
}
