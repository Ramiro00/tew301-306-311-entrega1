package impl.tew.persistence;

import java.sql.*;
import java.util.*;

import com.tew.model.Citas;
import com.tew.persistence.CitasDao;
import com.tew.persistence.exception.PersistenceException;


/**
 * Implementacion de la interfaz de fachada al servicio de persistencia para
 * Alumnos. En este caso es Jdbc pero podri�a ser cualquier otra tecnologia 
 * de persistencia, por ejemplo, la que veremos mas adelante JPA 
 * (mapeador de objetos a relacional)
 * 
 * @author Javier
 *
 */
public class CitasJdbcDao implements CitasDao {

	public List<Citas> getCitas() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Citas> citas = new ArrayList<Citas>();

		try {
			// En una implemenntacian mas sofisticada estas constantes habria 
			// que sacarlas a un sistema de configuracion: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexion a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from alumno");
			rs = ps.executeQuery();

			while (rs.next()) {
				Citas cita = new Citas();
				cita.setIdPiso(rs.getInt("IDPISO"));
				cita.setIdCliente(rs.getInt("IDCLIENTE"));
				cita.setCita(rs.getLong("FECHAHORACITA"));
				cita.setEstado(rs.getInt("ESTADO"));
				
				citas.add(cita);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return citas;
	}

}
