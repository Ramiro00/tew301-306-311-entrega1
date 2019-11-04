package com.tew.model;

public class Cita {
	private int idPiso;
	private int idCliente;
	private long fechaHoraCita;
	private int estado;
	
	public int getIdPiso() {
		return idPiso;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public long getFechaHoraCita() {
		return fechaHoraCita;
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
	public void setFechaHoraCita(long fechaHoraCita) {
		this.fechaHoraCita = fechaHoraCita;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}

}