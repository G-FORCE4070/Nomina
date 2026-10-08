package co.edu.unbosque.model.persistence;

import java.sql.*;
import java.util.ArrayList;

import co.edu.unbosque.model.DeduccionDTO;
import co.edu.unbosque.model.persistence.util.ConexionDB;

public class DeduccionDAO {

	private ArrayList<DeduccionDTO> listaDeducciones;

	public DeduccionDAO() {
		listaDeducciones = new ArrayList<>();
	}

	public void insertarDeduccion(DeduccionDTO deduccion) {
		String sql = "INSERT INTO DEDUCCION (TIPO_DEDUCCION, VALOR, RAZON) VALUES (?, ?, ?)";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, deduccion.getTipoDeduccion());
			stmt.setLong(2, deduccion.getValor());
			stmt.setString(3, deduccion.getRazon());

			int filas = stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error al insertar deducción: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public String readAll() {
		String sql = "SELECT * FROM DEDUCCION";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				DeduccionDTO deduccion = new DeduccionDTO();
				deduccion.setIdDeduccion(rs.getInt("ID_DEDUCCION"));
				deduccion.setTipoDeduccion(rs.getString("TIPO_DEDUCCION"));
				deduccion.setValor(rs.getLong("VALOR"));
				deduccion.setRazon(rs.getString("RAZON"));
				resultado.append(deduccion.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}

	public DeduccionDTO findOne(int idDeduccion) {
		String sql = "SELECT * FROM DEDUCCION WHERE ID_DEDUCCION = ?";
		DeduccionDTO deduccion = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idDeduccion);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				deduccion = new DeduccionDTO();
				deduccion.setIdDeduccion(rs.getInt("ID_DEDUCCION"));
				deduccion.setTipoDeduccion(rs.getString("TIPO_DEDUCCION"));
				deduccion.setValor(rs.getLong("VALOR"));
				deduccion.setRazon(rs.getString("RAZON"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return deduccion;
	}

	public DeduccionDTO findLast() {
		String sql = "SELECT * FROM DEDUCCION ORDER BY ID_DEDUCCION DESC LIMIT 1";
		DeduccionDTO deduccion = null;

		try (Connection conn = ConexionDB.conectar(); Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				deduccion = new DeduccionDTO();
				deduccion.setIdDeduccion(rs.getInt("ID_DEDUCCION"));
				deduccion.setTipoDeduccion(rs.getString("TIPO_DEDUCCION"));
				deduccion.setValor(rs.getLong("VALOR"));
				deduccion.setRazon(rs.getString("RAZON"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return deduccion;
	}

	public void actualizarDeduccion(DeduccionDTO deduccion) {
		String sql = "UPDATE DEDUCCION SET TIPO_DEDUCCION = ?, VALOR = ?, RAZON = ? WHERE ID_DEDUCCION = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, deduccion.getTipoDeduccion());
			stmt.setLong(2, deduccion.getValor());
			stmt.setString(3, deduccion.getRazon());
			stmt.setInt(4, deduccion.getIdDeduccion());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void eliminarDeduccion(int idDeduccion) {
		String sql = "DELETE FROM DEDUCCION WHERE ID_DEDUCCION = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idDeduccion);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
