package com.tew.persistence;

import com.tew.persistence.AlumnoDao;

/**
 * Interfaz de la factoria que suministra implementaciones reales de la fachada
 * de persistencia para cada Entidad del Modelo (en este caso solo hay una:
 * Alumno; pero en futuras versiones habra mas)
 * 
 * @author alb
 *
 */
public interface PersistenceFactory {

	AlumnoDao createAlumnoDao();

	CitaDao createCitaDao();

	ClienteDao createClienteDao();

	AgenteDao createAgenteDao();
}
