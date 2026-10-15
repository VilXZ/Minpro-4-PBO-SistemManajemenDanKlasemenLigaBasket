/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kidst
 */
public class HasilPertandingan {

    private int idHasil;
    private int idPertandingan;
    private int skorKandang;
    private int skorTandang;
    private String namaTimKandang;
    private String namaTimTandang;

    public HasilPertandingan(
            int idHasil,
            int idPertandingan,
            int skorKandang,
            int skorTandang,
            String namaTimKandang,
            String namaTimTandang) {

        this.idHasil = idHasil;
        this.idPertandingan = idPertandingan;
        this.skorKandang = skorKandang;
        this.skorTandang = skorTandang;
        this.namaTimKandang = namaTimKandang;
        this.namaTimTandang = namaTimTandang;
    }

    public String getPemenang() {
        if (skorKandang > skorTandang) {
            return namaTimKandang;
        }

        if (skorTandang > skorKandang) {
            return namaTimTandang;
        }

        return "Seri";
    }

    public int getIdHasil() {
        return idHasil;
    }

    public int getIdPertandingan() {
        return idPertandingan;
    }

    public int getSkorKandang() {
        return skorKandang;
    }

    public int getSkorTandang() {
        return skorTandang;
    }

    public String getNamaTimKandang() {
        return namaTimKandang;
    }

    public String getNamaTimTandang() {
        return namaTimTandang;
    }

    @Override
    public String toString() {
        return "ID Hasil: " + idHasil
                + " | Pertandingan: "
                + namaTimKandang + " vs " + namaTimTandang
                + " | Skor: " + skorKandang + " - " + skorTandang
                + " | Pemenang: " + getPemenang();
    }
}