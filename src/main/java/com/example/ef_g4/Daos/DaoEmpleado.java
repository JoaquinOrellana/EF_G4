package com.example.ef_g4.Daos;


import com.example.ef_g4.Beans.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoEmpleado extends DaoBase{



    public Empleado obtenerEmpleado(String dniEmpleado) {

        Empleado employee = null;

        String sql = "SELECT * from movies.empleado;";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(4, dniEmpleado);

            try (ResultSet rs = pstmt.executeQuery();) {

                if (rs.next()) {
                    employee = new Empleado();
                    employee.setEmployeeId(rs.getInt(1));
                    employee.setFirstName(rs.getString(2));
                    employee.setLastName(rs.getString(3));
                    employee.setEmail(rs.getString(4));
                    employee.setPhoneNumber(rs.getString(5));
                    employee.setHireDate(rs.getString(6));

                    Job job = new Job();
                    job.setJobId(rs.getString(7));
                    employee.setJob(job);

                    employee.setSalary(rs.getBigDecimal(8));
                    employee.setCommissionPct(rs.getBigDecimal(9));

                    Employee manager = new Employee();
                    manager.setEmployeeId(rs.getInt(10));
                    employee.setManager(manager);

                    Department department = new Department();
                    department.setDepartmentId(rs.getInt(11));
                    employee.setDepartment(department);

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return employee;
    }




    public Empleado validarUsuarioPasswordHashed(String username, String password) {

        Empleado empleado = null;

        String sql = "SELECT dni as \"usuario\",dni-salario as \"password\" FROM movies.empleado;";

        try (Connection conn = this.getConection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    int employeeId = rs.getInt(1);
                    empleado = this.obtenerEmpleado(employeeId);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return employee;
    }


}
