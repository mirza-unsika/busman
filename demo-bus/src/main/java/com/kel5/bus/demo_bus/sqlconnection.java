package com.kel5.bus.demo_bus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class sqlconnection {
    public static void main(String[] args) {
        // Informasi koneksi ke MySQL
    	String url = "jdbc:mysql://localhost:3306/busman?serverTimezone=UTC";
        String user = "root";
        String password = "";

        // Blok try-catch untuk menangkap eksepsi jika koneksi gagal
        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            System.out.println("Berhasil terhubung ke MySQL!");

            // Tutup koneksi
            connection.close();
        } catch (SQLException e) {
            System.err.println("Gagal terhubung ke MySQL: " + e.getMessage());
        }
    }
}