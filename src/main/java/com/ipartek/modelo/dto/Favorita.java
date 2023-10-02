package com.ipartek.modelo.dto;

import java.util.Objects;

public class Favorita {
	
	
	private int id;
	private int FK_usuario_fav;
	private int FK_cancion_fav;

	public Favorita(int id, int fK_usuario_fav, int fK_cancion_fav) {
		super();
		this.id = id;
		FK_usuario_fav = fK_usuario_fav;
		FK_cancion_fav = fK_cancion_fav;
	}
	
	public Favorita() {
		super();
		this.id = 0;
		FK_usuario_fav = 0;
		FK_cancion_fav = 0;
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
		return "Favorita [id=" + id + ", FK_usuario_fav=" + FK_usuario_fav + ", FK_cancion_fav=" + FK_cancion_fav + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(FK_cancion_fav, FK_usuario_fav, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Favorita other = (Favorita) obj;
		return FK_cancion_fav == other.FK_cancion_fav && FK_usuario_fav == other.FK_usuario_fav && id == other.id;
	}
	
	
	
	
	
	
	
	

}
