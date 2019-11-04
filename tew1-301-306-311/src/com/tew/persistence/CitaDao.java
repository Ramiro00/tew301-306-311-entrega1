package com.tew.persistence;

import java.util.List;

import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Cita;
import com.tew.model.Piso;
import com.tew.persistence.exception.AlreadyPersistedException;

/**
 * Interfaz de la fachada a servicios de persistencia para la entidad Citas.
 * 
 * En esta version aparecen los otros metodos basicos de un servicio de
 * persistencia
 * 
 * @author JaVi
 *
 */
public interface CitaDao {

	List<Cita> getCitas();

	List<Cita> getCitas(String login);

	
	void save(Cita c) throws AlreadyPersistedException;

	List<Piso> getPisos(String id);

	void confirmaVisita(Cita c) throws EntityNotFoundException;

}