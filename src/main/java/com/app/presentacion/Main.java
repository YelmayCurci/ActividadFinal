package com.app.presentacion;

import com.app.logica.Actor;
import com.app.logica.Pelicula;
import com.app.logica.ControladoraLogica;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		// INSTANCIAR DOS ARTISTAS

		ControladoraLogica conLog = new ControladoraLogica();
		try {
			Actor Emma = new Actor("Emma", "Watson", 36, "Britanica", 28);
			conLog.crearActor(Emma);

			Actor Adam = new Actor("Adam", "Sandler", 60, "Estado Unidense", 83);
			conLog.crearActor(Adam);

			Pelicula Mujercitas = new Pelicula("Mujercitas", "Drama", 2020, 2.15f, "Greta Gerwig");
			conLog.crearPelicula(Mujercitas);

			Pelicula SCniños = new Pelicula("Son Como Niños", "Comedia", 2010, 1.42f, "Dennis Dugan");
			conLog.crearPelicula(SCniños);

			// CREAR AL MENOS DOS ARTISTAS, Y DOS CANCIONES
		} catch (Exception e) {
			System.out.println("No se pudo crear el elemento: " + e.getMessage());
		}
//act 15

		// ----- LECTURA DE ARTISTAS -----
		System.out.println("=== TODOS LOS ACTORES ===");

		List<Actor> actores = conLog.listarActores();
		for (Actor a : actores) {
			System.out.println(a);
		}
		System.out.println("=== BUSCAR ACTOR CON ID 1 ===");

		Actor encontrado = conLog.buscarUnActor(1);
		if (encontrado != null) {
			System.out.println(encontrado);
		} else {
			System.out.println("No existe un actor con ese id");
		}
		System.out.println("=== BUSCAR ACTOR CON ID 999 ===");
		Actor noExiste = conLog.buscarUnActor(999);
		if (noExiste != null) {
			System.out.println(noExiste);
		} else {
			System.out.println("No existe un actor con ese id");
		}

		// ----- LECTURA DE CANCIONES -----
		System.out.println("=== TODOS LAS PELICULAS ===");
		List<Pelicula> peliculas = conLog.listarPeliculas();
		for (Pelicula p : peliculas) {
			System.out.println(p);
		}
		System.out.println("=== BUSCAR PELICULA CON ID 1 ===");
		Pelicula encontrada = conLog.buscarUnaPelicula(1);
		if (encontrada != null) {
			System.out.println(encontrada);
		} else {
			System.out.println("No existe una pelicula con ese id");
		}
		System.out.println("=== BUSCAR PELICULA CON ID 999 ===");
		Pelicula NoExiste = conLog.buscarUnaPelicula(999);
		if (NoExiste != null) {
			System.out.println(NoExiste);
		} else {
			System.out.println("No existe una pelicula con ese id");
		}
	}
}
