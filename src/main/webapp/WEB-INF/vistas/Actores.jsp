
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.app.logica.Actor, com.app.logica.Pelicula" %>

<%
    String ctx = request.getContextPath();

    List<Actor> actores = (List<Actor>) request.getAttribute("listaActores");
    List<Pelicula> peliculas = (List<Pelicula>) request.getAttribute("listaPeliculas");

    String error = request.getParameter("error");
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Actores</title>

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

    <h2>Actores</h2>

    <% if (error != null) { %>
        <p class="error"><%= error %></p>
    <% } %>

    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Edad</th>
            <th>Nacionalidad</th>
            <th>Premios</th>
            <th>Acciones</th>
        </tr>

        <% if (actores == null || actores.isEmpty()) { %>

            <tr>
                <td colspan="7" class="vacio">
                    Todavía no hay actores cargados.
                </td>
            </tr>

        <% } else {

            for (Actor a : actores) { %>

                <tr>
                    <td><%= a.getId() %></td>
                    <td><%= a.getNombre() %></td>
                    <td><%= a.getApellido() %></td>
                    <td><%= a.getEdad() %></td>
                    <td><%= a.getNacionalidad() %></td>
                    <td><%= a.getCantPremios() %></td>

                    <td class="acciones">

                        <a class="boton"
                           href="<%= ctx %>/SvActorEditar?id=<%= a.getId() %>">
                            Editar
                        </a>

                        <form action="<%= ctx %>/SvActorEliminar"
                              method="post"
                              onsubmit="return confirm('¿Eliminar este actor?');">

                            <input type="hidden"
                                   name="id"
                                   value="<%= a.getId() %>">

                            <button type="submit" class="peligro">
                                Eliminar
                            </button>

                        </form>

                    </td>
                </tr>

        <%  }
           } %>

    </table>


    <h2>Nuevo actor</h2>

    <form class="formulario"
          action="<%= ctx %>/SvActorCrear"
          method="post">

        <label>
            Nombre
            <input type="text" name="nombre" required>
        </label>

        <label>
            Apellido
            <input type="text" name="apellido" required>
        </label>

        <label>
            Edad
            <input type="number" name="edad" min="1" max="130" required>
        </label>

        <label>
            Nacionalidad
            <input type="text" name="nacionalidad" required>
        </label>

        <label>
            Cantidad de premios
            <input type="number" name="cantPremios" min="0" value="0" required>
        </label>

        <label>
            Película

            <select name="peliculaId" required>

                <option value="">Seleccionar película</option>

                <% if (peliculas != null) {
                    for (Pelicula p : peliculas) { %>

                        <option value="<%= p.getId() %>">
                            <%= p.getTitulo() %>
                        </option>

                <%  }
                   } %>

            </select>
        </label>

        <button type="submit">Guardar</button>

    </form>

</main>

<footer>
    Proyecto Final
</footer>

</body>
</html>
