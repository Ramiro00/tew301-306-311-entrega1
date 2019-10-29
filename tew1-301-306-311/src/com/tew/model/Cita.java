package com.tew.model;

public class Cita {
	private int idPiso;
	private int idCliente;
	private long cita;
	private int estado;

	public int getIdPiso() {
		return idPiso;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public long getCita() {
		return cita;
	}

	public int getEstado() {
		return estado;
	}

	public void setIdPiso(int idPiso) {
		this.idPiso = idPiso;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setCita(long cita) {
		this.cita = cita;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
