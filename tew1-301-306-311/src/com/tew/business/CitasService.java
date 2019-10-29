package com.tew.business;

import java.util.List;

import javax.persistence.EntityExistsException;

import com.tew.model.Cita;

/**
 * Este es el interfaz que ofrecera cualquier implementacion de la clase
 * fachada.
 * 
 * Al separar la implementacion de la fachada de su interfaz se permite cambiar
 * las implementaciones reales de la fachada. Esto es muy Util cuando se
 * necesita añadir funcionalidad extra como acceso remoto, web services, control
 * de acceso, etc. Al hacerlo de esta forma esos cambios solo afectan a las
 * factorias y no al contenido de las capas. Las factor��as, en un desarrollo
 * profesional, se configuran declarativamente (properties, xml, etc)
 * 
 * @author JaVi
 *
 */

public interface CitasService {

	List<Cita> getCitas() throws Exception;
	void saveCita(Cita cita) throws EntityExistsException;

}
