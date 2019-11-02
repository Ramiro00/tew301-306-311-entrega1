package com.tew.model;

public class Cliente {

	private int id;

	public Cliente(String nombre2, String apellidos2, String email2, String password2) {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String login;
	private String Nombre;
	private String Apellidos;
	private String email;
	private String password;

	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
