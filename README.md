# Sistem Manajemen Pertandingan dan Klasemen Liga Basket

## Deskripsi Singkat Program

Program **Sistem Manajemen Pertandingan dan Klasemen Liga Basket** merupakan program berbasis Java yang digunakan untuk mengelola data dalam sebuah liga basket. Program ini memungkinkan pengguna untuk mengelola data tim, pertandingan, hasil pertandingan, dan klasemen.

Program menerapkan konsep **CRUD (Create, Read, Update, Delete)** serta menggunakan `ArrayList` untuk menyimpan data selama program berjalan. Program juga menggunakan beberapa class, yaitu `Tim`, `Pertandingan`, `HasilPertandingan`, dan `Klasemen`.

Struktur Program

## Alur Program

Saat program dijalankan, pengguna akan diberikan menu utama yang terdiri dari beberapa pilihan:

<img width="272" height="165" alt="image" src="https://github.com/user-attachments/assets/f553d4cf-f744-40d0-bfa4-858d680d7075" />

1. **Manajemen Data Tim**
   - Menambahkan data tim.
   - Menampilkan data tim.
   - Mengubah data tim.
   - Menghapus data tim.

     <img width="267" height="159" alt="image" src="https://github.com/user-attachments/assets/22e79209-7b98-45e5-9606-19822afb8950" />
     
     <img width="311" height="441" alt="image" src="https://github.com/user-attachments/assets/c5bff1a3-b32d-45cc-ab75-6528dae75305" />
Pada bagian Manajemen Data Tim pengguna dapat menambahkan Tim sesuai dengan keiinginan user, dalam kasus ini digunakan data-data Tim dari Laga asli yaitu dari NBA. Pengguna dapat menampilkan data tim yang telah ditambahkan sebelumnya, jika belum ada data tim yang tersedia maka output yang akan keluar adalah "Belum ada tim.". Jika pengguna merasa melakukan kesalahan pada proses penambahan data tim pengguna dapat melakukan perubahan pada data tim yang telah ditambahkan sebelumnya. Jika data tim sudah tidak relevan maka pengguna dapat menghapusnya.

2. **Manajemen Pertandingan**
   - Menambahkan data pertandingan.
   - Menampilkan data pertandingan.
   - Mengubah data pertandingan.
   - Menghapus data pertandingan.
  
     <img width="392" height="700" alt="image" src="https://github.com/user-attachments/assets/822bcf61-bc86-4576-a590-a39698e77632" />
Pada bagian Manajemen Pertandingan pengguna dapat Menambahkan data pertandingan dengan memilih Tim yang sebelumnya sudah ditambahkan maksimal 2, jika belum ada tim atau tim belum memenuhi jumlah minimal maka output yang akan dihasilkan adalah "Minimal harus ada 2 tim". Setelah data pertandingan ditambahkan pengguna dapat menampilkan data pertandingan tersebut. Jika pengguna mearasa melakukan kesalahan pada proses penambahan data pertandingan pengguna dapat melakukan perubahan pada data pertandingan yang telah ditambahkan sebelumnya. Jika data pertandingan sudah tidak relevan maka pengguna dapat menghapusnya.

3. **Manajemen Hasil Pertandingan**
   - Menambahkan hasil pertandingan.
   - Menampilkan hasil pertandingan.
   - Mengubah hasil pertandingan.
   - Menghapus hasil pertandingan.
  
     <img width="452" height="629" alt="image" src="https://github.com/user-attachments/assets/88e31acb-23af-45aa-aa0f-f3f4afc4f8e1" />
Pada bagian Manajemen Hasil Pertandingan pengguna dapat menambahkan hasil pertandingan daripada pertandingan yang telah ditambahkan sebelumnya. Setelah menambahkan hasil pertandingan pengguna dapat menampilkannya. Jika pengguna merasa melakukan keasalahan pada proses penambahan hasil pertandingan pengguna dapat melakukan perubahan pada data hasil pertandingan yang telah ditambahkan sebelumnya. Jika hasil pertandingan sudah tidak dibutuhkan maka pengguna dapat menghapusnya.

