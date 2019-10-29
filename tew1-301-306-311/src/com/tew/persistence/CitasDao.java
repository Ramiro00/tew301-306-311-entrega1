package com.tew.persistence;

import java.util.List;

import com.tew.model.Citas;

/**
 * Interfaz de la fachada a servicios de persistencia para la entidad Citas.
 * 
 * En esta version aparecen los otros metodos basicos de un servicio 
 * de persistencia
 * 
 * @author JaVi
 *
 */
public interface CitasDao {
	
	List<Citas> getCitas();

}
