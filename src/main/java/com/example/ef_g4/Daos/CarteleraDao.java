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
}
