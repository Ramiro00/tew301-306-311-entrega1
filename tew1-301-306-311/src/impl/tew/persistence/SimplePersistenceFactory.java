package impl.tew.persistence;


import com.tew.persistence.AgenteDao;
import com.tew.persistence.AlumnoDao;
import com.tew.persistence.CitaDao;
import com.tew.persistence.ClienteDao;
import com.tew.persistence.PersistenceFactory;

/**
 * Implementaci??????n de la factoria que devuelve implementaci??????n de la capa
 * de persistencia con Jdbc 
 * 
 * @author Enrique
 *
 */
public class SimplePersistenceFactory implements PersistenceFactory {

	@Override
	public AlumnoDao createAlumnoDao() {
		return new AlumnoJdbcDao();
	}

	
	public CitaDao createCitaDao() {
		return new CitasJdbcDao();
	}
	
	@Override
	public ClienteDao createClienteDao() {
		return new ClienteJdbcDao();
	}


	@Override
	public AgenteDao createAgenteDao() {
		return new AgenteJdbcDao();
	}
}
