package view;

import controller.DaftarControl;
import model.*;

import java.util.Scanner;

public class DaftarView {
    private DaftarControl controller;
    private Scanner scanner;

    public DaftarView(DaftarControl controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void tampilkanMenuUtama() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== SISTEM PENDATAAN PERTANDINGAN PENCAK SILAT ===");
            System.out.println("1. Peserta");
            System.out.println("2. Pertandingan");
            System.out.println("3. Keluar");
            int pilihan = bacaAngka("Pilih menu: ");

            switch (pilihan) {
                case 1:
                    menuPeserta();
                    break;
                case 2:
                    menuPertandingan();
                    break;
                case 3:
                    running = false;
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }
    }

   
    private void menuPeserta() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- MENU PESERTA ---");
            System.out.println("1. Tambah Peserta Tanding");
            System.out.println("2. Tambah Peserta Seni");
            System.out.println("3. Lihat Semua Peserta");
            System.out.println("4. Ubah Data Peserta");
            System.out.println("5. Hapus Data Peserta");
            System.out.println("6. Kembali ke Menu Utama");
            int pilihan = bacaAngka("Pilih menu: ");

            switch (pilihan) {
                case 1: {
                    String nama = bacaTeksTidakKosong("Nama: ");
                    String asalPerguruan = bacaTeksTidakKosong("Asal Perguruan: ");
                    String kelasBerat = bacaTeksTidakKosong("Kelas Berat: ");
                    controller.tambahPesertaTanding(nama, asalPerguruan, kelasBerat);
                    System.out.println("Peserta Tanding berhasil ditambahkan.");
                    break;
                }
                case 2: {
                    String nama = bacaTeksTidakKosong("Nama: ");
                    String asalPerguruan = bacaTeksTidakKosong("Asal Perguruan: ");
                    String jenisSeni = bacaTeksTidakKosong("Jenis Seni (Tunggal/Ganda/Regu): ");
                    controller.tambahPesertaSeni(nama, asalPerguruan, jenisSeni);
                    System.out.println("Peserta Seni berhasil ditambahkan.");
                    break;
                }
                case 3:
                    tampilkanPeserta();
                    break;
                case 4: {
                    tampilkanPeserta();
                    if (controller.getDaftarPeserta().isEmpty()) break;

                    int id = bacaIdPesertaValid("Masukkan ID Peserta yang akan diubah: ");
                    Peserta p = controller.cariPesertaById(id);

                    String namaBaru = bacaTeksTidakKosong("Nama baru: ");
                    String asalPerguruanBaru = bacaTeksTidakKosong("Asal Perguruan baru: ");
                    String atributKhusus = "";

                    if (p instanceof PesertaTanding) {
                        atributKhusus = bacaTeksTidakKosong("Kelas Berat baru: ");
                    } else if (p instanceof PesertaSeni) {
                        atributKhusus = bacaTeksTidakKosong("Jenis Seni baru (Tunggal/Ganda/Regu): ");
                    }

                    if (controller.ubahPeserta(id, namaBaru, asalPerguruanBaru, atributKhusus)) {
                        System.out.println("Data peserta berhasil diubah.");
                    }
                    break;
                }
                case 5: {
                    tampilkanPeserta();
                    if (controller.getDaftarPeserta().isEmpty()) break;

                    int id = bacaIdPesertaValid("Masukkan ID Peserta yang akan dihapus: ");
                    if (controller.hapusPeserta(id)) {
                        System.out.println("Data peserta berhasil dihapus.");
                    }
                    break;
                }
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }
    }


    private void menuPertandingan() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- MENU PERTANDINGAN ---");
            System.out.println("1. Tambah Data");
            System.out.println("2. Lihat Semua Data");
            System.out.println("3. Ubah Data");
            System.out.println("4. Hapus Data");
            System.out.println("5. Kembali ke Menu Utama");
            int pilihan = bacaAngka("Pilih menu: ");

            switch (pilihan) {
                case 1: {
                    tampilkanPeserta();
                    if (controller.getDaftarPeserta().isEmpty()) break;

                    int idPeserta1 = bacaIdPesertaValid("Masukkan ID Peserta 1: ");
                    String kategori = pilihKategoriPertandingan();
                    String hasil = bacaHasilOpsional();

                    if (kategori.equals("Tanding")) {
                        int idPeserta2 = bacaIdPeserta2JikaTanding(kategori, idPeserta1);
                        controller.tambahPertandingan(idPeserta1, idPeserta2, kategori, hasil);
                    } else {
                        controller.tambahPertandingan(idPeserta1, kategori, hasil);
                    }
                    System.out.println("Data pertandingan berhasil ditambahkan.");
                    break;
                }
                case 2:
                    tampilkanPertandingan();
                    break;
                case 3: {
                    tampilkanPertandingan();
                    if (controller.getDaftarPertandingan().isEmpty()) break;

                    int id = bacaIdPertandinganValid("Masukkan ID Pertandingan yang akan diubah: ");
                    tampilkanPeserta();

                    int idPeserta1 = bacaIdPesertaValid("Masukkan ID Peserta 1 baru: ");
                    String kategori = pilihKategoriPertandingan();
                    int idPeserta2 = bacaIdPeserta2JikaTanding(kategori, idPeserta1);
                    String hasil = bacaHasilOpsional();

                    if (controller.ubahPertandingan(id, idPeserta1, idPeserta2, kategori, hasil)) {
                        System.out.println("Data pertandingan berhasil diubah.");
                    }
                    break;
                }
                case 4: {
                    tampilkanPertandingan();
                    if (controller.getDaftarPertandingan().isEmpty()) break;

                    int id = bacaIdPertandinganValid("Masukkan ID Pertandingan yang akan dihapus: ");
                    if (controller.hapusPertandingan(id)) {
                        System.out.println("Data pertandingan berhasil dihapus.");
                    }
                    break;
                }
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }
    }


    private void tampilkanPeserta() {
    if (controller.getDaftarPeserta().isEmpty()) {
        System.out.println("Belum ada data peserta.");
        return;
    }
    System.out.println("=== DAFTAR PESERTA ===");
    for (Peserta peserta : controller.getDaftarPeserta()) {
        peserta.cetakInfo();
    }
}