4. **Manajemen Klasemen**
   - Menambahkan data klasemen.
   - Menampilkan data klasemen.
   - Mengubah data klasemen.
   - Menghapus data klasemen.
  
     <img width="509" height="758" alt="image" src="https://github.com/user-attachments/assets/e538a8e8-a4f3-4103-87a6-e6c8177ac9ea" />
Pada bagian Manajemen Klasemen pengguna dapat menambahkan data klasemen tetapi jika belum ada tim yang ditambahkan maka output yang akan dihasilkan adalah "Belum ada tim". Jika pengguna telah menambahkan seluruh data dari data tim, data pertandingan, dan hasil pertandingan maka pengguna dapat menentukan klasemen untuk tim-tim yang ada. Jika pengguna telah menambahkan data klasemen maka data klasemen tersebut dapat ditampilkan oleh pengguna. Jika pengguna merasa melakukan kesalahan pada proses penambahan data klasemen pengguna dapat melakukan perubahan pada data klasemen tersebut. Jika data klasemen tidak relevan maka pengguna juga dapat menghapusnya.

5. **Keluar**
   - Mengakhiri program.

Pengguna memilih menu dengan memasukkan nomor pilihan melalui `Scanner`. Setiap menu memiliki submenu untuk melakukan proses CRUD. Program menggunakan perulangan sehingga menu akan terus ditampilkan dan dapat digunakan kembali sampai pengguna memilih pilihan **Keluar**.

Data yang dimasukkan pengguna akan disimpan ke dalam `ArrayList` sesuai dengan jenis datanya. Data tim disimpan pada `ArrayList<Tim>`, data pertandingan pada `ArrayList<Pertandingan>`, data hasil pertandingan pada `ArrayList<HasilPertandingan>`, dan data klasemen pada `ArrayList<Klasemen>`.

## Encapsulation

Encapsulation digunakan pada masing-masing class dengan menerapkan `Access Modifier` dan `Setter` `Getter`. Encapsulation `Setter` `Getter` dapat ditemukan pada class Tim, Pertandingan, Hasil Pertandingan, dan Klasemen. Hal tersebut digunakan penulis untuk digunakan sebagai pintu gerbang resmi dari atribut-atribut yang diterapkan `Access Modifier` yang dimana atribut ditetapkan sebagai `private`.

## Inheritance

Inheritance diterapkan pada class `Pertandingan` sebagai Parent dengan `PertandinganLiga` dan `PertandingaFinal` sebagai subclassnya.

## Overriding dan Overloading

Overriding dan Overloading diterapkan pada subclass PertandinganLiga dan PertandinganFinal tetapi belum sepenuhnya dimanfaatkan atau diterapkan.

## Integrasi Database MySQL

Pada tahap pengembangan selanjutnya, program diintegrasikan dengan database MySQL untuk menyimpan data secara permanen. Sebelumnya, program menggunakan ArrayList sebagai media penyimpanan data selama program berjalan. Setelah integrasi database, data utama dikelola melalui query SQL menggunakan JDBC.

Database yang digunakan

Nama database: ligabasket

Database digunakan untuk menyimpan informasi mengenai:

- Data tim.

- Data pertandingan.

- Hasil pertandingan.

- Data yang diperlukan untuk menampilkan klasemen liga.

- Class DatabaseConnection

Class DatabaseConnection bertanggung jawab untuk membuat koneksi antara aplikasi Java dengan database MySQL menggunakan JDBC.

## Class DbKonek

Class DatabaseConnection bertanggung jawab untuk membuat koneksi antara aplikasi Java dengan database MySQL menggunakan JDBC.

Contoh konfigurasi koneksi:

<img width="659" height="382" alt="image" src="https://github.com/user-attachments/assets/c79d26a9-387a-4f38-9b64-1a60ed5fa92e" />

Konfigurasi port harus disesuaikan apabila MySQL menggunakan port selain 3306.

## Penggunaan DAO

DAO (Data Access Object) digunakan untuk memisahkan proses akses database dari logika pengelolaan program.

Setiap DAO bertanggung jawab atas operasi database untuk entitas tertentu, seperti:

TimDAO: mengelola data tim.

PertandinganDAO: mengelola data pertandingan.

HasilPertandinganDAO: mengelola hasil pertandingan.

KlasemenDAO: mengambil atau mengolah data klasemen sesuai implementasi program.

