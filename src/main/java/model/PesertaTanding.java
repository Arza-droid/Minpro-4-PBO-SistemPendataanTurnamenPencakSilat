
package model;


public class PesertaTanding extends Peserta {
    private String kelasBerat;

    public PesertaTanding(int id, String nama, String asalPerguruan, String kelasBerat) {
        super(id, nama, asalPerguruan);
        this.kelasBerat = kelasBerat;
    }

    public String getKelasBerat() {
        return kelasBerat;
    }

    public void setKelasBerat(String kelasBerat) {
        this.kelasBerat = kelasBerat;
    }

    @Override
    public String getDetailKategori() {
        return "Tanding (Kelas Berat: " + kelasBerat + ")";
    }

    @Override
    public void cetakInfo() {
        super.cetakInfo();
        System.out.println(" | Kategori: " + getDetailKategori());
    }
}