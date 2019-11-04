package impl.tew.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.tew.model.Cita;
import com.tew.persistence.CitaDao;
import com.tew.persistence.exception.AlreadyPersistedException;
import com.tew.persistence.exception.PersistenceException;

/**
 * Implementacion de la interfaz de fachada al servicio de persistencia para
 * Alumnos. En este caso es Jdbc pero podria ser cualquier otra tecnologia de
 * persistencia, por ejemplo, la que veremos mas adelante JPA (mapeador de
 * objetos a relacional)
 * 
 * @author Javier
 *
 */
public class CitasJdbcDao implements CitaDao {

	public List<Cita> getCitas() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Cita> citas = new ArrayList<Cita>();

		try {
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("SELECT * FROM PISOPARAVISITAR");
			rs = ps.executeQuery();

			while (rs.next()) {
				Cita cita = new Cita();
				cita.setIdPiso(rs.getInt("IDPISO"));
				cita.setIdCliente(rs.getInt("IDCLIENTE"));
				cita.setFechaHoraCita(rs.getLong("FECHAHORACITA"));
				cita.setEstado(rs.getInt("ESTADO"));
				
/*				DateTimeFormatter formato = DateTimeFormatter.ofPattern( "MMdduuHHmmss" ) ;
				LocalDateTime ldt = LocalDateTime.parse(String.valueOf(rs.getLong("FECHAHORACITA")) , formato ) ;
				cita.setFecha(ldt.toString());*/
				
				citas.add(cita);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (rs != null) {
				try {rs.close();} catch (Exception ex) {}};
			if (ps != null) {
				try {
					ps.close();} catch (Exception ex) {}};
			if (con != null) {
				try {
					con.close();} catch (Exception ex) {}};
		}
		return citas;
	}

	@Override
	public void save(Cita c) throws AlreadyPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;

		try {
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

			// Obtenemos la conexion a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("insert into Cita (idPiso, idCliente, cita, estado) " + "values (?, ?, ?, ?)");
			ps.setInt(1, c.getIdPiso());
			ps.setInt(2, c.getIdCliente());
			ps.setLong(3, c.getFechaHoraCita());
			ps.setInt(4, c.getEstado());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Cita " + c + " already persisted");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (ps != null) {
				try {ps.close();} catch (Exception ex) {}};
			if (ps != null) {
				try {
					ps.close();} catch (Exception ex) {}};
			if (con != null) {
				try {
					con.close();} catch (Exception ex) {}};
		}
	}

	@Override
	public List<Cita> getCitas(String login) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		List<Cita> citas = new ArrayList<Cita>();

		try {
			String SQL_DRV = "org.hsqldb.jdbcDriver";
			String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("SELECT * FROM PISOPARAVISITAR");
			rs = ps.executeQuery();

			while (rs.next()) {
				Cita cita = new Cita();
				cita.setIdPiso(rs.getInt("IDPISO"));
				cita.setIdCliente(rs.getInt("IDCLIENTE"));
				cita.setFechaHoraCita(rs.getLong("FECHAHORACITA"));
				cita.setEstado(rs.getInt("ESTADO"));
				
				citas.add(cita);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally {
			if (rs != null) { 
				try { rs.close();} catch (Exception ex) {}};
			if (ps != null) {
				try { ps.close();} catch (Exception ex) {}};
			if (con != null) {
				try { con.close();} catch (Exception ex) {}};
		}

		return citas;
	}

	/*
	 * INSERT INTO "PUBLIC"."PISOPARAVISITAR" ( "IDPISO", "IDCLIENTE",
	 * "FECHAHORACITA", "ESTADO" ) VALUES (0 ,3 ,1572358635 ,1 )
	 */
}