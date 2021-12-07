package com.example.ef_g4.Daos;

import com.example.ef_g4.Beans.Cartelera;
import com.example.ef_g4.Beans.Cine;
import com.example.ef_g4.Beans.Pelicula;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarteleraDao extends DaoBase {

    public ArrayList<Cartelera> listarCartelera() {
        ArrayList<Cartelera> listaCartelera = new ArrayList<>();

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM cartelera");) {

            Cartelera cartelera;

            while (rs.next()) {
                cartelera = obtenerCartelera(rs.getInt(1));
                listaCartelera.add(cartelera);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCartelera;
    }

    public Cartelera obtenerCartelera(int idCartelera) {

        Cartelera cartelera = null;

        String sql = "SELECT * FROM cartelera WHERE idCartelera = ?";
        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idCartelera);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cartelera = new Cartelera();
                    cartelera.setIdCartelera(rs.getInt(1));

                    Pelicula pelicula = new Pelicula();
                    pelicula.setIdPelicula(rs.getInt(2));
                    cartelera.setPelicula(pelicula);

                    Cine cine = new Cine();
                    cine.setIdCine(rs.getInt(3));
                    cartelera.setCine(cine);

                    cartelera.setTresD(rs.getInt(4));
                    cartelera.setDoblada(rs.getInt(5));
                    cartelera.setSubtitulada(rs.getInt(6));
                    cartelera.setHorario(rs.getString(7));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarteleraDao.class.getName()).log(Level.SEVERE, null, ex);

        }

        return cartelera;
    }

    public void actualizarCartelera(Cartelera cartelera) throws SQLException {

        String sql = "UPDATE cartelera SET idpelicula = ?, idcine = ?, 3d = ?, doblada = ?,subtitulada = ?, horario = ? "
                + "WHERE idCartelera = ?";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, cartelera.getPelicula().getIdPelicula());
            pstmt.setInt(2, cartelera.getCine().getIdCine());
            pstmt.setInt(3, cartelera.getTresD());
            pstmt.setInt(4, cartelera.getDoblada());
            pstmt.setInt(5, cartelera.getSubtitulada());
            pstmt.setString(6, cartelera.getHorario());
            pstmt.setInt(7, cartelera.getIdCartelera());

            pstmt.executeUpdate();
        }
    }

    public void agregarFuncion(Cartelera cartelera) throws SQLException {

        String sql = "INSERT INTO cartelera (idpelicula, idcine, 3d, doblada, subtitulada, horario) "
                + "VALUES (?,?,?,?,?,?)";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {

            ResultSet rsKeys = pstmt.getGeneratedKeys();

            pstmt.setInt(1, rsKeys.getInt(1));
            pstmt.setInt(2, cartelera.getPelicula().getIdPelicula());
            pstmt.setInt(3, cartelera.getCine().getIdCine());
            pstmt.setInt(4, cartelera.getTresD());
            pstmt.setInt(5, cartelera.getDoblada());
            pstmt.setInt(6, cartelera.getSubtitulada());
            pstmt.setString(7, cartelera.getHorario());

            pstmt.executeUpdate();
        }
    }

    public void borrarDeLaCartelera(int idCartelera) throws SQLException {

        String sql = "DELETE FROM cartelera WHERE idCartelera = ?";
        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1, idCartelera);
            pstmt.executeUpdate();
        }
    }
}
