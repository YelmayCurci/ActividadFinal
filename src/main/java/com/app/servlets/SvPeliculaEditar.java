package com.app.servlets;

import com.app.logica.Pelicula;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/SvPeliculaEditar")
public class SvPeliculaEditar extends HttpServlet {

    private final ControladoraLogica controladora = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Pelicula pelicula = controladora.buscarUnaPelicula(id);

            if (pelicula == null) {
                response.sendRedirect(request.getContextPath()
                        + "/SvPeliculaListar?error="
                        + URLEncoder.encode("No se encontró la película.", StandardCharsets.UTF_8));
                return;
            }

            request.setAttribute("pelicula", pelicula);

            request.getRequestDispatcher("/WEB-INF/vistas/EditarPelicula.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/SvPeliculaListar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Pelicula pelicula = controladora.buscarUnaPelicula(id);

            if (pelicula == null) {
                throw new Exception("No se encontró la película.");
            }

            pelicula.setTitulo(request.getParameter("titulo"));
            pelicula.setGenero(request.getParameter("genero"));
            pelicula.setDirector(request.getParameter("director"));
            pelicula.setDuracion(Float.parseFloat(request.getParameter("duracion")));
            pelicula.setAnio(Integer.parseInt(request.getParameter("anio")));

            controladora.editarPelicula(pelicula);

            response.sendRedirect(request.getContextPath() + "/SvPeliculaListar");

        } catch (Exception e) {

            e.printStackTrace();

            String mensaje = e.getMessage();

            if (mensaje == null || mensaje.trim().isEmpty()) {
                mensaje = "No se pudo modificar la película.";
            }

            response.sendRedirect(request.getContextPath()
                    + "/SvPeliculaListar?error="
                    + URLEncoder.encode(mensaje, StandardCharsets.UTF_8));
        }
    }
}