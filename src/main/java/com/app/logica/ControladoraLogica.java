package com.app.logica;

import java.util.List;
import java.util.List;
import com.app.logica.Actor;
import com.app.logica.Pelicula;
import com.app.persistencia.ControladoraPersistencia;

public class ControladoraLogica {

	// La controladora de persistencia se encarga de hablar con la BD.
	// Desde aquí la usamos como "puente" para guardar datos.
	ControladoraPersistencia controlPersis = new ControladoraPersistencia();

	// ----- ARTISTA -----
	// Método de la capa lógica para crear un artista.
	// Antes de guardarlo en la BD, se hacen validaciones.
	public void crearActor(Actor act) throws Exception {
		// Validación: el artista debe tener un nombre
		if (act.getNombre() == null || act.getNombre().isEmpty()) {
			throw new Exception("El actor debe tener un nombre.");
		}
		// Validación: el artista debe tener un género musical
		if (act.getApellido() == null || act.getApellido().isEmpty()) {
			throw new Exception("El actor debe tener un apellido.");
		}
		// Validación: la edad debe ser positiva
		if (act.getEdad() <= 0) {
			throw new Exception("La edad del artista debe ser mayor a 0.");
		}

		// AGREGAR AL MENOS UNA VALIDACIÓN MAS
		if (act.getNacionalidad() == null || act.getNacionalidad().isEmpty()) {
			throw new Exception("El actor debe aclarar su nacionalidad.");
		}
		if (act.getCantPremios() < 0) {
			throw new Exception("La cantidad de premios no puede ser negativa.");

		}

		// Si pasa todas las validaciones, recién ahí se manda a la persistencia
		controlPersis.crearActor(act);
	}

	// act 15
	// BUSCAR UN ARTISTA por su id
	public Actor buscarUnActor(int id) {
		return controlPersis.buscarActor(id);
	}

	// LISTAR TODOS LOS ARTISTAS
	public List<Actor> listarActores() {
		return controlPersis.buscarTodosLosActores();
	}

	// ----- ARTISTA -----

	public void editarActor(Actor act) throws Exception {
		if (act == null) {
			throw new Exception("El actor no existe.");
		}

		if (act.getNombre() == null || act.getNombre().trim().isEmpty()) {
			throw new Exception("El actor debe tener un nombre.");
		}

		if (act.getApellido() == null || act.getApellido().trim().isEmpty()) {
			throw new Exception("El actor debe tener un apellido.");
		}

		if (act.getEdad() <= 0) {
			throw new Exception("La edad debe ser mayor a 0.");
		}

		if (act.getNacionalidad() == null) {
			throw new Exception("El actor debe tener nacionalidad.");
		}
		// antes
		if (act.getCantPremios() <= 0) {
			throw new Exception("La cantidad de premios del actor debe ser mayor a 0.");
		}

		// después
		if (act.getCantPremios() < 0) {
			throw new Exception("La cantidad de premios no puede ser negativa.");
		}

		controlPersis.editarActor(act);
	}

	public void eliminarActor(int id) {
		controlPersis.eliminarActor(id);
	}

	// ----- CANCIÓN -----
	// Método de la capa lógica para crear una canción.
	// Se valida que tenga datos coherentes antes de persistirla.
	public void crearPelicula(Pelicula peli) throws Exception {
		// Validación: el título no puede estar vacío
		if (peli.getTitulo() == null || peli.getTitulo().isEmpty()) {
			throw new Exception("La pelicula debe tener un título.");
		}
		if (peli.getGenero() == null || peli.getGenero().isEmpty()) {
			throw new Exception("La pelicula debe tener un genero.");
		}
		// Validación: el año debe tener sentido
		if (peli.getAnio() < 1900) {
			throw new Exception("El año de la pelicula debe ser válido.");
		}
		// Validación: la duración debe ser mayor a cero
		if (peli.getDuracion() <= 0) {
			throw new Exception("La duración debe ser mayor a 0 segundos.");
		}
		// AGREGAR AL MENOS UNA VALIDACIÓN MAS
		if (peli.getDirector() == null || peli.getDirector().isEmpty()) {
			throw new Exception("La pelicula debe aclarar que director la dirigio.");
		}

		// Si pasa todas las validaciones, se guarda en la base de datos
		controlPersis.crearPelicula(peli);
	}

	// act 15
	// BUSCAR UNA CANCION por su id
	public Pelicula buscarUnaPelicula(int id) {
		return controlPersis.buscarPelicula(id);
	}

	// LISTAR TODAS LAS CANCIONES
	public List<Pelicula> listarPeliculas() {
		return controlPersis.buscarTodasLasPeliculas();
	}

	public void editarPelicula(Pelicula peli) throws Exception {
		if (peli == null) {
			throw new Exception("La pelicula no existe.");
		}

		if (peli.getTitulo() == null || peli.getTitulo().trim().isEmpty()) {
			throw new Exception("La pelicula debe tener un título.");
		}

		if (peli.getGenero() == null || peli.getGenero().trim().isEmpty()) {
			throw new Exception("La pelicula debe tener un genero.");
		}

		if (peli.getAnio() < 1800) {
			throw new Exception("El año de la pelicula debe ser válido.");
		}

		if (peli.getDuracion() < 0) {
			throw new Exception("La duracion de la pelicula debe ser válida.");
		}

		if (peli.getDirector() == null || peli.getDirector().trim().isEmpty()) {
			throw new Exception("La pelicula debe tener un director.");
		}

		controlPersis.editarPelicula(peli);
	}

	public void eliminarPelicula(int id) {
		controlPersis.eliminarPelicula(id);
	}

	// BUSCADOR: tipo "pelicula" busca por título, cualquier otro busca por actor
	public List<Actor> buscarActores(String tipo, String texto) {
		if ("pelicula".equals(tipo)) {
			return controlPersis.buscarActoresPorPelicula(texto);
		}
		return controlPersis.buscarActoresPorNombre(texto);
	}

	// BUSCAR ACTORES POR NOMBRE
	public List<Actor> buscarActoresPorNombre(String texto) {
		return controlPersis.buscarActoresPorNombre(texto);
	}

	// BUSCAR PELÍCULAS POR TÍTULO
	// BUSCAR PELÍCULAS POR TÍTULO
	public List<Pelicula> buscarPeliculasPorTitulo(String texto) {
	    return controlPersis.buscarPeliculasPorTitulo(texto);
	}
	}

