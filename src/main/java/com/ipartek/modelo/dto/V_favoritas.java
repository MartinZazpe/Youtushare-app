package com.ipartek.modelo.dto;

import java.util.Objects;

public class V_favoritas extends V_Cancion {
	

	private int id;
	private int FK_usuario_fav;
	private int FK_cancion_fav;
	
	
	public V_favoritas(int id, String name, String grupo, String enlace, String comentario, String genero,
			String usuario, int fK_genero, int fK_usuario, String enlaceId, String user_rol, int id2,
			int fK_usuario_fav, int fK_cancion_fav) {
		super(id, name, grupo, enlace, comentario, genero, usuario, fK_genero, fK_usuario, enlaceId, user_rol);
		this.id = id2;
		FK_usuario_fav = fK_usuario_fav;
		FK_cancion_fav = fK_cancion_fav;
	}


	public V_favoritas() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getFK_usuario_fav() {
		return FK_usuario_fav;
	}


	public void setFK_usuario_fav(int fK_usuario_fav) {
		FK_usuario_fav = fK_usuario_fav;
	}


	public int getFK_cancion_fav() {
		return FK_cancion_fav;
	}


	public void setFK_cancion_fav(int fK_cancion_fav) {
		FK_cancion_fav = fK_cancion_fav;
	}


	@Override
	public String toString() {
		return "V_favoritas [id=" + id + ", FK_usuario_fav=" + FK_usuario_fav + ", FK_cancion_fav=" + FK_cancion_fav
				+ "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(FK_cancion_fav, FK_usuario_fav, id);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		V_favoritas other = (V_favoritas) obj;
		return FK_cancion_fav == other.FK_cancion_fav && FK_usuario_fav == other.FK_usuario_fav && id == other.id;
	}
	

}

