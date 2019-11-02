package com.tew.presentation;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import com.tew.business.PisosService;
import com.tew.infrastructure.Factories;
import com.tew.model.Piso;
import com.tew.model.User;

@ManagedBean(name = "control")
@SessionScoped
public class BeanPisos implements Serializable {
	private static final long serialVersionUID = 55555L;
	// Se añade este atributo de entidad para recibir el alumno concreto
	// selecionado de la tabla o de un formulario
	// Es necesario inicializarlo para que al entrar desde el formulario de
	// AltaForm.xml se puedan
	// dejar los avalores en un objeto existente.

	private Piso[] pisos = null;
	private Piso[] pisosfiltrados = null;

	// uso de inyección de dependencia
	@ManagedProperty(value = "#{piso}")
	private BeanPiso piso;

	public BeanPiso getPiso() {
		return piso;
	}

	public void setPiso(BeanPiso piso) {
		this.piso = piso;
	}

	@ManagedProperty(value = "#{signup}")
	private BeanSignUp signup;

	public BeanSignUp getSignUp() {
		return signup;
	}

	/*
	 * public BeanAlumnos() { iniciaAlumno(null); }
	 */

	public Piso[] getPisos() {
		return (pisos);
	}

	public void setAlumnos(Piso[] pisos) {
		this.pisos = pisos;
	}

	public void iniciaPisos(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		// Obtenemos el archivo de propiedades correspondiente al idioma que
		// tengamos seleccionado y que viene envuelto en facesContext
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
		piso.setId(Integer.valueOf((String) bundle.getObject("valorDefectoPisoId")));
		piso.setIdagente(Integer.valueOf((String) bundle.getObject("valorDefectoIdAgente")));
		piso.setPrecio(Integer.valueOf((String) bundle.getObject("valorDefectoPrecio")));
		piso.setDireccion(bundle.getString("valorDefectoDireccion"));
		piso.setCiudad(bundle.getString("valorDefectoCiudad"));
		piso.setAno(Integer.valueOf((String) bundle.getObject("valorDefectoAnyo")));
		piso.setEstado(Integer.valueOf((String) bundle.getObject("valorDefectoEstado")));
		piso.setVisita(false);
	}

	public String listado() {
		PisosService service;
		User user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("LOGGEDIN_USER");

		try {
			service = Factories.services.createPisosService();
			pisos = (Piso[]) service.getPisos().toArray(new Piso[0]);
			System.out.println(user.getLogin());
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String baja(Piso piso) {
		PisosService service;
		try {
			service = Factories.services.createPisosService();
			service.deletePiso(piso.getId());
			pisos = (Piso[]) service.getPisos().toArray(new Piso[0]);
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String edit() {
		PisosService service;
		try {
			service = Factories.services.createPisosService();
			piso = (BeanPiso) service.findById(piso.getId());
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String salva() {
		PisosService service;
		try {
			service = Factories.services.createPisosService();
			piso.setIdagente(FacesContext.getCurrentInstance().get);
			service.savePiso(piso);

			pisos = (Piso[]) service.getPisos().toArray(new Piso[0]);
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public boolean filterByPrice(Object value, Object filter, Locale locale) {
		String filterText = (filter == null) ? null : filter.toString().trim();
		if (filterText == null || filterText.equals("")) {
			return true;
		}

		if (value == null) {
			return false;
		}

		return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
	}

	@PostConstruct
	public void init() {
		System.out.println("BeanPisos - PostConstruct");
		piso = (BeanPiso) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(new String("piso"));

		if (piso == null) {
			System.out.println("BeanPisos - No existia");
			piso = new BeanPiso();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("piso", piso);
		}
	}

	@PreDestroy
	public void end() {
		System.out.println("BeanPisos - PreDestroy");
	}

}
