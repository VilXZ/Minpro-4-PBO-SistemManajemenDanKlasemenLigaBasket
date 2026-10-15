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
import model.HasilPertandingan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HasilPertandinganDAO {

    public boolean tambah(
            int idPertandingan,
            int skorKandang,
            int skorTandang) throws SQLException {

        String sql = "INSERT INTO hasil_pertandingan "
                + "(id_pertandingan, skor_kandang, skor_tandang) "
                + "VALUES (?, ?, ?)";

        try (Connection c = DbKonek.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idPertandingan);
            ps.setInt(2, skorKandang);
            ps.setInt(3, skorTandang);

            boolean berhasil = ps.executeUpdate() > 0;

            if (berhasil) {
                perbaruiStatus(c, idPertandingan, "Selesai");
            }

            return berhasil;
        }
    }

    public List<HasilPertandingan> semua() throws SQLException {
        List<HasilPertandingan> list = new ArrayList<>();

        String sql = "SELECT h.id_hasil, h.id_pertandingan, "
                + "h.skor_kandang, h.skor_tandang, "
                + "tk.nama_tim AS nama_kandang, "
                + "tt.nama_tim AS nama_tandang "
                + "FROM hasil_pertandingan h "
                + "JOIN pertandingan p "
                + "ON h.id_pertandingan = p.id_pertandingan "
                + "JOIN tim tk ON p.id_tim_kandang = tk.id_tim "
                + "JOIN tim tt ON p.id_tim_tandang = tt.id_tim "
                + "ORDER BY h.id_hasil";

        try (Connection c = DbKonek.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new HasilPertandingan(
                        rs.getInt("id_hasil"),
                        rs.getInt("id_pertandingan"),
                        rs.getInt("skor_kandang"),
                        rs.getInt("skor_tandang"),
                        rs.getString("nama_kandang"),
                        rs.getString("nama_tandang")
                ));
            }
        }

        return list;
    }

    public boolean ubah(
            int idHasil,
            int skorKandang,
            int skorTandang) throws SQLException {

        String sql = "UPDATE hasil_pertandingan "
                + "SET skor_kandang=?, skor_tandang=? "
                + "WHERE id_hasil=?";

        try (Connection c = DbKonek.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, skorKandang);
            ps.setInt(2, skorTandang);
            ps.setInt(3, idHasil);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean hapus(int idHasil) throws SQLException {
        String sql = "DELETE FROM hasil_pertandingan "
                + "WHERE id_hasil=?";

        String cariPertandingan =
                "SELECT id_pertandingan FROM hasil_pertandingan "
                + "WHERE id_hasil=?";

        try (Connection c = DbKonek.getConnection()) {
            Integer idPertandingan = null;

            try (PreparedStatement ps =
                         c.prepareStatement(cariPertandingan)) {

                ps.setInt(1, idHasil);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        idPertandingan = rs.getInt("id_pertandingan");
                    }
                }
            }

            if (idPertandingan == null) {
                return false;
            }

            try (PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setInt(1, idHasil);

                if (ps.executeUpdate() == 0) {
                    return false;
                }
            }

            perbaruiStatus(c, idPertandingan, "Belum Dimainkan");
            return true;
        }
    }

    private void perbaruiStatus(
            Connection c,
            int idPertandingan,
            String status) throws SQLException {

        try (PreparedStatement ps = c.prepareStatement(
                "UPDATE pertandingan SET status=? "
                + "WHERE id_pertandingan=?")) {

            ps.setString(1, status);
            ps.setInt(2, idPertandingan);
            ps.executeUpdate();
        }
    }

    public boolean pertandinganPunyaHasil(
            int idPertandingan) throws SQLException {

        String sql = "SELECT 1 FROM hasil_pertandingan "
                + "WHERE id_pertandingan=?";

        try (Connection c = DbKonek.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, idPertandingan);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}