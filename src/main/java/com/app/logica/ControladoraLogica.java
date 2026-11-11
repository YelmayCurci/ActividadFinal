
package com.app.logica;

import java.util.List;
import com.app.persistencia.ControladoraPersistencia;

public class ControladoraLogica {

	// La controladora de persistencia se encarga de hablar con la BD.
	ControladoraPersistencia controlPersis = new ControladoraPersistencia();

	// ----- ACTORES -----

	// Crear un actor
	public void crearActor(Actor act) throws Exception {

		if (act.getNombre() == null || act.getNombre().isEmpty()) {
			throw new Exception("El actor debe tener un nombre.");
		}

		if (act.getApellido() == null || act.getApellido().isEmpty()) {
			throw new Exception("El actor debe tener un apellido.");
		}

		if (act.getEdad() <= 0) {
			throw new Exception("La edad del actor debe ser mayor a 0.");
		}

		if (act.getNacionalidad() == null || act.getNacionalidad().isEmpty()) {
			throw new Exception("El actor debe aclarar su nacionalidad.");
		}

		if (act.getCantPremios() < 0) {
			throw new Exception("La cantidad de premios no puede ser negativa.");
		}

		controlPersis.crearActor(act);
	}

	// Buscar un actor por su ID
	public Actor buscarUnActor(int id) {
		return controlPersis.buscarActor(id);
	}

	// Listar todos los actores
	public List<Actor> listarActores() {
		return controlPersis.buscarTodosLosActores();
	}

	// Editar un actor
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

		if (act.getNacionalidad() == null || act.getNacionalidad().trim().isEmpty()) {
			throw new Exception("El actor debe tener nacionalidad.");
		}

		if (act.getCantPremios() < 0) {
			throw new Exception("La cantidad de premios no puede ser negativa.");
		}

		controlPersis.editarActor(act);
	}

	// Eliminar un actor
	public void eliminarActor(int id) {
		controlPersis.eliminarActor(id);
	}

	// ----- PELÍCULAS -----

	// Crear una película
	public void crearPelicula(Pelicula peli) throws Exception {

		if (peli.getTitulo() == null || peli.getTitulo().isEmpty()) {
			throw new Exception("La película debe tener un título.");
		}

		if (peli.getGenero() == null || peli.getGenero().isEmpty()) {
			throw new Exception("La película debe tener un género.");
		}

		if (peli.getAnio() < 1900) {
			throw new Exception("El año de la película debe ser válido.");
		}

		if (peli.getDuracion() <= 0) {
			throw new Exception("La duración debe ser mayor a 0 minutos.");
		}

		if (peli.getDirector() == null || peli.getDirector().isEmpty()) {
			throw new Exception("La película debe aclarar qué director la dirigió.");
		}

		controlPersis.crearPelicula(peli);
	}

	// Buscar una película por su ID
	public Pelicula buscarUnaPelicula(int id) {
		return controlPersis.buscarPelicula(id);
	}

	// Listar todas las películas
	public List<Pelicula> listarPeliculas() {
		return controlPersis.buscarTodasLasPeliculas();
	}

	// Editar una película
	public void editarPelicula(Pelicula peli) throws Exception {

		if (peli == null) {
			throw new Exception("La película no existe.");
		}

		if (peli.getTitulo() == null || peli.getTitulo().trim().isEmpty()) {
			throw new Exception("La película debe tener un título.");
		}

		if (peli.getGenero() == null || peli.getGenero().trim().isEmpty()) {
			throw new Exception("La película debe tener un género.");
		}

		if (peli.getAnio() < 1900) {
			throw new Exception("El año de la película debe ser válido.");
		}

		if (peli.getDuracion() <= 0) {
			throw new Exception("La duración de la película debe ser mayor a 0.");
		}

		if (peli.getDirector() == null || peli.getDirector().trim().isEmpty()) {
			throw new Exception("La película debe tener un director.");
		}

		controlPersis.editarPelicula(peli);
	}

	// Eliminar una película
	public void eliminarPelicula(int id) {
		controlPersis.eliminarPelicula(id);
	}

	// ----- BUSCADOR -----

	// Buscar actores por película o por nombre
	public List<Actor> buscarActores(String tipo, String texto) {

		if ("pelicula".equals(tipo)) {
			return controlPersis.buscarActoresPorPelicula(texto);
		}

		return controlPersis.buscarActoresPorNombre(texto);
	}

	// Buscar actores por nombre
	public List<Actor> buscarActoresPorNombre(String texto) {
		return controlPersis.buscarActoresPorNombre(texto);
	}

	// Buscar películas por título
	public List<Pelicula> buscarPeliculasPorTitulo(String texto) {
		return controlPersis.buscarPeliculasPorTitulo(texto);
	}

}
