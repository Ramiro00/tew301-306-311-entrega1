package com.tew.presentation;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name="settings")
@SessionScoped
public class BeanSettings implements Serializable {
	private static final long serialVersionUID = 2L;
	private static final Locale ENGLISH = new Locale("en");
	private static final Locale SPANISH = new Locale("es");
	private Locale locale = new Locale("es");

	// uso de inyección de dependencia
	@ManagedProperty(value = "#{cita}")
	private BeanCita cita;

	public BeanCita getCita() {
		return cita;
	}

	public void setCita(BeanCita cita) {
		this.cita = cita;
	}

	public Locale getLocale() {
		/*
		 * Aqui habria que cambiar algo de código para coger locale del navegador la
		 * primera vez que se accede a getLocale(), de momento dejamos como idioma de
		 * partida “es”
		 */
		return (locale);
	}

	public void setSpanish(ActionEvent event) {
		locale = SPANISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (cita != null)
				if (cita.getCita() == 0) // valores por defecto del cita, si no NO inicializar
					cita.iniciaCita(event);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setEnglish(ActionEvent event) {
		locale = ENGLISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (cita != null)
				if (cita.getCita() == 0) // valores por defecto del cita, si no NO inicializar
					cita.iniciaCita(event);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * Se inicia correctamente el Managed Bean inyectado si JSF lo hubiera creado y
	 * en caso contrario se crea. hay que tener en cuenta que es un Bean de sesión)
	 * Se usa @PostConstruct, ya que en el contructor no se sabe todavía siel MBean
	 * ya estaba construido y en @PostConstruct SI.
	 */
	@PostConstruct
	public void init() {
		/*
		 * Buscamos el cita en la sesión. Esto es un patrón factoría claramente si no
		 * existe lo creamos e inicializamos
		 */
		System.out.println("BeanSettings - PostConstruct");
		cita = (BeanCita) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(new String("cita"));
		// si no existe lo creamos e inicializamos
		if (cita == null) {
			System.out.println("BeanSettings - No existia");
			cita = new BeanCita();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cita", cita);
		}
		/*
		 * Buscamos la cita en la sesión. Esto es un patrón factoría claramente si no
		 * existe lo creamos e inicializamos
		 */
		cita = (BeanCita) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(new String("cita"));
		if (cita != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("settings", cita);
		}
	}

	// Es sólo a modo de traza.
	@PreDestroy
	public void end() {
		System.out.println("BeanSettings - PreDestroy");
	}

}
