
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

@WebServlet("/SvBuscar")
public class SvBuscar extends HttpServlet {

    private final ControladoraLogica control = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tipo = request.getParameter("tipo");
        String texto = request.getParameter("texto");

        if (texto != null && !texto.trim().isEmpty()) {

            texto = texto.trim();

            if ("actor".equals(tipo)) {

                List<Actor> resultados = control.buscarActoresPorNombre(texto);
                request.setAttribute("resultados", resultados);

            } else if ("pelicula".equals(tipo)) {

                List<Pelicula> resultados = control.buscarPeliculasPorTitulo(texto);
                request.setAttribute("resultados", resultados);

            }
        }

        request.setAttribute("tipo", tipo);
        request.setAttribute("texto", texto);

        request.getRequestDispatcher("/WEB-INF/vistas/Buscar.jsp")
                .forward(request, response);
    }
}
