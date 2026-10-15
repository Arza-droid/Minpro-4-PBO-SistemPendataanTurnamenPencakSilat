# Sistem Pendataan Pendaftaran Turnamen Bela Diri Pencak Silat

- **Nama**: Muhammad Arza Dwiarto Anugerah
- **NIM**: 2509116007

## Deskripsi Singkat

Program ini merupakan aplikasi manajemen data **CRUD** (*Create, Read, Update, Delete*) sederhana untuk pendaftaran dan pelaksanaan turnamen Pencak Silat.
Program ini memiliki 2 data utama yaitu:

1. **Data Peserta** yang terbagi menjadi dua kategori:
   - **Peserta Tanding**: bertarung melawan lawan, dikelompokkan berdasarkan kelas berat
   - **Peserta Seni**: menampilkan jurus, dikelompokkan berdasarkan jenis seni (Tunggal/Ganda/Regu)
2. **Data Pertandingan**: mencatat dua peserta untuk kategori pertandingan atau satu peserta untuk kategori Seni, dan hasilnya


## 1. Penerapan Validasi Input

berikut untuk contoh dari salah satu validasi input dalam pemrograman saya

```java
private String bacaTeksTidakKosong(String label) {
        String input;
        do {
            System.out.print(label);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input tidak boleh kosong, silakan coba lagi.");
            }
        } while (input.isEmpty());
        return input;
}
```

validasi input ini akan memberikan pemberitahuan ketika kita tidak mengisi input



## 2. Penerapan Encapsulation

berikut adalah salah satu penerapan encapsulation pada pemrograman saya

```java
public class Peserta {
    private int id;
    private String nama;
    private String asalPerguruan;

    public int getId() { return id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getAsalPerguruan() { return asalPerguruan; }
    public void setAsalPerguruan(String asalPerguruan) { this.asalPerguruan = asalPerguruan; }
}
```
Atribut dibuat private, lalu diakses lewat getter dan setter

## 3. Penerapan Inheritance

dalam pemrograman saya ada **1 superclass yaitu `Peserta`** dan **2 subclass yaitu `PesertaTanding` dan `PesertaSeni`**.

```java
public class PesertaTanding extends Peserta {
    private String kelasBerat;

    public PesertaTanding(int id, String nama, String asalPerguruan, String kelasBerat) {
        super(id, nama, asalPerguruan); 
        this.kelasBerat = kelasBerat;
    }

    @Override
    public String tampilkanInfo() {
        return "ID: " + getId() + " | Nama: " + getNama() + " | Asal Perguruan: " + getAsalPerguruan()
                + " | Kategori: Tanding | Kelas Berat: " + kelasBerat;
    }
}
```


## 4. Alur Program
Pada menu awal kita dapat memilih menu peserta dan pertandingan  
Berikut adalah alur program untuk proses CRUD pada menu peserta dan juga pertandingan
### Peserta

| Fitur | Alur |
|---|---|
| **Create** | Input nama, asal perguruan, kelas berat / jenis seni → `tambahPesertaTanding()` / `tambahPesertaSeni()` → objek dibuat dengan ID dari `idPesertaCounter` → masuk `daftarPeserta` → counter naik |
| **Read** | `lihatSemuaPeserta()` → jika kosong tampil pesan, jika tidak tiap objek memanggil `tampilkanInfo()` |
| **Update** | Tampil daftar → input ID (harus valid) → input nama dan asal perguruan baru → `ubahPeserta()` → cari by ID → `setNama()` dan `setAsalPerguruan()` |
| **Delete** | Tampil daftar → input ID (harus valid) → `hapusPeserta()` → cari by ID → `remove()` dari `daftarPeserta` |

### Pertandingan

| Fitur | Alur |
|---|---|
| **Create** | Tampil peserta → input ID Peserta 1 → pilih kategori → jika Tanding input ID Peserta 2, jika Seni otomatis `-1` → input hasil (opsional) → `tambahPertandingan()` → cari peserta → objek dibuat → masuk `daftarPertandingan` → counter naik |
| **Read** | `lihatSemuaPertandingan()` → jika kosong tampil pesan, jika tidak tampilkan tiap data (Peserta 2 tampil "-" jika kosong) |
| **Update** | Tampil pertandingan → input ID Pertandingan → input ulang peserta, kategori, hasil → `ubahPertandingan()` → cari data dan peserta → setter memperbarui data |
| **Delete** | Tampil pertandingan → input ID → `hapusPertandingan()` → cari by ID → `remove()` dari `daftarPertandingan` |  
  
Untuk mengakhiri atau keluar dari program kita perlu kembali ke menu utama dan memilih pilihan keluar

