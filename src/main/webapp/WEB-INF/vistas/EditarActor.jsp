
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.app.logica.Actor"%>

<%
/*
   Vista para editar un actor.

   La prepara el Servlet SvActorEditar (en su doGet),
   que antes de hacer forward a esta página tiene que dejar:

   request.setAttribute("actor", actor);

   El formulario manda los datos por POST al mismo SvActorEditar.
*/

String ctx = request.getContextPath();
Actor a = (Actor) request.getAttribute("actor");
%>

<!DOCTYPE html>
<html lang="es">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Editar Actor</title>
<link rel="stylesheet" href="<%=ctx%>/css/estilos.css">
</head>

<body>

	<header>
		<h1>Proyecto Actividad Final - Cine</h1>

		<nav>
			<a href="<%=ctx%>/SvActorListar">Actores</a> <a
				href="<%=ctx%>/SvPeliculaListar">Películas</a>
		</nav>
	</header>

	<main>

		<h2>Editar Actor</h2>

		<%
		if (a == null) {
		%>

		<div class="error">No se recibió ningún actor para editar.</div>

		<a class="boton secundario" href="<%=ctx%>/SvActorListar">
			Volver </a>

		<%
		} else {
		%>

		<form class="formulario" action="<%=ctx%>/SvActorEditar"
			method="post">

			<input type="hidden" name="id" value="<%=a.getId()%>"> <label>
				Nombre <input type="text" name="nombre" value="<%=a.getNombre()%>"
				required>
			</label> <label> Apellido <input type="text" name="apellido"
				value="<%=a.getApellido()%>" required>
			</label> <label> Edad <input type="number" name="edad" min="1"
				max="120" value="<%=a.getEdad()%>" required>
			</label> <label> Nacionalidad <input type="text" name="nacionalidad"
				value="<%=a.getNacionalidad()%>" required>
			</label> <label> Cantidad de premios <input type="number"
				name="cantPremios" min="0" max="120"
				value="<%=a.getCantPremios()%>" required>
			</label>

			<div>
				<button type="submit">Guardar cambios</button>

				<a class="boton secundario" href="<%=ctx%>/SvActorListar">
					Cancelar </a>
			</div>

		</form>

		<%
		}
		%>

	</main>

	<footer> Carina Yelma y Nicole Curci </footer>

</body>
</html>
