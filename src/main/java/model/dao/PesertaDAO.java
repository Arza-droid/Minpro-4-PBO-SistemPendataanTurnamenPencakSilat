package model.dao;

import config.Koneksi;
import model.*;

import java.sql.*;
import java.util.ArrayList;

public class PesertaDAO {

    public ArrayList<Peserta> getAllPeserta() {
        ArrayList<Peserta> list = new ArrayList<>();
        String sql = "SELECT * FROM peserta ORDER BY id ASC";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nama = rs.getString("nama");
                String asal = rs.getString("asal_perguruan");
                String kategori = rs.getString("kategori");

                if ("Tanding".equalsIgnoreCase(kategori)) {
                    String kelasBerat = rs.getString("kelas_berat");
                    list.add(new PesertaTanding(id, nama, asal, kelasBerat));
                } else if ("Seni".equalsIgnoreCase(kategori)) {
                    String jenisSeni = rs.getString("jenis_seni");
                    list.add(new PesertaSeni(id, nama, asal, jenisSeni));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getAllPeserta: " + e.getMessage());
        }
        return list;
    }

    public Peserta getPesertaById(int id) {
        String sql = "SELECT * FROM peserta WHERE id = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nama = rs.getString("nama");
                    String asal = rs.getString("asal_perguruan");
                    String kategori = rs.getString("kategori");

                    if ("Tanding".equalsIgnoreCase(kategori)) {
                        String kelasBerat = rs.getString("kelas_berat");
                        return new PesertaTanding(id, nama, asal, kelasBerat);
                    } else if ("Seni".equalsIgnoreCase(kategori)) {
                        String jenisSeni = rs.getString("jenis_seni");
                        return new PesertaSeni(id, nama, asal, jenisSeni);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getPesertaById: " + e.getMessage());
        }
        return null;
    }

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

    public boolean insertPesertaSeni(PesertaSeni p) {
        String sql = "INSERT INTO peserta (nama, asal_perguruan, kategori, jenis_seni) VALUES (?, ?, 'Seni', ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNama());
            stmt.setString(2, p.getAsalPerguruan());
            stmt.setString(3, p.getJenisSeni());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error insertPesertaSeni: " + e.getMessage());
            return false;
        }
    }

    public boolean updatePeserta(Peserta p) {
        String sql;
        if (p instanceof PesertaTanding) {
            sql = "UPDATE peserta SET nama = ?, asal_perguruan = ?, kelas_berat = ? WHERE id = ?";
        } else {
            sql = "UPDATE peserta SET nama = ?, asal_perguruan = ?, jenis_seni = ? WHERE id = ?";
        }

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNama());
            stmt.setString(2, p.getAsalPerguruan());

            if (p instanceof PesertaTanding) {
                stmt.setString(3, ((PesertaTanding) p).getKelasBerat());
            } else {
                stmt.setString(3, ((PesertaSeni) p).getJenisSeni());
            }

            stmt.setInt(4, p.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updatePeserta: " + e.getMessage());
            return false;
        }
    }

    public boolean deletePeserta(int id) {
        String sql = "DELETE FROM peserta WHERE id = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deletePeserta: " + e.getMessage());
            return false;
        }
    }
}