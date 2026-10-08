package co.edu.unbosque.model.persistence;

import java.sql.*;
import java.util.ArrayList;

import co.edu.unbosque.model.DepartamentoDTO;
import co.edu.unbosque.model.persistence.util.ConexionDB;

public class DepartamentoDAO {

	private ArrayList<DepartamentoDTO> listaDepartamentos;

	public DepartamentoDAO() {
		listaDepartamentos = new ArrayList<>();
	}

	public void insertarDepartamento(DepartamentoDTO depto) {
		String sql = "INSERT INTO DEPARTAMENTO (NOMBRE_DEPARTAMENTO) VALUES (?)";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, depto.getNombreDepartamento());

			int filas = stmt.executeUpdate();
			if (filas > 0) {
				System.out.println("Departamento insertado correctamente.");
			}

		} catch (SQLException e) {
			System.out.println("Error al insertar departamento: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public String readAll() {
		String sql = "SELECT * FROM DEPARTAMENTO";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				DepartamentoDTO depto = new DepartamentoDTO();
				depto.setIdDepartamento(rs.getInt("ID_DEPARTAMENTO"));
				depto.setNombreDepartamento(rs.getString("NOMBRE_DEPARTAMENTO"));
				resultado.append(depto.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}

	public DepartamentoDTO findOne(int idDepartamento) {
		String sql = "SELECT * FROM DEPARTAMENTO WHERE ID_DEPARTAMENTO = ?";
		DepartamentoDTO depto = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idDepartamento);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				depto = new DepartamentoDTO();
				depto.setIdDepartamento(rs.getInt("ID_DEPARTAMENTO"));
				depto.setNombreDepartamento(rs.getString("NOMBRE_DEPARTAMENTO"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return depto;
	}

	public void actualizarDepartamento(DepartamentoDTO depto) {
		String sql = "UPDATE DEPARTAMENTO SET NOMBRE_DEPARTAMENTO = ? WHERE ID_DEPARTAMENTO = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, depto.getNombreDepartamento());
			stmt.setInt(2, depto.getIdDepartamento());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void eliminarDepartamento(int idDepartamento) {
		String sql = "DELETE FROM DEPARTAMENTO WHERE ID_DEPARTAMENTO = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idDepartamento);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
