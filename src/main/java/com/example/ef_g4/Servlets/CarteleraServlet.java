package com.example.ef_g4.Servlets;

import com.example.ef_g4.Beans.Cartelera;
import com.example.ef_g4.Beans.Cine;
import com.example.ef_g4.Beans.Pelicula;
import com.example.ef_g4.Daos.CarteleraDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "CarteleraServlet", value = "/CarteleraServlet")
public class CarteleraServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        CarteleraDao carteleraDao = new CarteleraDao();
        RequestDispatcher view;
        Cartelera cartelera;
        int idCartelera;

        switch (action) {
            case "lista":
                ArrayList<Cartelera> listaCartelera = carteleraDao.listarCartelera();

                request.setAttribute("listaCartelera", listaCartelera);

                view = request.getRequestDispatcher("cartelera.jsp");
                view.forward(request, response);
                break;
            case "agregar":
                view = request.getRequestDispatcher("newFuncion.jsp");
                view.forward(request, response);
                break;

            case "editar":
                String idCarteleraStr = request.getParameter("id");
                try {
                    idCartelera = Integer.parseInt(idCarteleraStr);
                    cartelera = carteleraDao.obtenerCartelera(idCartelera);
                    if (cartelera == null) {
                        response.sendRedirect(request.getContextPath() + "/CarteleraServlet");
                    } else {
                        request.setAttribute("cartelera", cartelera);
                        view = request.getRequestDispatcher("updateCartelera.jsp");
                        view.forward(request, response);
                    }
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/CarteleraServlet");
                    e.printStackTrace();
                }
                break;
            case "borrar":
                idCarteleraStr = request.getParameter("id");
                try {
                    idCartelera = Integer.parseInt(idCarteleraStr);
                    if (carteleraDao.obtenerCartelera(idCartelera) != null) {
                        try {
                            carteleraDao.borrarDeLaCartelera(idCartelera);
                            request.getSession().setAttribute("msg", "Función borrada exitosamente");
                            response.sendRedirect(request.getContextPath() + "/CarteleraServlet");
                        } catch (SQLException e) {
                            e.printStackTrace();
                            request.getSession().setAttribute("err", "Error al borrar la función");
                            response.sendRedirect(request.getContextPath() + "/CarteleraServlet");
                        }
                    } else {
                        request.getSession().setAttribute("err", "No existe esa función");
                        response.sendRedirect(request.getContextPath() + "/CarteleraServlet");
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    response.sendRedirect(request.getContextPath() + "/CarteleraServlet");
                }
                break;
            case "reporte":


                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String action = request.getParameter("action") == null ? "" : request.getParameter("action");

        CarteleraDao carteleraDao = new CarteleraDao();
        RequestDispatcher view;
        Cartelera cartelera;
        int idCartelera;

        if ("agregar".equals(action)) {
            idCartelera = Integer.parseInt(request.getParameter("id"));
            int idPelicula = Integer.parseInt(request.getParameter("idPel"));
            int idCine = Integer.parseInt(request.getParameter("idCine"));
            int tresD = Integer.parseInt(request.getParameter("3D"));
            int doblada = Integer.parseInt(request.getParameter("dob"));
            int subtitulada = Integer.parseInt(request.getParameter("sub"));

            String horario = request.getParameter("hor");


            Cartelera newCartelera = new Cartelera();
            newCartelera.setIdCartelera(idCartelera);

            Pelicula pelicula = new Pelicula();
            pelicula.setIdPelicula(idPelicula);
            newCartelera.setPelicula(pelicula);

            Cine cine = new Cine();
            cine.setIdCine(idCine);
            newCartelera.setCine(cine);

            newCartelera.setTresD(tresD);
            newCartelera.setDoblada(doblada);
            newCartelera.setSubtitulada(subtitulada);
            newCartelera.setHorario(horario);

            cartelera = carteleraDao.obtenerCartelera(idCartelera);

            if (cartelera == null) {
                try {
                    carteleraDao.agregarFuncion(newCartelera);

                    session.setAttribute("msg", "Función creada exitosamente");
                    response.sendRedirect(request.getContextPath() + "/CarteleraServlet");
                } catch (SQLException e) {
                    session.setAttribute("err", "Error al crear la función");
                    response.sendRedirect(request.getContextPath() + "/CarteleraServlet?action=agregar");
                }
            } else {
                try {
                    carteleraDao.actualizarCartelera(newCartelera);
                    session.setAttribute("msg", "Función actualizada exitosamente");
                    response.sendRedirect(request.getContextPath() + "/CarteleraServlet");
                } catch (SQLException e) {
                    session.setAttribute("err", "Error al actualizar la función");
                    response.sendRedirect(request.getContextPath() + "/CarteleraServlet?action=editar&id=" + idCartelera);
                }
            }
        }
    }
}
