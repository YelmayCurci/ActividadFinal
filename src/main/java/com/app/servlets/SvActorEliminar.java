
package com.app.servlets;

import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/SvActorEliminar")
public class SvActorEliminar extends HttpServlet {

    private final ControladoraLogica controladora =
            new ControladoraLogica();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            if (controladora.buscarUnActor(id) == null) {
                throw new Exception("No se encontró el actor.");
            }

            controladora.eliminarActor(id);

            response.sendRedirect(
                    request.getContextPath() + "/SvActorListar"
            );

        } catch (Exception e) {

            String mensaje = e.getMessage();

            if (mensaje == null || mensaje.trim().isEmpty()) {
                mensaje = "No se pudo eliminar el actor.";
            }

            response.sendRedirect(
                    request.getContextPath()
                    + "/SvActorListar?error="
                    + URLEncoder.encode(
                            mensaje, StandardCharsets.UTF_8
                    )
            );
        }
    }
}
