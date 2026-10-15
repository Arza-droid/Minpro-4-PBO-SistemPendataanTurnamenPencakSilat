package controller;

import model.dao.PesertaDAO;
import model.dao.PertadinganDAO; 
import model.*;

import java.util.ArrayList;

public class DaftarControl {
    private PesertaDAO pesertaDAO;
    private PertadinganDAO pertandinganDAO;

    public DaftarControl() {
        this.pesertaDAO = new PesertaDAO();
        this.pertandinganDAO = new PertadinganDAO();
    }

 

    public ArrayList<Peserta> getDaftarPeserta() {
        return pesertaDAO.getAllPeserta();
    }

    public Peserta cariPesertaById(int id) {
        return pesertaDAO.getPesertaById(id);
    }

    public void tambahPesertaTanding(String nama, String asalPerguruan, String kelasBerat) {
        PesertaTanding p = new PesertaTanding(0, nama, asalPerguruan, kelasBerat);
        pesertaDAO.insertPesertaTanding(p);
    }

    public void tambahPesertaSeni(String nama, String asalPerguruan, String jenisSeni) {
        PesertaSeni p = new PesertaSeni(0, nama, asalPerguruan, jenisSeni);
        pesertaDAO.insertPesertaSeni(p);
    }

    public boolean ubahPeserta(int id, String namaBaru, String asalPerguruanBaru, String atributKhusus) {
        Peserta p = cariPesertaById(id);
        if (p == null) return false;

        p.setNama(namaBaru);
        p.setAsalPerguruan(asalPerguruanBaru);

        if (p instanceof PesertaTanding) {
            ((PesertaTanding) p).setKelasBerat(atributKhusus);
        } else if (p instanceof PesertaSeni) {
            ((PesertaSeni) p).setJenisSeni(atributKhusus);
        }

        return pesertaDAO.updatePeserta(p);
    }

    public boolean hapusPeserta(int id) {
        return pesertaDAO.deletePeserta(id);
    }

 

    public ArrayList<Pertandingan> getDaftarPertandingan() { 
        return pertandinganDAO.getAllPertandingan();
    }

    public Pertandingan cariPertandinganById(int id) {
        return pertandinganDAO.getPertandinganById(id);
    }


    public boolean tambahPertandingan(int idPeserta1, int idPeserta2, String kategori, String hasil) {
        return pertandinganDAO.insertPertandingan(idPeserta1, idPeserta2, kategori, hasil);
    }

    public boolean tambahPertandingan(int idPeserta1, String kategori, String hasil) {
        return pertandinganDAO.insertPertandingan(idPeserta1, kategori, hasil);
    }

    public boolean ubahPertandingan(int id, int idPeserta1, int idPeserta2, String kategori, String hasil) {
        return pertandinganDAO.updatePertandingan(id, idPeserta1, idPeserta2, kategori, hasil);
    }

    public boolean hapusPertandingan(int id) {
        return pertandinganDAO.deletePertandingan(id);
    }
}