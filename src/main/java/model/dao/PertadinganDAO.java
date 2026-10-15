package model.dao;

import config.Koneksi;
import model.*;

import java.sql.*;
import java.util.ArrayList;

public class PertadinganDAO {

    private PesertaDAO pesertaDAO = new PesertaDAO();

    public ArrayList<Pertandingan> getAllPertandingan() {
        ArrayList<Pertandingan> list = new ArrayList<>();
        String sql = "SELECT * FROM pertandingan ORDER BY id ASC";

        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int idP1 = rs.getInt("id_peserta1");
                int idP2 = rs.getInt("id_peserta2");
                boolean p2IsNull = rs.wasNull(); // Cek apakah id_peserta2 di DB bertipe NULL
                String kategori = rs.getString("kategori");
                String hasil = rs.getString("hasil");

                Peserta p1 = pesertaDAO.getPesertaById(idP1);
                Peserta p2 = p2IsNull ? null : pesertaDAO.getPesertaById(idP2);

                list.add(new Pertandingan(id, p1, p2, kategori, hasil));
            }
        } catch (SQLException e) {
            System.out.println("Error getAllPertandingan: " + e.getMessage());
        }
        return list;
    }

    public Pertandingan getPertandinganById(int id) {
        String sql = "SELECT * FROM pertandingan WHERE id = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idP1 = rs.getInt("id_peserta1");
                    int idP2 = rs.getInt("id_peserta2");
                    boolean p2IsNull = rs.wasNull();
                    String kategori = rs.getString("kategori");
                    String hasil = rs.getString("hasil");

                    Peserta p1 = pesertaDAO.getPesertaById(idP1);
                    Peserta p2 = p2IsNull ? null : pesertaDAO.getPesertaById(idP2);

                    return new Pertandingan(id, p1, p2, kategori, hasil);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getPertandinganById: " + e.getMessage());
        }
        return null;
    }

    public boolean insertPertandingan(int idPeserta1, int idPeserta2, String kategori, String hasil) {
        String sql = "INSERT INTO pertandingan (id_peserta1, id_peserta2, kategori, hasil) VALUES (?, ?, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPeserta1);
            stmt.setInt(2, idPeserta2);
            stmt.setString(3, kategori);
            stmt.setString(4, hasil);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error insertPertandingan (Tanding): " + e.getMessage());
            return false;
        }
    }

    public boolean insertPertandingan(int idPeserta1, String kategori, String hasil) {
        String sql = "INSERT INTO pertandingan (id_peserta1, id_peserta2, kategori, hasil) VALUES (?, NULL, ?, ?)";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPeserta1);
            stmt.setString(2, kategori);
            stmt.setString(3, hasil);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error insertPertandingan (Seni): " + e.getMessage());
            return false;
        }
    }

    public boolean updatePertandingan(int id, int idPeserta1, int idPeserta2, String kategori, String hasil) {
        String sql = "UPDATE pertandingan SET id_peserta1 = ?, id_peserta2 = ?, kategori = ?, hasil = ? WHERE id = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPeserta1);
            if (idPeserta2 != -1) {
                stmt.setInt(2, idPeserta2);
            } else {
                stmt.setNull(2, java.sql.Types.INTEGER);
            }
            stmt.setString(3, kategori);
            stmt.setString(4, hasil);
            stmt.setInt(5, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updatePertandingan: " + e.getMessage());
            return false;
        }
    }

    public boolean deletePertandingan(int id) {
        String sql = "DELETE FROM pertandingan WHERE id = ?";
        try (Connection conn = Koneksi.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deletePertandingan: " + e.getMessage());
            return false;
        }
    }
}