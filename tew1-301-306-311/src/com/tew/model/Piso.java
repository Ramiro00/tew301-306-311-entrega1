package com.tew.model;

public class Piso {

	private int id;
	private int idagente;
	private int precio;
	private String direccion;
	private String ciudad;
	private int anyo;
	private int estado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdagente() {
		return idagente;
	}

	public void setIdagente(int idagente) {
		this.idagente = idagente;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int i) {
		this.estado = i;
	}

}