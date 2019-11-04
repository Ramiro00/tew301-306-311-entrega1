package impl.tew.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tew.model.Agente;
import com.tew.model.Alumno;
import com.tew.persistence.AgenteDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.PersistenceException;

public class AgenteJdbcDao implements AgenteDao{
	
	@Override
	public Agente findByLogin(String login) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Agente agente  = null;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from AGENTES where login = ?");
			ps.setString(1, login);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				agente = new Agente();
				
				agente.setID(rs.getLong("ID"));
				agente.setLogin(rs.getString("LOGIN"));
				agente.setPassword(rs.getString("PASSWD"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return agente;
	}

	@Override
	public void save(Agente a) throws AlreadyPersistedException {
		
	}
}
