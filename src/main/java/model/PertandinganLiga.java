/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kidst
 */

public class PertandinganLiga extends Pertandingan {

    private int pekan;

    public PertandinganLiga(
            int id,
            String tanggal,
            String lokasi,
            int idKandang,
            int idTandang,
            String namaKandang,
            String namaTandang,
            String status,
            int pekan) {

        super(
                id, tanggal, lokasi,
                idKandang, idTandang,
                namaKandang, namaTandang, status
        );

        this.pekan = pekan;
    }

    public int getPekan() {
        return pekan;
    }

    public void setPekan(int pekan) {
        this.pekan = pekan;
    }

    @Override
    public String getInfoPertandingan() {
        return "Pertandingan Liga"
                + " | ID: " + getIdPertandingan()
                + " | " + getNamaTimKandang()
                + " vs " + getNamaTimTandang()
                + " | Pekan " + pekan
                + " | " + getTanggal()
                + " | " + getLokasi()
                + " | Status: " + getStatus();
    }
}