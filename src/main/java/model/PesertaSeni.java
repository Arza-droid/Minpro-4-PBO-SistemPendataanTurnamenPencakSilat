
package model;


public class PesertaSeni extends Peserta {
    private String jenisSeni;

    public PesertaSeni(int id, String nama, String asalPerguruan, String jenisSeni) {
        super(id, nama, asalPerguruan);
        this.jenisSeni = jenisSeni;
    }

    public String getJenisSeni() {
        return jenisSeni;
    }

    public void setJenisSeni(String jenisSeni) {
        this.jenisSeni = jenisSeni;
    }

    @Override
    public String getDetailKategori() {
        return "Seni (Jenis: " + jenisSeni + ")";
    }

    @Override
    public void cetakInfo() {
        super.cetakInfo();
        System.out.println(" | Kategori: " + getDetailKategori());
    }
}