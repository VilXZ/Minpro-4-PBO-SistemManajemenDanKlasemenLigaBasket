/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author kidst
 */
import java.util.Scanner;

public class MenuView {

    private final Scanner scanner = new Scanner(System.in);

    public void tampilkanMenuUtama() {
        System.out.println("\n==========================================");
        System.out.println("     SISTEM MANAJEMEN LIGA BASKET");
        System.out.println("==========================================");
        System.out.println("1. Manajemen Tim");
        System.out.println("2. Manajemen Pertandingan");
        System.out.println("3. Manajemen Hasil Pertandingan");
        System.out.println("4. Tampilkan Klasemen");
        System.out.println("5. Keluar");
        System.out.println("==========================================");
    }

    public int inputInt(String pesan) {
        while (true) {
            System.out.print(pesan);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    public String inputString(String pesan) {
        while (true) {
            System.out.print(pesan);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Input tidak boleh kosong!");
        }
    }

    public String inputTanggal(String pesan) {
        while (true) {
            String tanggal = inputString(
                    pesan + " (YYYY-MM-DD): "
            );

            if (tanggal.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return tanggal;
            }

            System.out.println(
                    "Format tanggal harus YYYY-MM-DD."
            );
        }
    }

    public void tampilkanPesan(String pesan) {
        System.out.println(pesan);
    }

    public void tutupScanner() {
        scanner.close();
    }
}