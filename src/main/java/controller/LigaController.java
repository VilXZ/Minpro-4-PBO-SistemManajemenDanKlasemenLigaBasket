package controller;

import model.dao.HasilPertandinganDAO;
import model.dao.KlasemenDAO;
import model.dao.PertandinganDAO;
import model.dao.TimDAO;
import model.*;
import controller.ValidasiInput;
import view.MenuView;
import java.sql.SQLException;
import java.util.List;

public class LigaController implements ValidasiInput {

    private final MenuView view;
    private final TimDAO timDAO = new TimDAO();
    private final PertandinganDAO pertandinganDAO =
            new PertandinganDAO();
    private final HasilPertandinganDAO hasilDAO =
            new HasilPertandinganDAO();
    private final KlasemenDAO klasemenDAO = new KlasemenDAO();

    public LigaController(MenuView view) {
        this.view = view;
    }

    public void jalankan() {
        boolean jalan = true;

        while (jalan) {
            view.tampilkanMenuUtama();

            switch (view.inputInt("Pilih menu: ")) {
                case 1:
                    menuTim();
                    break;
                case 2:
                    menuPertandingan();
                    break;
                case 3:
                    menuHasil();
                    break;
                case 4:
                    tampilkanKlasemen();
                    break;
                case 5:
                    jalan = false;
                    break;
                default:
                    view.tampilkanPesan("Pilihan tidak tersedia.");
            }
        }

        view.tampilkanPesan("Program selesai.");
        view.tutupScanner();
    }

    // ==================== MENU TIM ====================

    private void menuTim() {
        boolean kembali = false;

        while (!kembali) {
            view.tampilkanPesan(
                    "\n--- MANAJEMEN TIM ---\n"
                    + "1. Tambah\n"
                    + "2. Tampilkan\n"
                    + "3. Ubah\n"
                    + "4. Hapus\n"
                    + "5. Kembali"
            );

            switch (view.inputInt("Pilih: ")) {
                case 1:
                    tambahTim();
                    break;
                case 2:
                    tampilkanTim();
                    break;
                case 3:
                    ubahTim();
                    break;
                case 4:
                    hapusTim();
                    break;
                case 5:
                    kembali = true;
                    break;
                default:
                    view.tampilkanPesan("Pilihan tidak tersedia.");
            }
        }
    }

    private void tambahTim() {
        String nama = view.inputString("Nama tim: ");
        String kota = view.inputString("Kota: ");
        String pelatih = view.inputString("Pelatih: ");

        if (!validasiNama(nama)
                || !validasiNama(kota)
                || !validasiNama(pelatih)) {
            view.tampilkanPesan(
                    "Nama, kota, dan pelatih tidak boleh kosong."
            );
            return;
        }

        try {
            timDAO.tambah(new Tim(0, nama, kota, pelatih));
            view.tampilkanPesan("Tim berhasil ditambahkan.");
        } catch (SQLException e) {
            view.tampilkanPesan(
                    "Gagal menambah tim: " + e.getMessage()
            );
        }
    }

    private void tampilkanTim() {
        try {
            List<Tim> data = timDAO.semua();

            if (data.isEmpty()) {
                view.tampilkanPesan("Belum ada tim.");
            }

            for (Tim t : data) {
                view.tampilkanPesan(t.toString());
            }
        } catch (SQLException e) {
            view.tampilkanPesan(
                    "Gagal membaca tim: " + e.getMessage()
            );
        }
    }

    private void ubahTim() {
        int id = view.inputInt("ID tim yang diubah: ");

        try {
            Tim tim = timDAO.cari(id);

            if (tim == null) {
                view.tampilkanPesan("Tim tidak ditemukan.");
                return;
            }

            String nama = view.inputString("Nama tim baru: ");
            String kota = view.inputString("Kota baru: ");
            String pelatih = view.inputString("Pelatih baru: ");

            if (!validasiNama(nama)
                    || !validasiNama(kota)
                    || !validasiNama(pelatih)) {
                view.tampilkanPesan("Data tidak boleh kosong.");
                return;
            }

            tim.setNamaTim(nama);
            tim.setKota(kota);
            tim.setPelatih(pelatih);

            view.tampilkanPesan(
                    timDAO.ubah(tim)
                    ? "Tim berhasil diubah."
                    : "Tim tidak berubah."
            );

        } catch (SQLException e) {
            view.tampilkanPesan(
                    "Gagal mengubah tim: " + e.getMessage()
            );
        }
    }

