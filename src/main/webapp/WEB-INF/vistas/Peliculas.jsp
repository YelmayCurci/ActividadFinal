
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.app.logica.Pelicula" %>

<%
    String ctx = request.getContextPath();

    List<Pelicula> peliculas =
        (List<Pelicula>) request.getAttribute("listaPeliculas");

    String error = request.getParameter("error");
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Películas</title>
    <link rel="stylesheet" href="<%= ctx %>/css/estilos.css">
</head>

<body>

<header>
    <h1>Actividad Final</h1>

    <nav>
     <a href="<%= ctx %>/index.html">Inicio</a>
        <a href="<%= ctx %>/SvPeliculaListar">Películas</a>
        <a href="<%= ctx %>/SvActorListar">Actores</a>
    </nav>
</header>

<main>

    <% if (error != null) { %>
        <div class="error"><%= error %></div>
    <% } %>

    <h2>Películas</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Género</th>
            <th>Director</th>
            <th>Duración</th>
            <th>Año</th>
            <th>Acciones</th>
        </tr>

        <% if (peliculas == null || peliculas.isEmpty()) { %>

            <tr>
                <td colspan="7" class="vacio">
                    Todavía no hay películas cargadas.
                </td>
            </tr>

        <% } else {
            for (Pelicula p : peliculas) { %>

                <tr>
                    <td><%= p.getId() %></td>
                    <td><%= p.getTitulo() %></td>
                    <td><%= p.getGenero() %></td>
                    <td><%= p.getDirector() %></td>
                    <td><%= p.getDuracion() %></td>
                    <td><%= p.getAnio() %></td>

                    <td class="acciones">

                        <a class="boton"
                           href="<%= ctx %>/SvPeliculaEditar?id=<%= p.getId() %>">
                            Editar
                        </a>

                        <form action="<%= ctx %>/SvPeliculaEliminar"
                              method="post"
                              onsubmit="return confirm('¿Eliminar esta película?');">

                            <input type="hidden" name="id"
                                   value="<%= p.getId() %>">

                            <button type="submit" class="peligro">
                                Eliminar
                            </button>

                        </form>

                    </td>
                </tr>

        <%  }
           } %>

    </table>

    <h2>Nueva película</h2>

    <form class="formulario"
          action="<%= ctx %>/SvPeliculaCrear"
          method="post">

        <label>
            Título
            <input type="text" name="titulo" required>
        </label>

        <label>
            Género
            <input type="text" name="genero" required>
        </label>

        <label>
            Director
            <input type="text" name="director" required>
        </label>

        <label>
            Duración (minutos)
            <input type="number" name="duracion"
                   step="0.01" min="0.01" required>
        </label>

        <label>
            Año
            <input type="number" name="anio"
                   min="1900" max="2100" required>
        </label>

        <button type="submit">Guardar</button>
       

    </form>

</main>

<footer>Proyecto Cine</footer>

</body>
</html>