private void tampilkanPertandingan() {
    if (controller.getDaftarPertandingan().isEmpty()) {
        System.out.println("Belum ada data pertandingan.");
        return;
    }
    System.out.println("=== DAFTAR PERTANDINGAN ===");
    for (Pertandingan pertandingan : controller.getDaftarPertandingan()) {
        pertandingan.cetakInfo(); 
    }
}

    private String pilihKategoriPertandingan() {
        while (true) {
            System.out.println("Pilih kategori pertandingan:");
            System.out.println("1. Tanding");
            System.out.println("2. Seni Tunggal");
            System.out.println("3. Seni Ganda");
            int pilihan = bacaAngka("Pilih kategori (1-3): ");
            switch (pilihan) {
                case 1: return "Tanding";
                case 2: return "Seni Tunggal";
                case 3: return "Seni Ganda";
                default: System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }
    }

    private int bacaIdPeserta2JikaTanding(String kategori, int idPeserta1) {
        if (!kategori.equals("Tanding")) {
            return -1;
        }
        while (true) {
            int idPeserta2 = bacaIdPesertaValid("Masukkan ID Peserta 2 (lawan): ");
            if (idPeserta2 == idPeserta1) {
                System.out.println("Peserta 2 tidak boleh sama dengan Peserta 1, silakan coba lagi.");
                continue;
            }
            return idPeserta2;
        }
    }

    private String bacaHasilOpsional() {
        System.out.print("Hasil (kosongkan jika belum bertanding): ");
        String hasil = scanner.nextLine().trim();
        return hasil.isEmpty() ? "Belum Bertanding" : hasil;
    }

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

    private int bacaAngka(String label) {
        while (true) {
            System.out.print(label);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka, silakan coba lagi.");
            }
        }
    }

    private int bacaIdPesertaValid(String label) {
        while (true) {
            int id = bacaAngka(label);
            if (controller.cariPesertaById(id) != null) {
                return id;
            }
            System.out.println("ID Peserta tidak ditemukan, silakan coba lagi.");
        }
    }

    private int bacaIdPertandinganValid(String label) {
        while (true) {
            int id = bacaAngka(label);
            if (controller.cariPertandinganById(id) != null) {
                return id;
            }
            System.out.println("ID Pertandingan tidak ditemukan, silakan coba lagi.");
        }
    }
}