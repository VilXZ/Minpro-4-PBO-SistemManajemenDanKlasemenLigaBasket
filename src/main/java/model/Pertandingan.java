/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kidst
 */

public abstract class Pertandingan {

    private int idPertandingan;
    private String tanggal;
    private String lokasi;
    private int idTimKandang;
    private int idTimTandang;
    private String namaTimKandang;
    private String namaTimTandang;
    private String status;

    public Pertandingan(
            int idPertandingan,
            String tanggal,
            String lokasi,
            int idTimKandang,
            int idTimTandang,
            String namaTimKandang,
            String namaTimTandang,
            String status) {

        this.idPertandingan = idPertandingan;
        this.tanggal = tanggal;
        this.lokasi = lokasi;
        this.idTimKandang = idTimKandang;
        this.idTimTandang = idTimTandang;
        this.namaTimKandang = namaTimKandang;
        this.namaTimTandang = namaTimTandang;
        this.status = status;
    }

    public abstract String getInfoPertandingan();

    // Overloading: nama method sama, parameter berbeda.
    public String getInfoPertandingan(boolean tampilLokasi) {
        String info = namaTimKandang + " vs " + namaTimTandang;

        if (tampilLokasi) {
            info += " | Lokasi: " + lokasi;
        }

        return info;
    }

    public int getIdPertandingan() {
        return idPertandingan;
    }

    public void setIdPertandingan(int idPertandingan) {
        this.idPertandingan = idPertandingan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public int getIdTimKandang() {
        return idTimKandang;
    }

    public void setIdTimKandang(int idTimKandang) {
        this.idTimKandang = idTimKandang;
    }

    public int getIdTimTandang() {
        return idTimTandang;
    }

    public void setIdTimTandang(int idTimTandang) {
        this.idTimTandang = idTimTandang;
    }

    public String getNamaTimKandang() {
        return namaTimKandang;
    }

    public void setNamaTimKandang(String namaTimKandang) {
        this.namaTimKandang = namaTimKandang;
    }

    public String getNamaTimTandang() {
        return namaTimTandang;
    }

    public void setNamaTimTandang(String namaTimTandang) {
        this.namaTimTandang = namaTimTandang;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}