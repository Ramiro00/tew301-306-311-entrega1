package com.tew.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.business.CitasService;
import com.tew.infrastructure.Factories;
import com.tew.model.Cita;

/**
 * Se añade este atributo de entidad para recibir la cita en concreto
 * selecionada de la tabla o de un formulario. Es necesario inicializarlo para
 * que al entrar desde el formulario de altaCitaForm.xml se puedan dejar los
 * avalores en un objeto existente.
 * 
 */
@ManagedBean(name = "control")
@SessionScoped
public class BeanCitas implements Serializable {
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

	public void inicaCitas(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		// Obtenemos el archivo de propiedades correspondiente al idioma que
		// tengamos seleccionado y que viene envuelto en facesContext
		@SuppressWarnings("unused")
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		cita.setIdPiso(0);
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
