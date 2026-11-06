
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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/SvActorCrear")
public class SvActorCrear extends HttpServlet {

    private final ControladoraLogica controladora = new ControladoraLogica();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            int edad = Integer.parseInt(request.getParameter("edad"));
            String nacionalidad = request.getParameter("nacionalidad");
            int cantPremios = Integer.parseInt(request.getParameter("cantPremios"));

            // Recuperamos el ID de la película seleccionada
            int peliculaId = Integer.parseInt(request.getParameter("peliculaId"));

            // Buscamos la película en la base de datos
            Pelicula pelicula = controladora.buscarUnaPelicula(peliculaId);

            if (pelicula == null) {
                throw new Exception("La película seleccionada no existe.");
            }

            // Creamos el actor
            Actor actor = new Actor(nombre, apellido, edad, nacionalidad, cantPremios);

            // Asignamos la película al actor
            actor.setPelicula(pelicula);

            // Guardamos el actor
            controladora.crearActor(actor);

            response.sendRedirect(request.getContextPath() + "/SvActorListar");

        } catch (Exception e) {

            e.printStackTrace();

            String mensaje = e.getMessage();

            if (mensaje == null || mensaje.trim().isEmpty()) {
                mensaje = "No se pudo crear el actor. Verificá los datos.";
            }

            response.sendRedirect(request.getContextPath() + "/SvActorListar?error="
                    + URLEncoder.encode(mensaje, StandardCharsets.UTF_8));
        }
    }
}
