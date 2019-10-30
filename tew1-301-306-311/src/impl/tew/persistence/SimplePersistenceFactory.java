package impl.tew.persistence;
<<<<<<< HEAD
=======


import com.tew.persistence.AlumnoDao;
import com.tew.persistence.CitaDao;
>>>>>>> refs/remotes/origin/Generales
import com.tew.persistence.PersistenceFactory;
import com.tew.persistence.PisoDao;

/**
 * Implementaci??????n de la factoria que devuelve implementaci??????n de la
 * capa de persistencia con Jdbc
 * 
 * @author Enrique
 *
 */
public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public PisoDao createPisoDao() {
		// TODO Auto-generated method stub
		return new PisoJdbcDao();
	}

	
	public CitaDao createCitaDao() {
		return new CitasJdbcDao();
	}
}