Operasi database dilakukan menggunakan JDBC, seperti Connection, PreparedStatement, dan ResultSet. Penggunaan PreparedStatement membantu memisahkan parameter input dari perintah SQL.

Penyimpanan Data

Setelah database terhubung, data yang ditambahkan melalui program dapat disimpan ke dalam MySQL dan tetap tersedia setelah program ditutup, selama data tidak dihapus dan database tetap tersedia.

Dengan demikian, penyimpanan data tidak lagi hanya bergantung pada keberadaan objek di memori program seperti pada penggunaan ArrayList.

## Penentuan Pemenang Otomatis

Pemenang pertandingan ditentukan berdasarkan skor akhir kedua tim, bukan ditentukan secara manual melalui input nama pemenang.

Aturan penentuan pemenang:

- Jika skor tim kandang lebih tinggi daripada tim tandang, tim kandang menjadi pemenang.

- Jika skor tim tandang lebih tinggi daripada tim kandang, tim tandang menjadi pemenang.

- Jika skor sama, pertandingan dianggap seri oleh logika dasar, kecuali program menerapkan aturan tambahan untuk overtime atau menolak skor akhir seri.

Penentuan otomatis ini membantu menjaga konsistensi antara skor pertandingan, data hasil pertandingan, dan informasi pemenang yang ditampilkan.

Konsistensi dan Perbaikan Program

Beberapa hal yang menjadi perhatian dalam pengembangan program adalah:

- Menghindari dead code, yaitu method atau bagian program yang tidak digunakan.

- Menggunakan method untuk menampilkan data agar logika tampilan tidak ditulis berulang kali pada beberapa bagian menu.

- Menjaga konsistensi informasi pemenang pada menu pertandingan dan menu hasil pertandingan.

- Memastikan method overriding benar-benar digunakan melalui pemanggilan polymorphism, bukan hanya didefinisikan tanpa pernah dipanggil.

- Memisahkan operasi database, logika program, dan tampilan sesuai tanggung jawab masing-masing.

- Menangani kesalahan input serta exception database agar program dapat memberikan pesan yang mudah dipahami pengguna.

Cara Menjalankan Program

1. **Persiapan**

Pastikan perangkat lunak berikut tersedia:

Java Development Kit (JDK).

Apache NetBeans atau IDE Java lainnya.

Apache Maven.

MySQL Server dan MySQL CLI.

MySQL Connector/J sebagai JDBC driver.

2. **Membuat Database**

Jalankan MySQL CLI, kemudian jalankan file database.sql yang berisi perintah pembuatan database, tabel, dan constraint yang diperlukan.

Pastikan database ligabasket beserta tabel-tabel yang dibutuhkan sudah tersedia.

<img width="678" height="366" alt="image" src="https://github.com/user-attachments/assets/e949383e-f9f4-4f5a-837a-c5691dd8f0ad" />

<img width="1384" height="938" alt="image" src="https://github.com/user-attachments/assets/62f118d2-01dc-4ca8-addc-8ddab4ecbb35" />

<img width="714" height="672" alt="image" src="https://github.com/user-attachments/assets/748e681a-fcba-4e74-b393-8b5d8eea84a9" />

<img width="1109" height="412" alt="image" src="https://github.com/user-attachments/assets/4ed266d8-7bea-4bde-abc1-88f933a6d5d9" />


3. **Mengatur Koneksi**

Periksa konfigurasi pada DatabaseConnection.java, terutama URL JDBC, username, password, dan port MySQL.

Pastikan MySQL Server sedang berjalan dan nama database pada URL sama dengan nama database yang dibuat.

4. **Menjalankan Program**

Buka proyek melalui NetBeans, kemudian lakukan Clean and Build Project. Setelah proses build berhasil, jalankan class Main.java.

<img width="954" height="824" alt="image" src="https://github.com/user-attachments/assets/136f60eb-7fef-4c1a-9523-8698cc14c1b5" />


5. **Menggunakan Menu**

Pilih menu yang tersedia untuk mengelola data tim, pertandingan, hasil pertandingan, dan klasemen. Masukkan pilihan sesuai instruksi yang ditampilkan.

Program akan terus menampilkan menu sampai pengguna memilih menu keluar.
