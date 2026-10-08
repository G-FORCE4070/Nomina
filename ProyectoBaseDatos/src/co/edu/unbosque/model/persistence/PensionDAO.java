package co.edu.unbosque.model.persistence;

import java.sql.*;
import java.util.ArrayList;

import co.edu.unbosque.model.PensionDTO;
import co.edu.unbosque.model.persistence.util.ConexionDB;

public class PensionDAO {

	private ArrayList<PensionDTO> listaPensiones;

	public PensionDAO() {
		listaPensiones = new ArrayList<>();
	}

	// Método para insertar una Pensión
	public void insertarPension(PensionDTO pension) {
		String sql = "INSERT INTO PENSION (NIT_PENSION, NOMBRE_FONDO) VALUES (?, ?)";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, pension.getNitPension());
			stmt.setString(2, pension.getNombreFondo());

			int filas = stmt.executeUpdate();
			if (filas > 0) {
				System.out.println("Pensión insertada correctamente.");
			}

		} catch (SQLException e) {
			System.out.println("Error al insertar pensión: " + e.getMessage());
		}
	}

	// Método para obtener todas las Pensiones
	public String readAll() {
		String sql = "SELECT * FROM PENSION";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				PensionDTO pension = new PensionDTO();
				pension.setNitPension(rs.getLong("NIT_PENSION"));
				pension.setNombreFondo(rs.getString("NOMBRE_FONDO"));
				resultado.append(pension.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}

	// Método para obtener una Pensión por su ID (reemplazado por NIT)
	public PensionDTO findOne(long nitPension) {
		String sql = "SELECT * FROM PENSION WHERE NIT_PENSION = ?";
		PensionDTO pension = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, nitPension);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				pension = new PensionDTO();
				pension.setNitPension(rs.getLong("NIT_PENSION"));
				pension.setNombreFondo(rs.getString("NOMBRE_FONDO"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pension;
	}

	// Método para obtener una Pensión por su NIT (igual que findOne)
	public PensionDTO findByNit(long nit) {
		return findOne(nit); // reutiliza findOne
	}

	// Método para actualizar una Pensión usando NIT
	public void actualizarPension(PensionDTO pension) {
		String sql = "UPDATE PENSION SET NOMBRE_FONDO = ? WHERE NIT_PENSION = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, pension.getNombreFondo());
			stmt.setLong(2, pension.getNitPension());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para eliminar una Pensión por NIT
	public void eliminarPension(long nitPension) {
		String sql = "DELETE FROM PENSION WHERE NIT_PENSION = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, nitPension);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
