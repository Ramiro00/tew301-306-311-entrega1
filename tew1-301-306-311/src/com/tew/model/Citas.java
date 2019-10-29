package com.tew.model;

public class Citas {
	private int idPiso;
	private long idCliente;
	private long cita;
	private int estado;

	public int getIdPiso() {
		return idPiso;
	}

	public long getIdCliente() {
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

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public void setCita(long cita) {
		this.cita = cita;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