    private void hapusTim() {
        int id = view.inputInt("ID tim yang dihapus: ");

        try {
            view.tampilkanPesan(
                    timDAO.hapus(id)
                    ? "Tim berhasil dihapus."
                    : "Tim tidak ditemukan."
            );

        } catch (SQLException e) {
            view.tampilkanPesan(
                    "Tim tidak dapat dihapus. Pastikan tim tidak "
                    + "digunakan pertandingan. Detail: "
                    + e.getMessage()
            );
        }
    }

    // ==================== MENU PERTANDINGAN ====================

    private void menuPertandingan() {
        boolean kembali = false;

        while (!kembali) {
            view.tampilkanPesan(
                    "\n--- MANAJEMEN PERTANDINGAN ---\n"
                    + "1. Tambah\n"
                    + "2. Tampilkan\n"
                    + "3. Ubah\n"
                    + "4. Hapus\n"
                    + "5. Kembali"
            );

            switch (view.inputInt("Pilih: ")) {
                case 1:
                    tambahPertandingan();
                    break;
                case 2:
                    tampilkanPertandingan();
                    break;
                case 3:
                    ubahPertandingan();
                    break;
                case 4:
                    hapusPertandingan();
                    break;
                case 5:
                    kembali = true;
                    break;
                default:
                    view.tampilkanPesan("Pilihan tidak tersedia.");
            }
        }
    }

    private void tambahPertandingan() {
        try {
            List<Tim> tim = timDAO.semua();

            if (tim.size() < 2) {
                view.tampilkanPesan("Minimal harus ada 2 tim.");
                return;
            }

            for (Tim t : tim) {
                view.tampilkanPesan(t.toString());
            }

            int idKandang = view.inputInt("ID tim kandang: ");
            int idTandang = view.inputInt("ID tim tandang: ");

            if (idKandang == idTandang) {
                view.tampilkanPesan(
                        "Tim kandang dan tim tandang tidak boleh sama."
                );
                return;
            }

            Tim kandang = timDAO.cari(idKandang);
            Tim tandang = timDAO.cari(idTandang);

            if (kandang == null || tandang == null) {
                view.tampilkanPesan("ID tim tidak ditemukan.");
                return;
            }

            String tanggal =
                    view.inputTanggal("Tanggal pertandingan");
            String lokasi = view.inputString("Lokasi: ");

            view.tampilkanPesan(
                    "Jenis pertandingan: 1. Liga  2. Final"
            );

            int jenis = view.inputInt("Pilih jenis: ");
            Pertandingan p;

            if (jenis == 1) {
                int pekan = view.inputInt("Pekan ke: ");

                if (!validasiAngka(pekan) || pekan < 1) {
                    view.tampilkanPesan(
                            "Pekan harus minimal 1."
                    );
                    return;
                }

                p = new PertandinganLiga(
                        0, tanggal, lokasi,
                        idKandang, idTandang,
                        kandang.getNamaTim(),
                        tandang.getNamaTim(),
                        "Belum Dimainkan", pekan
                );

            } else if (jenis == 2) {
                String babak = view.inputString("Babak final: ");

                p = new PertandinganFinal(
                        0, tanggal, lokasi,
                        idKandang, idTandang,
                        kandang.getNamaTim(),
                        tandang.getNamaTim(),
                        "Belum Dimainkan", babak
                );

            } else {
                view.tampilkanPesan(
                        "Jenis pertandingan tidak valid."
                );
                return;
            }

            view.tampilkanPesan(
                    pertandinganDAO.tambah(p)
                    ? "Pertandingan berhasil ditambahkan."
                    : "Pertandingan gagal ditambahkan."
            );

        } catch (SQLException e) {
            view.tampilkanPesan(
                    "Gagal mengelola pertandingan: "
                    + e.getMessage()
            );
        }
    }

