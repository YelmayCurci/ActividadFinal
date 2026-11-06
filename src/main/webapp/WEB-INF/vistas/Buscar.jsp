
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, com.app.logica.Actor, com.app.logica.Pelicula"%>

<%
String ctx = request.getContextPath();

String tipo = (String) request.getAttribute("tipo");
String texto = (String) request.getAttribute("texto");

List<Actor> resultados = null;
List<Pelicula> resultados1 = null;

if ("actor".equals(tipo)) {
	resultados = (List<Actor>) request.getAttribute("resultados");
}

if ("pelicula".equals(tipo)) {
	resultados1 = (List<Pelicula>) request.getAttribute("resultados");
}

if (texto == null) {
	texto = "";
}

// Escapamos el texto para que no rompa el HTML
String textoSeguro = texto.replace("&", "&amp;").replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;");
%>

<!DOCTYPE html>
<html lang="es">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Buscador</title>
<link rel="stylesheet" href="<%=ctx%>/css/estilos.css">
</head>

<body>

	<header>
		<h1>Actividad Final</h1>

		<nav>
			<a href="<%=ctx%>/SvPeliculaListar">Películas</a> <a
				href="<%=ctx%>/SvActorListar">Actores</a>
		</nav>
	</header>

	<main>

		<h2>Buscador</h2>

		<form class="formulario" action="<%=ctx%>/SvBuscar" method="get">

			<label> Buscar por <select name="tipo">

					<option value="pelicula"
						<%="actor".equals(tipo) ? "" : "selected"%>>Película</option>

					<option value="actor" <%="actor".equals(tipo) ? "selected" : ""%>>
						Actor</option>

			</select>
			</label> <label> Texto <input type="text" name="texto"
				value="<%=textoSeguro%>" required>
			</label>

			<button type="submit">Buscar</button>

		</form>


		<%-- RESULTADOS DE ACTORES --%>

		<%
		if (resultados != null) {
		%>

		<h2>Resultados</h2>

		<table>

			<tr>
				<th>Actor</th>
				<th>Edad</th>
				<th>Nacionalidad</th>
				<th>Premios</th>
				<th>Película</th>
			</tr>

			<%
			if (resultados.isEmpty()) {
			%>

			<tr>
				<td colspan="5" class="vacio">No se encontraron resultados.</td>
			</tr>

			<%
			} else {

			for (Actor a : resultados) {
			%>

			<tr>

				<td><%=a.getNombre()%> <%=a.getApellido()%></td>

				<td><%=a.getEdad()%></td>

				<td><%=a.getNacionalidad()%></td>

				<td><%=a.getCantPremios()%></td>

				<td><%=a.getPelicula() != null ? a.getPelicula().getTitulo() : "Sin película"%></td>

			</tr>

			<%
			}
			}
			%>

		</table>

		<%
		}
		%>


		<%-- RESULTADOS DE PELÍCULAS --%>

		<%
		if (resultados1 != null) {
		%>

		<h2>Resultados</h2>

		<table>

			<tr>
				<th>Película</th>
				<th>Género</th>
				<th>Director</th>
				<th>Año</th>
				<th>Actores</th>
			</tr>

			<%
			if (resultados1.isEmpty()) {
			%>

			<tr>
				<td colspan="5" class="vacio">No se encontraron resultados.</td>
			</tr>

			<%
			} else {

			for (Pelicula p : resultados1) {
			%>

			<tr>

				<td><%=p.getTitulo()%></td>

				<td><%=p.getGenero()%></td>

				<td><%=p.getDirector()%></td>

				<td><%=p.getAnio()%></td>

				<td>
					<%
					if (p.getListaActores() != null && !p.getListaActores().isEmpty()) {

						for (Actor a : p.getListaActores()) {
					%> <%=a.getNombre()%> <%=a.getApellido()%><br> <%
 }
 } else {
 %> Sin actores <%
 }
 %>

				</td>

			</tr>

			<%
			}
			}
			%>

		</table>

		<%
		}
		%>

	</main>

	<footer>Proyecto Cine</footer>

</body>
</html>
