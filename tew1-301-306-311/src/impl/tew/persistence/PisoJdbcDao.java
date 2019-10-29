package impl.tew.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tew.model.Piso;
import com.tew.persistence.PisoDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.NotPersistedException;
import com.tew.persistence.exception.PersistenceException;

public class PisoJdbcDao implements PisoDao {

	public List<Piso> getPisos() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Piso> pisos = new ArrayList<Piso>();

		try {
			// En una implemenntaci��n m��s sofisticada estas constantes habr��a
			// que sacarlas a un sistema de configuraci��n:
			// xml, properties, descriptores de despliege, etc
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from Piso");
			rs = ps.executeQuery();

			while (rs.next()) {
				Piso piso = new Piso();
				piso.setId(rs.getInt("ID"));
				piso.setIdagente(rs.getInt("IDAgente"));
				piso.setPrecio(rs.getInt("Precio"));
				piso.setDireccion(rs.getString("Direccion"));
				piso.setCiudad(rs.getString("Ciudad"));
				piso.setAnyo(rs.getInt("Ano"));

				pisos.add(piso);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {
				}
			}
			;
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return pisos;

	}

	@Override
	public void update(Piso p) throws NotPersistedException {
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
			ps = con.prepareStatement("update Pisos "
					+ "set IDAgente = ?, Precio = ?, Direccion = ?, Ciudad = ?,Ano = ?,Estado = ?" + "where id = ?");

			ps.setInt(1, p.getIdagente());
			ps.setInt(2, p.getPrecio());
			ps.setString(3, p.getDireccion());
			ps.setString(4, p.getCiudad());
			ps.setInt(5, p.getAnyo());
			ps.setInt(6, p.getEstado());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Piso " + p + " not found");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}
	}

	@Override
	public void save(Piso p) throws AlreadyPersistedException {
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
					"insert into Piso (IDAgente, Precio, Direccion, Ciudad, Ano, Estado) " + "values (?, ?, ?, ?)");

			ps.setInt(1, p.getIdagente());
			ps.setInt(2, p.getPrecio());
			ps.setString(3, p.getDireccion());
			ps.setString(4, p.getCiudad());
			ps.setInt(5, p.getAnyo());
			ps.setInt(6, p.getEstado());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Piso " + p + " already persisted");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

	}

	@Override
	public void delete(int id) throws NotPersistedException {
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
			ps = con.prepareStatement("delete from Piso where id = ?");

			ps.setLong(1, id);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Piso " + id + " not found");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

	}

	@Override
	public Piso findById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Piso piso = null;

		try {
			// En una implementaci��n m��s sofisticada estas constantes habr��a
			// que sacarlas a un sistema de configuraci��n:
			// xml, properties, descriptores de despliege, etc
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from Piso where id = ?");
			ps.setLong(1, id);

			rs = ps.executeQuery();
			if (rs.next()) {
				piso = new Piso();

				piso.setId(rs.getInt("ID"));
				piso.setIdagente(rs.getInt("IDAgente"));
				piso.setPrecio(rs.getInt("Precio"));
				piso.setDireccion(rs.getString("Direccion"));
				piso.setCiudad(rs.getString("Ciudad"));
				piso.setAnyo(rs.getInt("Ano"));
				piso.setEstado(rs.getInt("Estado"));

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {
				}
			}
			;
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception ex) {
				}
			}
			;
			if (con != null) {
				try {
					con.close();
				} catch (Exception ex) {
				}
			}
			;
		}

		return piso;
	}

	

	

}
