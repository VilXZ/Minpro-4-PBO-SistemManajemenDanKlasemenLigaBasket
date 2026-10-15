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
import model.Pertandingan;
import model.PertandinganFinal;
import model.PertandinganLiga;
import java.sql.Types;

public class PertandinganDAO {

    public boolean tambah(Pertandingan p) throws SQLException {
        String sql = "INSERT INTO pertandingan "
                + "(tanggal, lokasi, id_tim_kandang, id_tim_tandang, "
                + "status, jenis_pertandingan, pekan, babak) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection c = DbKonek.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            isiStatement(ps, p);

            return ps.executeUpdate() > 0;
        }
    }

    private void isiStatement(
            PreparedStatement ps,
            Pertandingan p) throws SQLException {

        ps.setDate(1, java.sql.Date.valueOf(p.getTanggal()));
        ps.setString(2, p.getLokasi());
        ps.setInt(3, p.getIdTimKandang());
        ps.setInt(4, p.getIdTimTandang());
        ps.setString(5, p.getStatus());

        if (p instanceof PertandinganLiga) {
            ps.setString(6, "Liga");
            ps.setInt(7, ((PertandinganLiga) p).getPekan());
            ps.setNull(8, Types.VARCHAR);

        } else if (p instanceof PertandinganFinal) {
            ps.setString(6, "Final");
            ps.setNull(7, Types.INTEGER);
            ps.setString(8, ((PertandinganFinal) p).getBabak());

        } else {
            throw new SQLException(
                    "Jenis pertandingan tidak dikenali."
            );
        }
    }

    public List<Pertandingan> semua() throws SQLException {
        List<Pertandingan> list = new ArrayList<>();

        String sql = "SELECT p.*, "
                + "tk.nama_tim AS nama_kandang, "
                + "tt.nama_tim AS nama_tandang "
                + "FROM pertandingan p "
                + "JOIN tim tk ON p.id_tim_kandang = tk.id_tim "
                + "JOIN tim tt ON p.id_tim_tandang = tt.id_tim "
                + "ORDER BY p.id_pertandingan";

        try (Connection c = DbKonek.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_pertandingan");
                String tanggal = rs.getDate("tanggal").toString();
                String lokasi = rs.getString("lokasi");
                int idK = rs.getInt("id_tim_kandang");
                int idT = rs.getInt("id_tim_tandang");
                String namaK = rs.getString("nama_kandang");
                String namaT = rs.getString("nama_tandang");
                String status = rs.getString("status");

                if ("Liga".equalsIgnoreCase(
                        rs.getString("jenis_pertandingan"))) {

                    list.add(new PertandinganLiga(
                            id, tanggal, lokasi, idK, idT,
                            namaK, namaT, status,
                            rs.getInt("pekan")
                    ));

                } else {
                    list.add(new PertandinganFinal(
                            id, tanggal, lokasi, idK, idT,
                            namaK, namaT, status,
                            rs.getString("babak")
                    ));
                }
            }
        }

        return list;
    }

    public Pertandingan cari(int id) throws SQLException {
        for (Pertandingan p : semua()) {
            if (p.getIdPertandingan() == id) {
                return p;
            }
        }

        return null;
    }

    public boolean ubah(Pertandingan p) throws SQLException {
        String sql = "UPDATE pertandingan SET "
                + "tanggal=?, lokasi=?, id_tim_kandang=?, "
                + "id_tim_tandang=?, status=?, jenis_pertandingan=?, "
                + "pekan=?, babak=? "
                + "WHERE id_pertandingan=?";

        try (Connection c = DbKonek.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            isiStatement(ps, p);
            ps.setInt(9, p.getIdPertandingan());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean hapus(int id) throws SQLException {
        String sql = "DELETE FROM pertandingan "
                + "WHERE id_pertandingan=?";

        try (Connection c = DbKonek.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }
}