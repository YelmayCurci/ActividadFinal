package com.app.logica;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Entity
public class Actor implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Basic
	private String nombre;
	private String apellido;
	private int edad;
	private String nacionalidad;
	private int cantPremios;

	@ManyToOne
	@JoinColumn(name = "pelicula_id")
	private Pelicula pelicula;

	public Actor() {
	}

	public Actor(String nombre, String apellido, int edad, String nacionalidad, int cantPremios) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.nacionalidad = nacionalidad;
		this.cantPremios = cantPremios;
	}

	// getters/setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public int getCantPremios() {
		return cantPremios;
	}

	public void setCantPremios(int cantPremios) {
		this.cantPremios = cantPremios;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	@Override
	public String toString() {
		return "Actor: \n" + "ID: " + getId() + "\nNombre: " + getNombre() + "\nApellido: " + getApellido()
				+ "\nEdad: " + getEdad() + "\nNacionalidad: " + getNacionalidad()
				+ "\nCantidad de premios: " + getCantPremios();
	}
}