    private void tampilkanPertandingan() {
        try {
            List<Pertandingan> data = pertandinganDAO.semua();

            if (data.isEmpty()) {
                view.tampilkanPesan("Belum ada pertandingan.");
            }

            // Polymorphism: method override dipanggil melalui
            // referensi dengan tipe induk Pertandingan.
            for (Pertandingan p : data) {
                view.tampilkanPesan(p.getInfoPertandingan());
            }

        } catch (SQLException e) {
            view.tampilkanPesan(
                    "Gagal membaca pertandingan: "
                    + e.getMessage()
            );
        }
    }

    private void ubahPertandingan() {
        tampilkanPertandingan();

        int id = view.inputInt(
                "ID pertandingan yang diubah: "
        );

        try {
            Pertandingan lama = pertandinganDAO.cari(id);

            if (lama == null) {
                view.tampilkanPesan(
                        "Pertandingan tidak ditemukan."
                );
                return;
            }

            List<Tim> tim = timDAO.semua();

            for (Tim t : tim) {
                view.tampilkanPesan(t.toString());
            }

            int idK = view.inputInt("ID tim kandang baru: ");
            int idT = view.inputInt("ID tim tandang baru: ");

            if (idK == idT) {
                view.tampilkanPesan(
                        "Kedua tim harus berbeda."
                );
                return;
            }

            Tim kandang = timDAO.cari(idK);
            Tim tandang = timDAO.cari(idT);

            if (kandang == null || tandang == null) {
                view.tampilkanPesan("ID tim tidak ditemukan.");
                return;
            }

            String tanggal = view.inputTanggal("Tanggal baru");
            String lokasi = view.inputString("Lokasi baru: ");

            Pertandingan baru;

            if (lama instanceof PertandinganLiga) {
                int pekan = view.inputInt("Pekan baru: ");

                if (pekan < 1) {
                    view.tampilkanPesan(
                            "Pekan minimal 1."
                    );
                    return;
                }

                baru = new PertandinganLiga(
                        id, tanggal, lokasi, idK, idT,
                        kandang.getNamaTim(),
                        tandang.getNamaTim(),
                        lama.getStatus(), pekan
                );

            } else {
                String babak = view.inputString("Babak baru: ");

                baru = new PertandinganFinal(
                        id, tanggal, lokasi, idK, idT,
                        kandang.getNamaTim(),
                        tandang.getNamaTim(),
                        lama.getStatus(), babak
                );
            }

            // Jika pertandingan sudah punya hasil, jangan ubah
            // pasangan tim/tanggal sebelum hasilnya dihapus.
            if (hasilDAO.pertandinganPunyaHasil(id)) {
                view.tampilkanPesan(
                        "Pertandingan sudah memiliki hasil. "
                        + "Hapus hasilnya terlebih dahulu sebelum "
                        + "mengubah tim atau tanggal."
                );
                return;
            }

            view.tampilkanPesan(
                    pertandinganDAO.ubah(baru)
                    ? "Pertandingan berhasil diubah."
                    : "Tidak ada perubahan."
            );

        } catch (SQLException e) {
            view.tampilkanPesan(
                    "Gagal mengubah pertandingan: "
                    + e.getMessage()
            );
        }
    }

    private void hapusPertandingan() {
        int id = view.inputInt(
                "ID pertandingan yang dihapus: "
        );

        try {
            view.tampilkanPesan(
                    pertandinganDAO.hapus(id)
                    ? "Pertandingan dan hasil terkait berhasil dihapus."
                    : "Pertandingan tidak ditemukan."
            );

        } catch (SQLException e) {
            view.tampilkanPesan(
                    "Gagal menghapus pertandingan: "
                    + e.getMessage()
            );
        }
    }

    // ==================== MENU HASIL ====================

    private void menuHasil() {
        boolean kembali = false;

        while (!kembali) {
            view.tampilkanPesan(
                    "\n--- MANAJEMEN HASIL ---\n"
                    + "1. Tambah\n"
                    + "2. Tampilkan\n"
                    + "3. Ubah\n"
                    + "4. Hapus\n"
                    + "5. Kembali"
            );

            switch (view.inputInt("Pilih: ")) {
                case 1:
                    tambahHasil();
                    break;
                case 2:
                    tampilkanHasil();
                    break;
                case 3:
                    ubahHasil();
                    break;
                case 4:
                    hapusHasil();
                    break;
                case 5:
                    kembali = true;
                    break;
                default:
                    view.tampilkanPesan("Pilihan tidak tersedia.");
            }
        }
    }

