package com.ipartek.modelo.dto;

import java.util.Objects;

public class V_Usuario {

	private int id;
	private String username;
	private String password;
	private String user_rol;

	public V_Usuario(int id, String username, String password, String user_rol) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.user_rol = user_rol;
	}
	

	public V_Usuario() {
		super();
		this.id = 0;
		this.username = "";
		this.password = "";
		this.user_rol = "";
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


	public String getUser_rol() {
		return user_rol;
	}


	public void setUser_rol(String user_rol) {
		this.user_rol = user_rol;
	}


	@Override
	public String toString() {
		return "V_Usuario [id=" + id + ", username=" + username + ", password=" + password + ", user_rol=" + user_rol
				+ "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, password, user_rol, username);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		V_Usuario other = (V_Usuario) obj;
		return id == other.id && Objects.equals(password, other.password) && Objects.equals(user_rol, other.user_rol)
				&& Objects.equals(username, other.username);
	}
	
	
	
	
	
	
	
}
