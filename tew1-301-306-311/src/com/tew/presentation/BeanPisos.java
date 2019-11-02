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
		try {
			service = Factories.services.createPisosService();
			pisos = (Piso[]) service.getPisos().toArray(new Piso[0]);
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String filtrar() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String min = params.get("action");
		String max = params.get("action2");

		PisosService service;
		try {
			service = Factories.services.createPisosService();

			Piso[] p = (Piso[]) service.getPisos(Integer.parseInt(min), Integer.parseInt(max)).toArray(new Piso[0]);

			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public String filterminPrice(int min) {
		PisosService service;
		try {
			service = Factories.services.createPisosService();
			Piso[] p = (Piso[]) service.getPisos().toArray(new Piso[0]);
			for (int i = 0; i < p.length; i++) {
				if (p[i].getPrecio() > min) {

				}

			}

			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	public void guardarSeleccion() {

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
			service.savePiso(piso);

			pisos = (Piso[]) service.getPisos().toArray(new Piso[0]);
			return "exito";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

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
