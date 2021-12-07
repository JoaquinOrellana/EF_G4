package com.example.ef_g4.Daos;


import com.example.ef_g4.Beans.Cine;
import com.example.ef_g4.Beans.Empleado;
import com.example.ef_g4.Beans.Rol;

import java.sql.*;
import java.util.ArrayList;

public class DaoEmpleado extends DaoBase {


    public Empleado obtenerEmpleado(String dniEmpleado) {

        Empleado employee = null;

        String sql = "SELECT * from movies.empleado;";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(4, dniEmpleado);

            try (ResultSet rs = pstmt.executeQuery();) {

                if (rs.next()) {
                    employee = new Empleado();
                    employee.setIdEmpleado(rs.getInt(1));
                    employee.setNombre(rs.getString(2));
                    employee.setApellido(rs.getString(3));
                    employee.setDni(rs.getString(4));
                    employee.setSalario(rs.getBigDecimal(5));
                    employee.setFechaContrato(rs.getString(6));
                    employee.setNombreUsuario(rs.getString(7));
                    employee.setEdad(rs.getInt(8));
                    employee.setActivo(rs.getBoolean(9));

                    Cine cine = new Cine();
                    cine.setIdCine(rs.getInt(10));
                    employee.setCine(cine);

                    Empleado jefe = new Empleado();
                    jefe.setIdEmpleado(rs.getInt(11));
                    employee.setJefe(jefe);

                    ArrayList<Rol> listrol = new ArrayList<>();
                    Rol rol = new Rol();
                    rol.setIdRol(this.obteneridrol(employee.getIdEmpleado()));
                    listrol.add(rol);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return employee;
    }


    public int obteneridrol(int idempleado) {

        String sql = "SELECT * FROM movies.rolempleado where idempleado=?;";

        int idrol = 0;
        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, idempleado);

            try (ResultSet rs = pstmt.executeQuery();) {

                if (rs.next()) {
                    idrol = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return idrol;
    }


    public Empleado validarUsuarioPassword(String username, String password) {

        Empleado empleado = null;

        String sql = "SELECT dni as \"usuario\",dni-salario as \"password\" FROM movies.empleado;";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    String dni = rs.getString(1);
                    empleado = this.obtenerEmpleado(dni);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return empleado;
    }


    public int obtenerEmpleadosSinJefe() {

        String sql = "SELECT count(*) FROM empleado where idjefe is null;";
        int cantidadEmpleados = -1;

        try (Connection conn = this.getConection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                cantidadEmpleados = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cantidadEmpleados;
    }

}
