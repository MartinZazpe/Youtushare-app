package com.ipartek.modelo.dto;

import java.util.Objects;

public class V_Cancion {
	
	private int id;
	private String name;
	private String grupo;
	private String enlace;
	private String comentario;
	private String genero;
	private String usuario;
	private int FK_genero;
	private int FK_usuario;
	private String enlaceId;
	private String user_rol;
	
	
	public V_Cancion(int id, String name, String grupo, String enlace, String comentario, String genero, String usuario,
			int fK_genero, int fK_usuario, String enlaceId, String user_rol) {
		super();
		this.id = id;
		this.name = name;
		this.grupo = grupo;
		this.enlace = enlace;
		this.comentario = comentario;
		this.genero = genero;
		this.usuario = usuario;
		FK_genero = fK_genero;
		FK_usuario = fK_usuario;
		this.enlaceId = enlaceId;
		this.user_rol = user_rol;
	}
	
	public V_Cancion() {
		super();
		this.id = 0;
		this.name = "";
		this.grupo = "";
		this.enlace = "";
		this.comentario = "";
		this.genero = "";
		this.usuario = "";
		FK_genero = 0;
		FK_usuario = 0;
		this.enlaceId = "";
		this.user_rol = "";
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	public String getUser_rol() {
		return user_rol;
	}

	public void setUser_rol(String user_rol) {
		this.user_rol = user_rol;
	}

	@Override
	public int hashCode() {
		return Objects.hash(FK_genero, FK_usuario, comentario, enlace, enlaceId, genero, grupo, id, name, user_rol,
				usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		V_Cancion other = (V_Cancion) obj;
		return FK_genero == other.FK_genero && FK_usuario == other.FK_usuario
				&& Objects.equals(comentario, other.comentario) && Objects.equals(enlace, other.enlace)
				&& Objects.equals(enlaceId, other.enlaceId) && Objects.equals(genero, other.genero)
				&& Objects.equals(grupo, other.grupo) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(user_rol, other.user_rol) && Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "V_Cancion [id=" + id + ", name=" + name + ", grupo=" + grupo + ", enlace=" + enlace + ", comentario="
				+ comentario + ", genero=" + genero + ", usuario=" + usuario + ", FK_genero=" + FK_genero
				+ ", FK_usuario=" + FK_usuario + ", enlaceId=" + enlaceId + ", user_rol=" + user_rol + "]";
	}
	


}



