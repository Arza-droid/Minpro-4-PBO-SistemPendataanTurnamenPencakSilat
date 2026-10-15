
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

    // Abstract method
    public abstract String getDetailKategori();

    @Override
    public void cetakInfo() {
        System.out.print("ID: " + id + " | Nama: " + nama + " | Asal Perguruan: " + asalPerguruan);
    }
}

