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

	// uso de inyecciÃ³n de dependencia
	@ManagedProperty(value = "#{piso}")
	private BeanPiso piso;

	public BeanPiso getPiso() {
		return piso;
	}

	public void setPiso(BeanPiso piso) {
		this.piso = piso;
	}

	public Locale getLocale() {
		// Aqui habria que cambiar algo de cÃ³digo para coger locale del
		// navegador
		// la primera vez que se accede a getLocale(), de momento dejamos como
		// idioma de partida â€œesâ€�
		return (locale);
	}

	public void setSpanish(ActionEvent event) {
		locale = SPANISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (piso != null)
				if (piso.getId()==0) //valores por defecto del alumno, si no NO inicializar
					piso.iniciaPiso(event);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setEnglish(ActionEvent event) {
		locale = ENGLISH;
		try {
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			if (piso != null)
				if (piso.getId()==0) //valores por defecto del alumno, si no NO inicializar
					piso.iniciaPiso(event);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Se inicia correctamente el Managed Bean inyectado si JSF lo hubiera
	// creado
	// y en caso contrario se crea.
	// (hay que tener en cuenta que es un Bean de sesionn)

	// Se usa @PostConstruct, ya que en el contructor no se sabe todavia
	// el MBean ya estaba construido y en @PostConstruct SI.
	@PostConstruct
	public void init() {
		System.out.println("BeanSettings - PostConstruct");
		// Buscamos el alumno en la sesiÃ³n. Esto es un patron factoria
		// claramente.
		piso = (BeanPiso) FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap().get(new String("alumno"));

		// si no existe lo creamos e inicializamos
		if (piso == null) {
			System.out.println("BeanSettings - No existia");
			piso = new BeanPiso();
			FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().put("piso", piso);
		}
	}

	//  a modo de traza.
	@PreDestroy
	public void end() {
		System.out.println("BeanSettings - PreDestroy");
	}

}
