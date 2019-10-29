package com.tew.presentation;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.tew.business.AlumnosService;
import com.tew.business.PisosService;
import com.tew.infrastructure.Factories;
import com.tew.model.Alumno;
import com.tew.model.Piso;

@ManagedBean
@SessionScoped
public class BeanPisos implements Serializable{
	      private static final long serialVersionUID = 55555L;
		  // Se añade este atributo de entidad para recibir el alumno concreto selecionado de la tabla o de un formulario
	      // Es necesario inicializarlo para que al entrar desde el formulario de AltaForm.xml se puedan
	      // dejar los avalores en un objeto existente.
	     
		
          private Piso[] pisos = null;
          
        //uso de inyección de dependencia
          @ManagedProperty(value="#{piso}") 
          private BeanPiso piso;
          public BeanPiso getAlumno() { return piso; }
          public void setAlumno(BeanPiso piso) {this.piso = piso;}
          
          
         /* public BeanAlumnos()
          {
        	  iniciaAlumno(null);
          }*/
		  
		  public Piso[] getPisos () {
			    return(pisos);
			  }
	      
	       public void setAlumnos(Piso[] pisos) {
				  this.pisos = pisos;
		     }  
	       public void iniciaPisos(ActionEvent event) {
	    	   FacesContext facesContext = FacesContext.getCurrentInstance();
	    	 //Obtenemos el archivo de propiedades correspondiente al idioma que
	    	 //tengamos seleccionado y que viene envuelto en facesContext
	    	 ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "msgs");
	    	 piso.setId(Integer.valueOf((String) bundle.getObject("valorDefectoPisoId")));
	    	 piso.setIdagente(Integer.valueOf((String) bundle.getObject("valorDefectoIdAgente")));
	    	 piso.setPrecio(Integer.valueOf((String) bundle.getObject("valorDefectoPrecio")));
	    	 piso.setDireccion(bundle.getString("valorDefectoDireccion"));
	    	 piso.setCiudad(bundle.getString("valorDefectoCiudad"));
	    	 piso.setAnyo(Integer.valueOf((String) bundle.getObject("valorDefectoAnyo")));
	    	 piso.setEstado(Integer.valueOf((String) bundle.getObject("valorDefectoEstado")));      
	       }
	       public String listado() {
		       PisosService service;
				  try {
				  // Acceso a la implementacion de la capa de negocio 
					// a trav��s de la factor��a
					service = Factories.services.createPisosService();
					// De esta forma le damos informaci��n a toArray para poder hacer el casting a Alumno[]
					pisos = (Piso [])service.getPisos().toArray(new Piso[0]);
					
					return "exito";
					
				  } catch (Exception e) {
					e.printStackTrace();  
					return "error";
				  }
				  
		 	  }
	       public String baja(Piso piso) {
		       PisosService service;
				  try {
				  // Acceso a la implementacion de la capa de negocio 
					// a trav��s de la factor��a
					service = Factories.services.createPisosService();
			      //Aliminamos el alumno seleccionado en la tabla
					service.deletePiso(piso.getId());
				  //Actualizamos el javabean de alumnos inyectado en la tabla.
					pisos = (Piso [])service.getPisos().toArray(new Piso[0]);
					return "exito";
					
				  } catch (Exception e) {
					e.printStackTrace();  
					return "error";
				  }
				  
		 	  }
	       public String edit() {
		       PisosService service;
				  try {
				  // Acceso a la implementacion de la capa de negocio 
					// a trav��s de la factor��a
					service = Factories.services.createPisosService();
			      //Recargamos el alumno seleccionado en la tabla de la base de datos por si hubiera cambios.
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
				  // Acceso a la implementacion de la capa de negocio 
					// a trav��s de la factor��a
					service = Factories.services.createPisosService();
			      //Salvamos o actualizamos el alumno segun sea una operacion de alta o de edici��n
					if (piso.getId() == 0) {
						service.savePiso(piso);
					}
					else {
						service.updatePiso(piso); 
					} 
					//Actualizamos el javabean de alumnos inyectado en la tabla
					pisos = (Piso [])service.getPisos().toArray(new Piso[0]);
					return "exito";
					
				  } catch (Exception e) {
					  e.printStackTrace();
					return "error";
				  }
				  
		 	  }
	     //Se inicia correctamente el MBean inyectado si JSF lo hubiera crea
	     //y en caso contrario se crea. (hay que tener en cuenta que es un Bean de sesión)
	     //Se usa @PostConstruct, ya que en el contructor no se sabe todavía si el Managed Bean
	     //ya estaba construido y en @PostConstruct SI.
	       
	     @PostConstruct
	     public void init() {    	  
	       System.out.println("BeanPisos - PostConstruct"); 
	       //Buscamos el alumno en la sesión. Esto es un patrón factoría claramente.
	       piso = (BeanPiso) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(new String("alumno"));
	       //si no existe lo creamos e inicializamos
	       if (piso == null) { 
	         System.out.println("BeanPisos - No existia");
	         piso = new BeanPiso();
	         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put( "piso", piso);
	       }
	     }
	     @PreDestroy
	     public void end()  {
	         System.out.println("BeanPisos - PreDestroy");
	     }

	}


	
