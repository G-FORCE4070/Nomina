package co.edu.unbosque.model.persistence;

import java.sql.*;
import java.util.ArrayList;

import co.edu.unbosque.model.PrestacionSocialDTO;
import co.edu.unbosque.model.persistence.util.ConexionDB;

public class PrestacionSocialDAO {

	private ArrayList<PrestacionSocialDTO> listaPrestaciones;

	public PrestacionSocialDAO() {
		listaPrestaciones = new ArrayList<>();
	}

	// Método para insertar una Prestación Social
	public void insertarPrestacionSocial(PrestacionSocialDTO prestacionSocial) {
		String sql = "INSERT INTO PRESTACION_SOCIAL (VALOR_CESANTIA, INTERES, VALOR_PRIMA, DIAS_VACACIONES, FECHA_LIQUIDACION, CONTRATO_ID_CONTRATO) "
				+ "VALUES (?, ?, ?, ?, ?,?)";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, prestacionSocial.getValorCesantia());
			stmt.setLong(2, prestacionSocial.getInteres());
			stmt.setLong(3, prestacionSocial.getValorPrima());
			stmt.setInt(4, prestacionSocial.getDiasVacaciones());
			stmt.setDate(5, prestacionSocial.getFechaLiquidacion());
			stmt.setInt(6, prestacionSocial.getIdContrato());

