/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kidst
 */

public class Tim {

    private int idTim;
    private String namaTim;
    private String kota;
    private String pelatih;

    public Tim(int idTim, String namaTim, String kota, String pelatih) {
        this.idTim = idTim;
        this.namaTim = namaTim;
        this.kota = kota;
        this.pelatih = pelatih;
    }

    public int getIdTim() {
        return idTim;
    }

    public void setIdTim(int idTim) {
        this.idTim = idTim;
    }

    public String getNamaTim() {
        return namaTim;
    }

    public void setNamaTim(String namaTim) {
        this.namaTim = namaTim;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getPelatih() {
        return pelatih;
    }

    public void setPelatih(String pelatih) {
        this.pelatih = pelatih;
    }

    @Override
    public String toString() {
        return "ID: " + idTim
                + " | Tim: " + namaTim
                + " | Kota: " + kota
                + " | Pelatih: " + pelatih;
    }
}