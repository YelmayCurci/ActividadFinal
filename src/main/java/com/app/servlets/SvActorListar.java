
package com.app.servlets;

import com.app.logica.Actor;
import com.app.logica.Pelicula;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/SvActorListar")
public class SvActorListar extends HttpServlet {

    private final ControladoraLogica controladora = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Buscamos todos los actores
        List<Actor> actores = controladora.listarActores();

        // Buscamos todas las películas
        List<Pelicula> peliculas = controladora.listarPeliculas();

        // Enviamos las listas al JSP
        request.setAttribute("listaActores", actores);
        request.setAttribute("listaPeliculas", peliculas);

        // Mostramos la vista
        request.getRequestDispatcher("/WEB-INF/vistas/Actores.jsp")
                .forward(request, response);
    }
}
