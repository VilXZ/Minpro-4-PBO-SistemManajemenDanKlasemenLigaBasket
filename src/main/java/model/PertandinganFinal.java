/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kidst
 */

public class PertandinganFinal extends Pertandingan {

    private String babak;

    public PertandinganFinal(
            int id,
            String tanggal,
            String lokasi,
            int idKandang,
            int idTandang,
            String namaKandang,
            String namaTandang,
            String status,
            String babak) {

        super(
                id, tanggal, lokasi,
                idKandang, idTandang,
                namaKandang, namaTandang, status
        );

        this.babak = babak;
    }

    public String getBabak() {
        return babak;
    }

    public void setBabak(String babak) {
        this.babak = babak;
    }

    @Override
    public String getInfoPertandingan() {
        return "Pertandingan Final"
                + " | ID: " + getIdPertandingan()
                + " | " + getNamaTimKandang()
                + " vs " + getNamaTimTandang()
                + " | " + babak
                + " | " + getTanggal()
                + " | " + getLokasi()
                + " | Status: " + getStatus();
    }
}