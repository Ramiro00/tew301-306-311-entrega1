package impl.tew.persistence;
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

}
