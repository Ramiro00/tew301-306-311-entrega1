package com.tew.presentation;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.model.Cita;

@ManagedBean(name="cita")
@SessionScoped
public class BeanCita extends Cita implements Serializable {
	private static final long serialVersionUID = 6611899L;
	
	public BeanCita() {
		iniciaCita(null);
	}
	
	/**
	 * Este método es necesario para copiar la cita a editar cuando se pincha el
	 * enlace Editar en la vista listadoCitas.xhtml. Podría sustituirse por un método
	 * editar en BeanCitas.
	 */
	public void setCita(Cita cita) {
		setIdPiso(cita.getIdPiso());
		setIdCliente(cita.getIdCliente());
		setEstado(cita.getEstado());
		setCita(cita.getCita());
	}

	private void iniciaCita(ActionEvent event) {

	}

	

}
