package co.edu.unbosque.model.persistence;

import java.sql.*;
import java.util.ArrayList;

import co.edu.unbosque.model.ArlDTO;
import co.edu.unbosque.model.persistence.util.ConexionDB;

public class ArlDAO {

	private ArrayList<ArlDTO> listaArls;

	public ArlDAO() {
		listaArls = new ArrayList<>();
	}

	// Método para insertar una ARL
	public void insertarArl(ArlDTO arl) {
		String sql = "INSERT INTO ARL (NIT_ARL, NOMBRE_ARL, NUMERO_CONTACTO) VALUES (?, ?, ?)";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, arl.getNitArl());
			stmt.setString(2, arl.getNombreArl());
			stmt.setLong(3, arl.getNumeroContacto());

			int filas = stmt.executeUpdate();
			if (filas > 0) {
				System.out.println("ARL insertada correctamente.");
			}

		} catch (SQLException e) {
			System.out.println("Error al insertar ARL: " + e.getMessage());
		}
	}

	// Método para obtener todas las ARLs
	public String readAll() {
		String sql = "SELECT * FROM ARL";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				ArlDTO arl = new ArlDTO();
				arl.setNitArl(rs.getLong("NIT_ARL"));
				arl.setNombreArl(rs.getString("NOMBRE_ARL"));
				arl.setNumeroContacto(rs.getLong("NUMERO_CONTACTO"));
				resultado.append(arl.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}

	// Método para obtener una ARL por su NIT
	public ArlDTO findByNIT(long nit) {
		String sql = "SELECT * FROM ARL WHERE NIT_ARL = ?";
		ArlDTO arl = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, nit);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				arl = new ArlDTO();
				arl.setNitArl(rs.getLong("NIT_ARL"));
				arl.setNombreArl(rs.getString("NOMBRE_ARL"));
				arl.setNumeroContacto(rs.getLong("NUMERO_CONTACTO"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arl;
	}

	// Método para actualizar una ARL por NIT
	public void actualizarArl(ArlDTO arl) {
		String sql = "UPDATE ARL SET NOMBRE_ARL = ?, NUMERO_CONTACTO = ? WHERE NIT_ARL = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, arl.getNombreArl());
			stmt.setLong(2, arl.getNumeroContacto());
			stmt.setLong(3, arl.getNitArl());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para eliminar una ARL por su NIT
	public void eliminarArl(long nitArl) {
		String sql = "DELETE FROM ARL WHERE NIT_ARL = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, nitArl);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
