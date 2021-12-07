package com.example.ef_g4.Servlets;

import com.example.ef_g4.Beans.Empleado;
import com.example.ef_g4.Daos.DaoEmpleado;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action") == null ? "loginform" : request.getParameter("action");
        HttpSession session = request.getSession();

        switch (action) {
            case "loginform":
                Empleado employeeSession = (Empleado) session.getAttribute("employeeSession");
                if (employeeSession != null) {
                    response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                } else {
                    RequestDispatcher view = request.getRequestDispatcher("Login/Login.jsp");
                    view.forward(request, response);
                }
                break;
            case "logout":
                session.invalidate();
                response.sendRedirect(request.getContextPath());
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("inputDni");
        String password = request.getParameter("inputPassword");

        if (username == null || password == null) {
            request.setAttribute("err", "El usuario o password no pueden ser vacíos");
            RequestDispatcher view = request.getRequestDispatcher("Login/Login.jsp");
            view.forward(request, response);
        }else{
            DaoEmpleado daoempleado = new DaoEmpleado();
            Empleado empleado = daoempleado.validarUsuarioPassword(username, password);

            if (empleado != null) {
                HttpSession session = request.getSession();
                session.setAttribute("employeeSession", empleado);

                session.setMaxInactiveInterval(10 * 60); // 10 minutos

                if(empleado.getRoles().get(1).getIdRol() != 1){
                    response.sendRedirect(request.getContextPath() + "/CarteleraServlet");
                }else{
                    response.sendRedirect(request.getContextPath() + "/ReportesServlet");
                }
            } else {
                request.setAttribute("err", "El usuario o password no existen");
                RequestDispatcher view = request.getRequestDispatcher("Login/Login.jsp");
                view.forward(request, response);
            }
        }
    }
}
