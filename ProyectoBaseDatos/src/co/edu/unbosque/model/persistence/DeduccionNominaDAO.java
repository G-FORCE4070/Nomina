package co.edu.unbosque.model.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.DeduccionNominaDTO;
import co.edu.unbosque.model.persistence.util.ConexionDB;

public class DeduccionNominaDAO {

	private ArrayList<DeduccionNominaDTO> listaDeduccionNominas;

	public DeduccionNominaDAO() {
		listaDeduccionNominas = new ArrayList<>();
	}

	public void insertarDeduccionNomina(DeduccionNominaDTO deduccionNomina) {
		String sql = "INSERT INTO DEDUCCION_NOMINA (NOMINA_ID_NOMINA, DEDUCCION_ID_DEDUCCION) VALUES (?, ?)";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, deduccionNomina.getIdNomina());
			stmt.setInt(2, deduccionNomina.getIdDeduccion());

			stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al insertar relación deducción: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public long calcularTotal(int idNomina) {
		String sql = "SELECT NOMINA_ID_NOMINA, SUM(VALOR) AS TOTAL " + "FROM DEDUCCION_NOMINA JOIN DEDUCCION "
				+ "ON DEDUCCION_ID_DEDUCCION = ID_DEDUCCION " + "WHERE NOMINA_ID_NOMINA = ? "
				+ "GROUP BY NOMINA_ID_NOMINA";
		long total = 0;
		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idNomina);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				total += rs.getLong("TOTAL");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

	public List<DeduccionNominaDTO> findAll() {
		String sql = "SELECT * FROM DEDUCCION_NOMINA";
		List<DeduccionNominaDTO> lista = new ArrayList<>();

		try (Connection conn = ConexionDB.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				DeduccionNominaDTO deduccionNomina = new DeduccionNominaDTO();
				deduccionNomina.setIdNomina(rs.getInt("NOMINA_ID_NOMINA"));
				deduccionNomina.setIdDeduccion(rs.getInt("DEDUCCION_ID_DEDUCCION"));
				lista.add(deduccionNomina);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public String readAll() {
		String sql = "SELECT * FROM DEDUCCION_NOMINA";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				DeduccionNominaDTO deduccionNomina = new DeduccionNominaDTO();
				deduccionNomina.setIdNomina(rs.getInt("NOMINA_ID_NOMINA"));
				deduccionNomina.setIdDeduccion(rs.getInt("DEDUCCION_ID_DEDUCCION"));
				resultado.append(deduccionNomina.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}

	public DeduccionNominaDTO findOne(int idNomina, int idDeduccion) {
		String sql = "SELECT * FROM DEDUCCION_NOMINA WHERE NOMINA_ID_NOMINA = ? AND DEDUCCION_ID_DEDUCCION = ?";
		DeduccionNominaDTO deduccionNomina = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idNomina);
			stmt.setInt(2, idDeduccion);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				deduccionNomina = new DeduccionNominaDTO();
				deduccionNomina.setIdNomina(rs.getInt("NOMINA_ID_NOMINA"));
				deduccionNomina.setIdDeduccion(rs.getInt("DEDUCCION_ID_DEDUCCION"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return deduccionNomina;
	}

	public void eliminarDeduccionNomina(int idNomina, int idDeduccion) {
		String sql = "DELETE FROM DEDUCCION_NOMINA WHERE NOMINA_ID_NOMINA = ? AND DEDUCCION_ID_DEDUCCION = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idNomina);
			stmt.setInt(2, idDeduccion);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