			int filas = stmt.executeUpdate();
			if (filas > 0) {
				System.out.println("Prestación Social insertada correctamente.");
			}

		} catch (SQLException e) {
			System.out.println("Error al insertar prestación social: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Método para obtener todas las Prestaciones Sociales
	public String readAll() {
		String sql = "SELECT * FROM PRESTACION_SOCIAL";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				PrestacionSocialDTO prestacionSocial = new PrestacionSocialDTO();
				prestacionSocial.setIdPrestacion(rs.getInt("ID_PRESTACION"));
				prestacionSocial.setValorCesantia(rs.getLong("VALOR_CESANTIA"));
				prestacionSocial.setInteres(rs.getLong("INTERES"));
				prestacionSocial.setValorPrima(rs.getLong("VALOR_PRIMA"));
				prestacionSocial.setDiasVacaciones(rs.getInt("DIAS_VACACIONES"));
				prestacionSocial.setFechaLiquidacion(rs.getDate("FECHA_LIQUIDACION"));
				prestacionSocial.setIdContrato(rs.getInt("CONTRATO_ID_CONTRATO"));
				resultado.append(prestacionSocial.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}
	
	public PrestacionSocialDTO findPreviousByContrato(int idContrato) {
		String sql = "SELECT * FROM PRESTACION_SOCIAL WHERE CONTRATO_ID_CONTRATO = ? AND FECHA_LIQUIDACION IS NOT NULL ORDER BY ID_PRESTACION DESC LIMIT 1";
		PrestacionSocialDTO prestacionSocial = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idContrato);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				prestacionSocial = new PrestacionSocialDTO();
				prestacionSocial.setIdPrestacion(rs.getInt("ID_PRESTACION"));
				prestacionSocial.setValorCesantia(rs.getLong("VALOR_CESANTIA"));
				prestacionSocial.setInteres(rs.getLong("INTERES"));
				prestacionSocial.setValorPrima(rs.getLong("VALOR_PRIMA"));
				prestacionSocial.setDiasVacaciones(rs.getInt("DIAS_VACACIONES"));
				prestacionSocial.setFechaLiquidacion(rs.getDate("FECHA_LIQUIDACION"));
				prestacionSocial.setIdContrato(rs.getInt("CONTRATO_ID_CONTRATO"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prestacionSocial;
	}
	
	public PrestacionSocialDTO findLastByContrato(int idContrato) {
		String sql = "SELECT * FROM PRESTACION_SOCIAL WHERE CONTRATO_ID_CONTRATO = ? ORDER BY ID_PRESTACION DESC LIMIT 1";
		PrestacionSocialDTO prestacionSocial = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idContrato);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				prestacionSocial = new PrestacionSocialDTO();
				prestacionSocial.setIdPrestacion(rs.getInt("ID_PRESTACION"));
				prestacionSocial.setValorCesantia(rs.getLong("VALOR_CESANTIA"));
				prestacionSocial.setInteres(rs.getLong("INTERES"));
				prestacionSocial.setValorPrima(rs.getLong("VALOR_PRIMA"));
				prestacionSocial.setDiasVacaciones(rs.getInt("DIAS_VACACIONES"));
				prestacionSocial.setFechaLiquidacion(rs.getDate("FECHA_LIQUIDACION"));
				prestacionSocial.setIdContrato(rs.getInt("CONTRATO_ID_CONTRATO"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prestacionSocial;
	}

	// Método para obtener una Prestación Social por su ID
	public PrestacionSocialDTO findOne(int idPrestacion) {
		String sql = "SELECT * FROM PRESTACION_SOCIAL WHERE ID_PRESTACION = ?";
		PrestacionSocialDTO prestacionSocial = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idPrestacion);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				prestacionSocial = new PrestacionSocialDTO();
				prestacionSocial.setIdPrestacion(rs.getInt("ID_PRESTACION"));
				prestacionSocial.setValorCesantia(rs.getLong("VALOR_CESANTIA"));
				prestacionSocial.setInteres(rs.getLong("INTERES"));
				prestacionSocial.setValorPrima(rs.getLong("VALOR_PRIMA"));
				prestacionSocial.setDiasVacaciones(rs.getInt("DIAS_VACACIONES"));
				prestacionSocial.setFechaLiquidacion(rs.getDate("FECHA_LIQUIDACION"));
				prestacionSocial.setIdContrato(rs.getInt("CONTRATO_ID_CONTRATO"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prestacionSocial;
	}
	
	public PrestacionSocialDTO findLast() {
		String sql = "SELECT * FROM PRESTACION_SOCIAL ORDER BY ID_PRESTACION DESC LIMIT 1";
		PrestacionSocialDTO prestacionSocial = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				prestacionSocial = new PrestacionSocialDTO();
				prestacionSocial.setIdPrestacion(rs.getInt("ID_PRESTACION"));
				prestacionSocial.setValorCesantia(rs.getLong("VALOR_CESANTIA"));
				prestacionSocial.setInteres(rs.getLong("INTERES"));
				prestacionSocial.setValorPrima(rs.getLong("VALOR_PRIMA"));
				prestacionSocial.setDiasVacaciones(rs.getInt("DIAS_VACACIONES"));
				prestacionSocial.setFechaLiquidacion(rs.getDate("FECHA_LIQUIDACION"));
				prestacionSocial.setIdContrato(rs.getInt("CONTRATO_ID_CONTRATO"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prestacionSocial;
	}

	// Método para actualizar una Prestación Social
	public void actualizarPrestacionSocial(PrestacionSocialDTO prestacionSocial) {
		String sql = "UPDATE PRESTACION_SOCIAL SET VALOR_CESANTIA = ?, INTERES = ?, VALOR_PRIMA = ?, "
				+ "DIAS_VACACIONES = ?, FECHA_LIQUIDACION = ? WHERE ID_PRESTACION = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, prestacionSocial.getValorCesantia());
			stmt.setLong(2, prestacionSocial.getInteres());
			stmt.setLong(3, prestacionSocial.getValorPrima());
			stmt.setInt(4, prestacionSocial.getDiasVacaciones());
			stmt.setDate(5, prestacionSocial.getFechaLiquidacion());
			stmt.setInt(6, prestacionSocial.getIdPrestacion());
			

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para eliminar una Prestación Social por su ID
	public void eliminarPrestacionSocial(int idPrestacion) {
		String sql = "DELETE FROM PRESTACION_SOCIAL WHERE ID_PRESTACION = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idPrestacion);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
