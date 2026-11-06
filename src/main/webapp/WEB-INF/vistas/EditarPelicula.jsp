
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.app.logica.Pelicula" %>

<%
    /*
       Vista para editar una película.

       La prepara el Servlet SvPeliculaEditar (en su doGet),
       que antes de hacer forward a esta página tiene que dejar:

       request.setAttribute("pelicula", pelicula);

       El formulario manda los datos por POST al mismo SvPeliculaEditar.
    */

    String ctx = request.getContextPath();

    Pelicula p = (Pelicula) request.getAttribute("pelicula");
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Editar Película</title>
    <link rel="stylesheet" href="<%= ctx %>/css/estilos.css">
</head>

<body>

    <header>
        <h1>Proyecto Actividad Final - Cine</h1>

        <nav>
            <a href="<%= ctx %>/SvActorListar">Actores</a>
            <a href="<%= ctx %>/SvPeliculaListar">Películas</a>
        </nav>
    </header>

    <main>

        <h2>Editar Película</h2>

        <% if (p == null) { %>

            <div class="error">
                No se recibió ninguna película para editar.
            </div>

            <a class="boton secundario" href="<%= ctx %>/SvPeliculaListar">
                Volver
            </a>

        <% } else { %>

            <form class="formulario"
                  action="<%= ctx %>/SvPeliculaEditar"
                  method="post">

                <input type="hidden" name="id" value="<%= p.getId() %>">

                <label>
                    Título
                    <input type="text" name="titulo"
                           value="<%= p.getTitulo() %>" required>
                </label>

                <label>
                    Género
                    <input type="text" name="genero"
                           value="<%= p.getGenero() %>" required>
                </label>

                <label>
                    Duración (minutos)
                    <input type="number" name="duracion"
                           step="0.01" min="0.01"
                           value="<%= p.getDuracion() %>" required>
                </label>

                <label>
                    Año
                    <input type="number" name="anio"
                           min="1900" max="2100"
                           value="<%= p.getAnio() %>" required>
                </label>

                <div>
                    <button type="submit">Guardar cambios</button>

                    <a class="boton secundario"
                       href="<%= ctx %>/SvPeliculaListar">
                        Cancelar
                    </a>
                </div>

            </form>

        <% } %>

    </main>

    <footer>
        Carina Yelma y Nicole Curci
    </footer>

</body>
</html>
