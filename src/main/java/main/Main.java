/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

/**
 *
 * @author kidst
 */

import controller.LigaController;
import view.MenuView;
import controller.LigaController;
import config.DbKonek;
import view.MenuView;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try (Connection ignored = DbKonek.getConnection()) {
            System.out.println("Koneksi database berhasil.");
        } catch (SQLException e) {
            System.out.println("Koneksi database gagal: " + e.getMessage());
            System.out.println(
                    "Periksa MySQL, database, username, password, dan Connector/J."
            );
            return;
        }

        MenuView view = new MenuView();
        LigaController controller = new LigaController(view);
        controller.jalankan();
    }
}