package co.edu.unbosque.model.persistence;

import java.sql.*;
import java.util.ArrayList;

import co.edu.unbosque.model.BonificacionDTO;
import co.edu.unbosque.model.persistence.util.ConexionDB;

public class BonificacionDAO {

	private ArrayList<BonificacionDTO> listaBonificaciones;

	public BonificacionDAO() {
		listaBonificaciones = new ArrayList<>();
	}

	// Método para insertar una bonificación
	public void insertarBonificacion(BonificacionDTO bonificacion) {
		String sql = "INSERT INTO BONIFICACION (VALOR, TIPO_BONIFICACION) VALUES (?, ?)";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, bonificacion.getValor());
			stmt.setString(2, bonificacion.getTipoBonificacion());

			int filas = stmt.executeUpdate();
			if (filas > 0) {
				System.out.println("Bonificación insertada correctamente.");
			}

		} catch (SQLException e) {
			System.out.println("Error al insertar bonificación: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Método para obtener todas las bonificaciones
	public String readAll() {
		String sql = "SELECT * FROM BONIFICACION";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				BonificacionDTO bonificacion = new BonificacionDTO();
				bonificacion.setIdBonificacion(rs.getInt("ID_BONIFICACION"));
				bonificacion.setValor(rs.getLong("VALOR"));
				bonificacion.setTipoBonificacion(rs.getString("TIPO_BONIFICACION"));
				resultado.append(bonificacion.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}

	// Método para obtener una bonificación por su ID
	public BonificacionDTO findOne(int idBonificacion) {
		String sql = "SELECT * FROM BONIFICACION WHERE ID_BONIFICACION = ?";
		BonificacionDTO bonificacion = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idBonificacion);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				bonificacion = new BonificacionDTO();
				bonificacion.setIdBonificacion(rs.getInt("ID_BONIFICACION"));
				bonificacion.setValor(rs.getLong("VALOR"));
				bonificacion.setTipoBonificacion(rs.getString("TIPO_BONIFICACION"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bonificacion;
	}

	public BonificacionDTO findLast() {
		String sql = "SELECT * FROM BONIFICACION ORDER BY ID_BONIFICACION DESC LIMIT 1";
		BonificacionDTO bonificacion = null;

		try (Connection conn = ConexionDB.conectar(); Statement stmt = conn.createStatement()) {


			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				bonificacion = new BonificacionDTO();
				bonificacion.setIdBonificacion(rs.getInt("ID_BONIFICACION"));
				bonificacion.setValor(rs.getLong("VALOR"));
				bonificacion.setTipoBonificacion(rs.getString("TIPO_BONIFICACION"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bonificacion;
	}
	// Método para actualizar una bonificación
	public void actualizarBonificacion(BonificacionDTO bonificacion) {
		String sql = "UPDATE BONIFICACION SET VALOR = ?, TIPO_BONIFICACION = ? WHERE ID_BONIFICACION = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, bonificacion.getValor());
			stmt.setString(2, bonificacion.getTipoBonificacion());
			stmt.setInt(3, bonificacion.getIdBonificacion());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para eliminar una bonificación por su ID
	public void eliminarBonificacion(int idBonificacion) {
		String sql = "DELETE FROM BONIFICACION WHERE ID_BONIFICACION = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idBonificacion);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
