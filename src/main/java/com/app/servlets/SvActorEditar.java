package com.app.servlets;

import com.app.logica.Actor;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/SvActorEditar")
public class SvActorEditar extends HttpServlet {

    private final ControladoraLogica controladora = new ControladoraLogica();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Actor actor = controladora.buscarUnActor(id);

            if (actor == null) {
                response.sendRedirect(request.getContextPath()
                        + "/SvActorListar?error="
                        + URLEncoder.encode("No se encontró el actor.", StandardCharsets.UTF_8));
                return;
            }

            request.setAttribute("actor", actor);

            request.getRequestDispatcher("/WEB-INF/vistas/EditarActor.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/SvActorListar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Actor actor = controladora.buscarUnActor(id);

            if (actor == null) {
                throw new Exception("No se encontró el actor.");
            }

            actor.setNombre(request.getParameter("nombre"));
            actor.setApellido(request.getParameter("apellido"));
            actor.setEdad(Integer.parseInt(request.getParameter("edad")));
            actor.setNacionalidad(request.getParameter("nacionalidad"));
            actor.setCantPremios(Integer.parseInt(request.getParameter("cantPremios")));

            controladora.editarActor(actor);

            response.sendRedirect(request.getContextPath() + "/SvActorListar");

        } catch (Exception e) {

            e.printStackTrace();

            String mensaje = e.getMessage();

            if (mensaje == null || mensaje.trim().isEmpty()) {
                mensaje = "No se pudo modificar el actor.";
            }

            response.sendRedirect(request.getContextPath()
                    + "/SvActorListar?error="
                    + URLEncoder.encode(mensaje, StandardCharsets.UTF_8));
        }
    }
}