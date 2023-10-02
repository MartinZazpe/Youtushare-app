package com.ipartek.modelo.dto;

import java.util.Objects;

public class Cancion {

	private int id;
	private String name;
	private String grupo;
	private String enlace;
	private String comentario;
	private int FK_genero;
	private int FK_usuario;
	private String enlaceId;
	
	public Cancion(int id, String name, String grupo, String enlace, String comentario, int fK_genero, int fK_usuario,
			String enlaceId) {
		super();
		this.id = id;
		this.name = name;
		this.grupo = grupo;
		this.enlace = enlace;
		this.comentario = comentario;
		this.FK_genero = fK_genero;
		this.FK_usuario = fK_usuario;
		this.enlaceId = enlaceId;
	}
	
	
	public Cancion() {
		super();
		this.id = 0;
		this.name = "";
		this.grupo = "";
		this.enlace = "";
		this.comentario = "";
		this.FK_genero = 0;
		this.FK_usuario = 0;
		this.enlaceId = "";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGrupo() {
		return grupo;
	}


	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}


	public String getEnlace() {
		return enlace;
	}


	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}


	public String getComentario() {
		return comentario;
	}


	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	public int getFK_genero() {
		return FK_genero;
	}


	public void setFK_genero(int fK_genero) {
		FK_genero = fK_genero;
	}


	public int getFK_usuario() {
		return FK_usuario;
	}


	public void setFK_usuario(int fK_usuario) {
		FK_usuario = fK_usuario;
	}


	public String getEnlaceId() {
		return enlaceId;
	}


	public void setEnlaceId(String enlaceId) {
		this.enlaceId = enlaceId;
	}


	@Override
	public String toString() {
		return "Cancion [id=" + id + ", name=" + name + ", grupo=" + grupo + ", enlace=" + enlace + ", comentario="
				+ comentario + ", FK_genero=" + FK_genero + ", FK_usuario=" + FK_usuario + ", enlaceId=" + enlaceId
				+ "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(FK_genero, FK_usuario, comentario, enlace, enlaceId, grupo, id, name);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cancion other = (Cancion) obj;
		return FK_genero == other.FK_genero && FK_usuario == other.FK_usuario
				&& Objects.equals(comentario, other.comentario) && Objects.equals(enlace, other.enlace)
				&& Objects.equals(enlaceId, other.enlaceId) && Objects.equals(grupo, other.grupo) && id == other.id
				&& Objects.equals(name, other.name);
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
}

