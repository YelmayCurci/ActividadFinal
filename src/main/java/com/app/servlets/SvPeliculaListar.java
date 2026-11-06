
package com.app.servlets;

import com.app.logica.Pelicula;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/SvPeliculaListar")
public class SvPeliculaListar extends HttpServlet {

    ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Pelicula> listaPeliculas = control.listarPeliculas();

        request.setAttribute("listaPeliculas", listaPeliculas);

        request.getRequestDispatcher("/WEB-INF/vistas/Peliculas.jsp")
               .forward(request, response);
    }
}
