package com.tew.presentation;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "settings")
@SessionScoped
public class BeanSettings implements Serializable {
	private static final long serialVersionUID = 2L;
	private static final Locale ENGLISH = new Locale("en");
	private static final Locale SPANISH = new Locale("es");
	private Locale locale = new Locale("es");

	@ManagedProperty(value = "#{piso}")
	private BeanPiso piso;

	public BeanPiso getPiso() {
		return piso;
	}

	public void setPiso(BeanPiso piso) {
		this.piso = piso;
	}

	@ManagedProperty(value = "#{cita}")
	private BeanCita cita;
	// uso de inyección de dependencia

	public Locale getLocale() {

		// Aqui habria que cambiar algo de código para coger locale del
		// navegador
		// la primera vez que se accede a getLocale(), de momento dejamos como
		// idioma de partida “es”
		return (locale);
	}

	public void setSpanish(ActionEvent event) {
		locale = SPANISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (piso != null) {
				if (piso.getId() == 0) {
					piso.iniciaPiso(event);
				}
			}

			if (cita != null) {
				if (cita.getCita() == 0) {
					cita.iniciaCita(event);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setEnglish(ActionEvent event) {
		locale = ENGLISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (piso != null) {
				if (piso.getId() == 0) {
					piso.iniciaPiso(event);
				}
			}

			if (cita != null) {
				if (cita.getCita() == 0) {
					cita.iniciaCita(event);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@PostConstruct
	public void init() {
		System.out.println("BeanSettings - PostConstruct");

		piso = (BeanPiso) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(new String("piso"));
		cita = (BeanCita) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(new String("cita"));

		if (piso == null) {

			System.out.println("BeanSettings - No existia");

		}

		if (cita == null) {

			System.out.println("BeanSettings - No existia");
			piso = new BeanPiso();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("settings", piso);
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

	// a modo de traza.
	@PreDestroy
	public void end() {
		System.out.println("BeanSettings - PreDestroy");
	}

}
