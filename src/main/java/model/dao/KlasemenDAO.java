/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

/**
 *
 * @author kidst
 */

import config.DbKonek;
import model.Klasemen;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KlasemenDAO {

    public List<Klasemen> semua() throws SQLException {
        List<Klasemen> list = new ArrayList<>();

        String sql =
            "SELECT t.nama_tim, "

          + "SUM(CASE "
          + "WHEN h.id_hasil IS NOT NULL THEN 1 "
          + "ELSE 0 END) AS main_count, "

          + "SUM(CASE "
          + "WHEN h.id_hasil IS NOT NULL AND "
          + "((p.id_tim_kandang=t.id_tim "
          + "AND h.skor_kandang>h.skor_tandang) OR "
          + "(p.id_tim_tandang=t.id_tim "
          + "AND h.skor_tandang>h.skor_kandang)) "
          + "THEN 1 ELSE 0 END) AS menang, "

          + "SUM(CASE "
          + "WHEN h.id_hasil IS NOT NULL AND "
          + "((p.id_tim_kandang=t.id_tim "
          + "AND h.skor_kandang<h.skor_tandang) OR "
          + "(p.id_tim_tandang=t.id_tim "
          + "AND h.skor_tandang<h.skor_kandang)) "
          + "THEN 1 ELSE 0 END) AS kalah, "

          + "SUM(CASE "
          + "WHEN h.id_hasil IS NULL THEN 0 "
          + "WHEN h.skor_kandang=h.skor_tandang THEN 1 "
          + "WHEN ((p.id_tim_kandang=t.id_tim "
          + "AND h.skor_kandang>h.skor_tandang) OR "
          + "(p.id_tim_tandang=t.id_tim "
          + "AND h.skor_tandang>h.skor_kandang)) "
          + "THEN 2 ELSE 0 END) AS poin "

          + "FROM tim t "

          + "LEFT JOIN pertandingan p "
          + "ON t.id_tim=p.id_tim_kandang "
          + "OR t.id_tim=p.id_tim_tandang "

          + "LEFT JOIN hasil_pertandingan h "
          + "ON p.id_pertandingan=h.id_pertandingan "

          + "GROUP BY t.id_tim, t.nama_tim "

          + "ORDER BY poin DESC, menang DESC, t.nama_tim";

        try (Connection c = DbKonek.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Klasemen(
                        rs.getString("nama_tim"),
                        rs.getInt("main_count"),
                        rs.getInt("menang"),
                        rs.getInt("kalah"),
                        rs.getInt("poin")
                ));
            }
        }

        return list;
    }
}