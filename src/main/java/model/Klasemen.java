/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kidst
 */
public class Klasemen {

    private String tim;
    private int jumlahPertandingan;
    private int jumlahMenang;
    private int jumlahKalah;
    private int poin;

    public Klasemen(
            String tim,
            int jumlahPertandingan,
            int jumlahMenang,
            int jumlahKalah,
            int poin) {

        this.tim = tim;
        this.jumlahPertandingan = jumlahPertandingan;
        this.jumlahMenang = jumlahMenang;
        this.jumlahKalah = jumlahKalah;
        this.poin = poin;
    }

    public String getTim() {
        return tim;
    }

    public int getJumlahPertandingan() {
        return jumlahPertandingan;
    }

    public int getJumlahMenang() {
        return jumlahMenang;
    }

    public int getJumlahKalah() {
        return jumlahKalah;
    }

    public int getPoin() {
        return poin;
    }

    @Override
    public String toString() {
        return tim
                + " | Main: " + jumlahPertandingan
                + " | Menang: " + jumlahMenang
                + " | Kalah: " + jumlahKalah
                + " | Poin: " + poin;
    }
}