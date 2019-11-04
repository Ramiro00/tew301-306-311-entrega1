package com.tew.model;

public class Cita {
	private int idPiso;
	private int idCliente;
	private long fechaHoraCita;
	private int estado;
	private String estadoStr;
		
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
	
/*	public String getEstadoStr() {
		return estadoStr;
	}*/
	
	public String getEstadoStr() {
		int i = this.getEstado();
		switch (i) {
		case 1:
			this.estadoStr = "Seleccionado";
			break;
		case 2:
			this.estadoStr = "Citado";
			break;
		case 3:
			this.estadoStr = "Acpetado";
			break;
		default:
			this.estadoStr = "Seleccionadoo";
		}

		return this.estadoStr;
	}
	public void setEstadoStr(String estadoStr) {
		this.estadoStr = estadoStr;
	}

}