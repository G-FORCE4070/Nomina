package co.edu.unbosque.model.persistence;

import java.sql.*;
import java.util.ArrayList;

import co.edu.unbosque.model.EpsDTO;
import co.edu.unbosque.model.persistence.util.ConexionDB;

public class EpsDAO {

	private ArrayList<EpsDTO> listaEps;

	public EpsDAO() {
		listaEps = new ArrayList<>();
	}

	// Método para obtener todas las EPS
	public String readAll() {
		String sql = "SELECT * FROM EPS";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				EpsDTO eps = new EpsDTO();
				eps.setNitEps(rs.getLong("NIT_EPS"));
				eps.setNombreEps(rs.getString("NOMBRE_EPS"));
				resultado.append(eps.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}

	// Método para obtener una EPS por su ID (modificado para usar NIT)
	public EpsDTO findOne(long nitEps) {
		String sql = "SELECT * FROM EPS WHERE NIT_EPS = ?";
		EpsDTO eps = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, nitEps);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				eps = new EpsDTO();
				eps.setNitEps(rs.getLong("NIT_EPS"));
				eps.setNombreEps(rs.getString("NOMBRE_EPS"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return eps;
	}

	// Método para obtener una EPS por su NIT (igual que findOne)
	public EpsDTO findByNit(long nit) {
		return findOne(nit); // Reutiliza findOne
	}

	// Método para actualizar una EPS (basado en NIT)
	public void actualizarEps(EpsDTO eps) {
		String sql = "UPDATE EPS SET NOMBRE_EPS = ? WHERE NIT_EPS = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, eps.getNombreEps());
			stmt.setLong(2, eps.getNitEps());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para eliminar una EPS por su NIT
	public void eliminarEps(long nitEps) {
		String sql = "DELETE FROM EPS WHERE NIT_EPS = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, nitEps);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
