package com.tew.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedProperty;

import com.tew.business.CitasService;
import com.tew.infrastructure.Factories;
import com.tew.model.Cita;

public class BeanCitas implements Serializable {

	/**
	 * Se añade este atributo de entidad para recibir la cita en concreto
	 * selecionada de la tabla o de un formulario. Es necesario inicializarlo para
	 * que al entrar desde el formulario de altaCitaForm.xml se puedan dejar los
	 * avalores en un objeto existente.
	 * 
	 */
	private static final long serialVersionUID = 7944042841591604009L;

	private Cita[] citas = null;

	// uso de inteccción de dependencia
	@ManagedProperty(value = "#{cita}")
	private BeanCita cita;

	public BeanCita getCita() {
		return cita;

	}

	public void setCta(BeanCita cita) {
		this.cita = cita;
	}

	public Cita[] getCitas() {
		return (citas);
	}

	public String listado() {
		CitasService service;
		try {
			// Acceso a la implementacion de la capa de negocio a traves de la factoria
			service = Factories.services.createCitasService();
			citas = (Cita[]) service.getCitas().toArray(new Cita[0]);
			return "exito";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

}
