package impl.tew.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.tew.model.Cliente;
import com.tew.persistence.ClienteDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.PersistenceException;

public class ClienteJdbcDao implements ClienteDao{

	@Override
	public void save(Cliente c) throws AlreadyPersistedException, SQLIntegrityConstraintViolationException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;

		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"insert into CLIENTES(LOGIN, PASSWD, NOMBRE, APELLIDOS, EMAIL) " +
					"values (?, ?, ?, ?, ?)");

			ps.setString(1, c.getLogin());
			ps.setString(2, c.getPassword());
			ps.setString(3, c.getNombre());
			ps.setString(4, c.getApellidos());
			ps.setString(5, c.getEmail());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Cliente " + c + " already persisted");
			} 

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		}
		catch (SQLIntegrityConstraintViolationException e) {
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}


	@Override
	public Cliente findByLogin(String login) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Cliente cliente = null;
		
		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a 
			// que sacarlas a un sistema de configuraci��n: 
			// xml, properties, descriptores de despliege, etc 
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from CLIENTES where login = ?");
			ps.setString(1, login);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				cliente = new Cliente();
				cliente.setID(rs.getLong("ID"));
				cliente.setNombre(rs.getString("NOMBRE"));
				cliente.setApellidos(rs.getString("APELLIDOS"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setLogin(rs.getString("LOGIN"));
				cliente.setPassword(rs.getString("PASSWD"));
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
		
		return cliente;
	}
	
}
