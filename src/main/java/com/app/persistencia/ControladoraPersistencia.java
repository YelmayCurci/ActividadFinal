package com.app.persistencia;

import java.util.List;

import com.app.logica.Actor;
import com.app.logica.Pelicula;

public class ControladoraPersistencia {
	// Artista: Creamos una instancia del controlador que sabe cómo hablar con la
	// base de datos
	ActorJpaController actorJpa = new ActorJpaController();

	// Cancion: Creamos una instancia del controlador que sabe cómo hablar con la
	// base de datos
	// INSTANCIAR OBJETO DE CANCIONJPACONTROLER AL IGUAL QUE SE HIZO CON ARTISTA
    PeliculaJpaController peliculaJpa = new PeliculaJpaController();
	// Este método es el que se usa desde otras capas para crear un ARTISTA
    
	public void crearActor(Actor actor) {
		// Delegamos la tarea al controlador especializado
		actorJpa.crear(actor);
	}
	public void editarActor(Actor actor) {
	    actorJpa.editar(actor);
	}

	public void eliminarActor(int id) {
	    actorJpa.eliminar(id);
	}
	//act 15
	public Actor buscarActor(int id) {
	return actorJpa.buscarUno(id);
	}
	// BUSCAR TODOS LOS ARTISTAS
	public List<Actor> buscarTodosLosActores() {
	return actorJpa.buscarTodos();
	
	
}
// AL IGUAL QUE CON ARTISTA, GENERAR EL MÉTODO “crearCancion()”

	public void crearPelicula(Pelicula pelicula) {
		// Delegamos la tarea al controlador especializado
		peliculaJpa.crear(pelicula);
	}
		public void editarPelicula(Pelicula pelicula) {
		    peliculaJpa.editar(pelicula);
		}

		public void eliminarPelicula(int id) {
		    peliculaJpa.eliminar(id);
		}
	
	// BUSCAR UNA CANCION
	public Pelicula buscarPelicula(int id) {
	return peliculaJpa.buscarUno(id);
	}
	// BUSCAR TODAS LAS CANCIONES
	public List<Pelicula> buscarTodasLasPeliculas() {
	return peliculaJpa.buscarTodos();
}
	public List<Actor> buscarActoresPorPelicula(String texto) {
		return peliculaJpa.buscarActoresPorPelicula(texto);
	}

	public List<Actor> buscarActoresPorNombre(String texto) {
		return peliculaJpa.buscarActoresPorNombre(texto);
	}
	public List<Pelicula> buscarPeliculasPorTitulo(String texto) {
	    return peliculaJpa.buscarPorTitulo(texto);
	}
}