## 5. Penerapan MVC (Model, View, Controller)  
<img width="291" height="404" alt="Screenshot 2026-10-08 214749" src="https://github.com/user-attachments/assets/01c315fa-deff-415d-b323-153b6dd10cd4" />  

Gambar diatas adalah bentuk penerapan MVC pada projek saya  

## 6. Penerapan Abstraction    
```java
package model;

public abstract class Peserta implements Cetakable {
    private int id;
    private String nama;
    private String asalPerguruan;

    public Peserta(int id, String nama, String asalPerguruan) {
        this.id = id;
        this.nama = nama;
        this.asalPerguruan = asalPerguruan;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAsalPerguruan() {
        return asalPerguruan;
    }

    public void setAsalPerguruan(String asalPerguruan) {
        this.asalPerguruan = asalPerguruan;
    }

    public abstract String getDetailKategori();

    @Override
    public void cetakInfo() {
        System.out.print("ID: " + id + " | Nama: " + nama + " | Asal Perguruan: " + asalPerguruan);
    }
}
```
Disini saya menerapkan abstract class pada class peserta dan juga menerapkan interface dengan implement Cetakable dari `Interface Cetakable`.  

## 7. Penerapan Interface  
```java
package model;

public interface Cetakable {
   void cetakInfo();
}

```
Kode diatas merupakan isi kode dari Interface pada `Interface Cetakable` yang digunakan pada abstract class `Peserta` menggunakan metode `void cetakInfo()`.  

## 8. Penerapan Polymorphism  
```java
@Override
public String getDetailKategori() {
    return "Tanding (Kelas Berat: " + kelasBerat + ")";
}

@Override
public void cetakInfo() {
    super.cetakInfo();
    System.out.println(" | Kategori: " + getDetailKategori());
}

public boolean tambahPertandingan(int idPeserta1, int idPeserta2, String kategori, String hasil) { ... }

public boolean tambahPertandingan(int idPeserta1, String kategori, String hasil) { ... }
```
Kode diatas adalah penerapan dari Overriding dan Overloading, untuk overriding sendiri di atas digunakan pada subclass peserta tanding dan peserta seni, yaitu cetakinfo() dari interface Cetakable dan getdetailkategori dari abstract class Peserta.  
Kemudian Pada DaftarControl, diterapkan method overloading pada fungsi tambahPertandingan untuk menangani perbedaan pendaftaran kategori Tanding yang butuh 2 peserta dan Seni yang butuh 1 peserta.  

## 10. Pembuatan Local Database  
  <img width="1617" height="719" alt="image" src="https://github.com/user-attachments/assets/3ddc4b0e-93f8-431d-9e54-2aab9e6de4d4" />  
  Gambar diatas adalah database yang telah dibuat untuk program saya.   
    
  <img width="1024" height="649" alt="image" src="https://github.com/user-attachments/assets/7332ae94-1e1a-4f05-86e0-1f75586eec3e" />  
  Gambar diatas adalah isi tabel dari database yang sebelumnya telah dibuat.


  
## 9. Penerapan JDBC & PreparedStatement    
<img width="770" height="482" alt="image" src="https://github.com/user-attachments/assets/bb815cb5-0b34-4836-ae1d-63d05ce16a95" />  
Gambar diatas adalah tes koneksi dari database yang sebelumnya telah dibuat dengan netbeans.  

```java  
public boolean insertPesertaTanding(PesertaTanding p) {
        String sql = "INSERT INTO peserta (nama, asal_perguruan, kategori, kelas_berat) VALUES (?, ?, 'Tanding', ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNama());
            stmt.setString(2, p.getAsalPerguruan());
            stmt.setString(3, p.getKelasBerat());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error insertPesertaTanding: " + e.getMessage());
            return false;
        }
    }
```
Kode diatas adalah penerapan PreparedStatement pada class PesertaDao yang berisi kode query insert yang akan dijalankan ke dalam database.  

  ## 10. Penerapan DAO  
  <img width="346" height="67" alt="image" src="https://github.com/user-attachments/assets/1db23bc6-117b-4125-bf30-a1cf3d863583" />  
  Gambar diatas adalah 2 class model.DAO yang telah dibuat untuk data Peserta dan juga Pertandingan.  

  <img width="1136" height="390" alt="image" src="https://github.com/user-attachments/assets/96c9482a-fe4a-4ed7-94dd-91ad120b8f94" />  
  Gambar di atas adalah tes run untuk melihat data peserta dari database yang berhasil terhubung dengan netbeans.  

  <img width="1051" height="379" alt="image" src="https://github.com/user-attachments/assets/906b2c67-be5c-4e45-b4f1-c8b97431ffea" />  
  Gambar di atas adalah tes run untuk melihat data pertandingan dari database yang berhasil terhubung sebelumnya.

