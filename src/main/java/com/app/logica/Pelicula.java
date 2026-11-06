package com.app.logica;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pelicula implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Basic
	private String titulo;
	private String genero;
	private int anio;
	private float duracion;
	private String director;

	@OneToMany(mappedBy = "pelicula")
	private List<Actor> listaActores = new ArrayList<>();

	public Pelicula() {
	}

	public Pelicula(String titulo, String genero, int anio, float duracion, String director) {
		this.titulo = titulo;
		this.genero = genero;
		this.anio = anio;
		this.duracion = duracion;
		this.director = director;
	}

	// getters/setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public float getDuracion() {
		return duracion;
	}

	public void setDuracion(float duracion) {
		this.duracion = duracion;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public List<Actor> getListaActores() {
		return listaActores;
	}

	public void setListaActores(List<Actor> listaActores) {
		this.listaActores = listaActores;
	}

	@Override
	public String toString() {
		return "Pelicula: \n" + "ID: " + getId() + "\nTitulo: " + getTitulo() + "\nGenero: " + getGenero()
				+ "\nAnio: " + getAnio() + "\nDuracion: " + getDuracion() + "\nDirector: "
				+ getDirector();
	}
}