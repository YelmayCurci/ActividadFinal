
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

@WebServlet("/SvPeliculaCrear")

public class SvPeliculaCrear extends HttpServlet {

    private final ControladoraLogica controladora = new ControladoraLogica();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String titulo = request.getParameter("titulo");
            String genero = request.getParameter("genero");
            int anio = Integer.parseInt(request.getParameter("anio"));
            float duracion = Float.parseFloat(request.getParameter("duracion"));
            String director = request.getParameter("director");

            Pelicula pelicula = new Pelicula(titulo, genero, anio, duracion, director);

            controladora.crearPelicula(pelicula);

            response.sendRedirect(request.getContextPath() + "/SvPeliculaListar");

        } catch (NumberFormatException e) {

            String msg = URLEncoder.encode(
                "El año y la duración deben ser números",
                StandardCharsets.UTF_8
            );

            response.sendRedirect(request.getContextPath() + "/SvPeliculaListar?error=" + msg);

        } catch (Exception e) {

            String mensaje = e.getMessage();

            if (mensaje == null || mensaje.trim().isEmpty()) {
                mensaje = "No se pudo crear la película. Verificá los datos.";
            }

            response.sendRedirect(request.getContextPath() + "/SvPeliculaListar?error="
                    + URLEncoder.encode(mensaje, StandardCharsets.UTF_8));
        }
    }
}
