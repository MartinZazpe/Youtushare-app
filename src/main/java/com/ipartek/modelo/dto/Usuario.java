package com.ipartek.modelo.dto;

import java.util.Objects;

public class Usuario {
	
	
	private int id;
	private String username;
	private String password;
	private int FK_rol;
	
	
	public Usuario(int id, String username, String password, int fK_rol) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.FK_rol = fK_rol;
	}
	
	
	public Usuario() {
		super();
		this.id = 0;
		this.username = "";
		this.password = "";
		this.FK_rol = 0;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getFK_rol() {
		return FK_rol;
	}


	public void setFK_rol(int fK_rol) {
		this.FK_rol = fK_rol;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", password=" + password + ", FK_rol=" + FK_rol + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(FK_rol, id, password, username);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return FK_rol == other.FK_rol && id == other.id && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
	
	
	
	
	
	
	
	
	

}
