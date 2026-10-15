/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

/**
 *
 * @author kidst
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import config.DbKonek;
import model.Tim;

public class TimDAO {

    public boolean tambah(Tim tim) throws SQLException {
        String sql = "INSERT INTO tim (nama_tim, kota, pelatih) "
                + "VALUES (?, ?, ?)";

        try (Connection c = DbKonek.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, tim.getNamaTim());
            ps.setString(2, tim.getKota());
            ps.setString(3, tim.getPelatih());

            return ps.executeUpdate() > 0;
        }
    }

    public List<Tim> semua() throws SQLException {
    List<Tim> list = new ArrayList<>();

    String sql = "SELECT id_tim, nama_tim, kota, pelatih "
               + "FROM tim ORDER BY id_tim";

    try (Connection c = DbKonek.getConnection();
         PreparedStatement ps = c.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Tim tim = new Tim(
                rs.getInt("id_tim"),
                rs.getString("nama_tim"),
                rs.getString("kota"),
                rs.getString("pelatih")
            );

            list.add(tim);
        }
    }

    return list;
}

    public Tim cari(int id) throws SQLException {
        String sql = "SELECT id_tim, nama_tim, kota, pelatih "
                + "FROM tim WHERE id_tim = ?";

        try (Connection c = DbKonek.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Tim(
                            rs.getInt("id_tim"),
                            rs.getString("nama_tim"),
                            rs.getString("kota"),
                            rs.getString("pelatih")
                    );
                }
            }
        }

        return null;
    }

    public boolean ubah(Tim tim) throws SQLException {
        String sql = "UPDATE tim SET nama_tim=?, kota=?, pelatih=? "
                + "WHERE id_tim=?";

        try (Connection c = DbKonek.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, tim.getNamaTim());
            ps.setString(2, tim.getKota());
            ps.setString(3, tim.getPelatih());
            ps.setInt(4, tim.getIdTim());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean hapus(int id) throws SQLException {
        String sql = "DELETE FROM tim WHERE id_tim=?";

        try (Connection c = DbKonek.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }
}