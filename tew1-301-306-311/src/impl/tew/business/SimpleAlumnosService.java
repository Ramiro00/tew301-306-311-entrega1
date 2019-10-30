package impl.tew.business;

import impl.tew.business.classes.*;

import java.util.List;

import com.tew.business.AlumnosService;
import com.tew.business.exception.EntityAlreadyExistsException;
import com.tew.business.exception.EntityNotFoundException;
import com.tew.model.Alumno;
import com.tew.model.Piso;

/**
 * Clase de implementación (una de las posibles) del interfaz de la fachada de
 * servicios
 * 
 * @author Enrique
 * 
 */
public class SimpleAlumnosService implements AlumnosService {

	@Override
	public List<Piso> getAlumnos() throws Exception {
		return new PisosListado().getPisos();
	}

	@Override
	public void saveAlumno(Piso alumno) throws EntityAlreadyExistsException {
		new PisosAlta().save(alumno);
	}

	@Override
	public void updateAlumno(Piso alumno) throws EntityNotFoundException {
		new PisosUpdate().update(alumno);
	}

	@Override
	public void deleteAlumno(int id) throws EntityNotFoundException {
		new PisosBaja().delete(id);
	}

	@Override
	public Piso findById(int id) throws EntityNotFoundException {
		return new PisosBuscar().find(id);
	}
}