package com.ipartek.modelo.dto;

import java.util.Objects;

public class Genero {

	private int id=0;
	private String nombre;
	
	
	public Genero(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	

	public Genero() {
		super();
		this.id = 0;
		this.nombre = "";
	}



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



	@Override
	public String toString() {
		return "Genero [id=" + id + ", nombre=" + nombre + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genero other = (Genero) obj;
		return id == other.id && Objects.equals(nombre, other.nombre);
	}
	
	
	
	
	
	
	
}