    private void tambahHasil() {
        try {
            tampilkanPertandingan();

            int idPertandingan = view.inputInt(
                    "ID pertandingan yang diberi hasil: "
            );

            Pertandingan p = pertandinganDAO.cari(idPertandingan);

            if (p == null) {
                view.tampilkanPesan(
                        "Pertandingan tidak ditemukan."
                );
                return;
            }

            if (hasilDAO.pertandinganPunyaHasil(idPertandingan)) {
                view.tampilkanPesan(
                        "Pertandingan ini sudah memiliki hasil. "
                        + "Gunakan menu Ubah."
                );
                return;
            }

            int skorK = view.inputInt("Skor tim kandang: ");
            int skorT = view.inputInt("Skor tim tandang: ");

            if (!validasiSkor(skorK)
                    || !validasiSkor(skorT)) {
                view.tampilkanPesan(
                        "Skor tidak boleh negatif."
                );
                return;
            }

            view.tampilkanPesan(
                    hasilDAO.tambah(idPertandingan, skorK, skorT)
                    ? "Hasil berhasil ditambahkan."
                    : "Hasil gagal ditambahkan."
            );

        } catch (SQLException e) {
            view.tampilkanPesan(
                    "Gagal menambah hasil: " + e.getMessage()
            );
        }
    }

    private void tampilkanHasil() {
        try {
            List<HasilPertandingan> data = hasilDAO.semua();

            if (data.isEmpty()) {
                view.tampilkanPesan(
                        "Belum ada hasil pertandingan."
                );
            }

            for (HasilPertandingan h : data) {
                view.tampilkanPesan(h.toString());
            }

        } catch (SQLException e) {
            view.tampilkanPesan(
                    "Gagal membaca hasil: " + e.getMessage()
            );
        }
    }

    private void ubahHasil() {
        tampilkanHasil();

        int id = view.inputInt("ID hasil yang diubah: ");
        int skorK = view.inputInt("Skor kandang baru: ");
        int skorT = view.inputInt("Skor tandang baru: ");

        if (!validasiSkor(skorK)
                || !validasiSkor(skorT)) {
            view.tampilkanPesan(
                    "Skor tidak boleh negatif."
            );
            return;
        }

        try {
            view.tampilkanPesan(
                    hasilDAO.ubah(id, skorK, skorT)
                    ? "Hasil berhasil diubah."
                    : "ID hasil tidak ditemukan."
            );

        } catch (SQLException e) {
            view.tampilkanPesan(
                    "Gagal mengubah hasil: " + e.getMessage()
            );
        }
    }

    private void hapusHasil() {
        int id = view.inputInt("ID hasil yang dihapus: ");

        try {
            view.tampilkanPesan(
                    hasilDAO.hapus(id)
                    ? "Hasil berhasil dihapus."
                    : "ID hasil tidak ditemukan."
            );

        } catch (SQLException e) {
            view.tampilkanPesan(
                    "Gagal menghapus hasil: " + e.getMessage()
            );
        }
    }

    // ==================== KLASEMEN ====================

    private void tampilkanKlasemen() {
        try {
            List<Klasemen> data = klasemenDAO.semua();

            if (data.isEmpty()) {
                view.tampilkanPesan("Belum ada tim.");
                return;
            }

            view.tampilkanPesan(
                    "\n--- KLASEMEN LIGA BASKET ---"
            );

            int posisi = 1;

            for (Klasemen k : data) {
                view.tampilkanPesan(posisi++ + ". " + k);
            }

        } catch (SQLException e) {
            view.tampilkanPesan(
                    "Gagal menghitung klasemen: "
                    + e.getMessage()
            );
        }
    }

    // ==================== IMPLEMENTASI INTERFACE ====================

    @Override
    public boolean validasiNama(String nama) {
        return nama != null && !nama.trim().isEmpty();
    }

    @Override
    public boolean validasiAngka(int angka) {
        return angka >= 0;
    }

    @Override
    public boolean validasiSkor(int skor) {
        return skor >= 0;
    }
}