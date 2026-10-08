package co.edu.unbosque.model.persistence;

import java.sql.*;
import java.util.ArrayList;

import co.edu.unbosque.model.CajaCompensacionDTO;
import co.edu.unbosque.model.persistence.util.ConexionDB;

public class CajaCompensacionDAO {

	private ArrayList<CajaCompensacionDTO> listaCajaCompensaciones;

	public CajaCompensacionDAO() {
		listaCajaCompensaciones = new ArrayList<>();
	}

	// Método para insertar una Caja de Compensación
	public void insertarCajaCompensacion(CajaCompensacionDTO cajaCompensacion) {
		String sql = "INSERT INTO CAJA_COMPENSACION (NIT_CAJA, NOMBRE_CAJA) VALUES (?, ?)";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, cajaCompensacion.getNitCaja());
			stmt.setString(2, cajaCompensacion.getNombreCaja());

			int filas = stmt.executeUpdate();
			if (filas > 0) {
				System.out.println("Caja de Compensación insertada correctamente.");
			}

		} catch (SQLException e) {
			System.out.println("Error al insertar Caja de Compensación: " + e.getMessage());
		}
	}

	// Método para obtener todas las Cajas de Compensación
	public String readAll() {
		String sql = "SELECT * FROM CAJA_COMPENSACION";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				CajaCompensacionDTO cajaCompensacion = new CajaCompensacionDTO();
				cajaCompensacion.setNitCaja(rs.getLong("NIT_CAJA"));
				cajaCompensacion.setNombreCaja(rs.getString("NOMBRE_CAJA"));
				resultado.append(cajaCompensacion.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}

	// Método para buscar una Caja por su NIT
	public CajaCompensacionDTO findByNIT(long nit) {
		String sql = "SELECT * FROM CAJA_COMPENSACION WHERE NIT_CAJA = ?";
		CajaCompensacionDTO cajaCompensacion = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, nit);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				cajaCompensacion = new CajaCompensacionDTO();
				cajaCompensacion.setNitCaja(rs.getLong("NIT_CAJA"));
				cajaCompensacion.setNombreCaja(rs.getString("NOMBRE_CAJA"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cajaCompensacion;
	}

	// Método para obtener una Caja por NIT (sustituto funcional de findOne por ID)
	public CajaCompensacionDTO findOne(long nitCaja) {
		return findByNIT(nitCaja); // se reutiliza findByNIT como alias funcional
	}

	// Método para actualizar una Caja usando su NIT
	public void actualizarCajaCompensacion(CajaCompensacionDTO cajaCompensacion) {
		String sql = "UPDATE CAJA_COMPENSACION SET NOMBRE_CAJA = ? WHERE NIT_CAJA = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, cajaCompensacion.getNombreCaja());
			stmt.setLong(2, cajaCompensacion.getNitCaja());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para eliminar una Caja por su NIT (antes usaba ID_CAJA)
	public void eliminarCajaCompensacion(long nitCaja) {
		String sql = "DELETE FROM CAJA_COMPENSACION WHERE NIT_CAJA = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, nitCaja);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
