package co.edu.unbosque.model.persistence;

import java.sql.*;
import java.util.ArrayList;

import co.edu.unbosque.model.LiquidacionDefinitivaDTO;
import co.edu.unbosque.model.LiquidacionDefinitivaDTO;
import co.edu.unbosque.model.persistence.util.ConexionDB;

public class LiquidacionDefinitivaDAO {

	private ArrayList<LiquidacionDefinitivaDTO> listaLiquidaciones;

	public LiquidacionDefinitivaDAO() {
		listaLiquidaciones = new ArrayList<>();
	}

	// Método para insertar una Liquidación Definitiva
	public void insertarLiquidacionDefinitiva(LiquidacionDefinitivaDTO liquidacionDefinitiva) {
		String sql = "INSERT INTO LIQUIDACION_DEFINITIVA (VALOR_INDEMNIZACION, FECHA_LIQUIDACION, VACACIONES_PENDIENTES, "
				+ "PRIMA_PROPORCIONAL, SALARIO_PENDIENTE, TOTAL_LIQUIDACION) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, liquidacionDefinitiva.getValorIndemnizacion());
			stmt.setDate(2, liquidacionDefinitiva.getFechaLiquidacion());
			stmt.setInt(3, liquidacionDefinitiva.getVacacionesPendientes());
			stmt.setLong(4, liquidacionDefinitiva.getPrimaProporcional());
			stmt.setLong(5, liquidacionDefinitiva.getSalarioPendiente());
			stmt.setLong(6, liquidacionDefinitiva.getTotalLiquidacion());

			int filas = stmt.executeUpdate();
			if (filas > 0) {
				System.out.println("Liquidación definitiva insertada correctamente.");
			}

		} catch (SQLException e) {
			System.out.println("Error al insertar liquidación definitiva: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Método para obtener todas las Liquidaciones Definitivas
	public String readAll() {
		String sql = "SELECT * FROM LIQUIDACION_DEFINITIVA";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				LiquidacionDefinitivaDTO liquidacionDefinitiva = new LiquidacionDefinitivaDTO();
				liquidacionDefinitiva.setIdLiquidacion(rs.getInt("ID_LIQUIDACION"));
				liquidacionDefinitiva.setValorIndemnizacion(rs.getLong("VALOR_INDEMNIZACION"));
				liquidacionDefinitiva.setFechaLiquidacion(rs.getDate("FECHA_LIQUIDACION"));
				liquidacionDefinitiva.setVacacionesPendientes(rs.getInt("VACACIONES_PENDIENTES"));
				liquidacionDefinitiva.setPrimaProporcional(rs.getLong("PRIMA_PROPORCIONAL"));
				liquidacionDefinitiva.setSalarioPendiente(rs.getLong("SALARIO_PENDIENTE"));
				liquidacionDefinitiva.setTotalLiquidacion(rs.getLong("TOTAL_LIQUIDACION"));
				resultado.append(liquidacionDefinitiva.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}

	// Método para obtener una Liquidación Definitiva por su ID
	public LiquidacionDefinitivaDTO findOne(int idLiquidacion) {
		String sql = "SELECT * FROM LIQUIDACION_DEFINITIVA WHERE ID_LIQUIDACION = ?";
		LiquidacionDefinitivaDTO liquidacionDefinitiva = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idLiquidacion);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				liquidacionDefinitiva = new LiquidacionDefinitivaDTO();
				liquidacionDefinitiva.setIdLiquidacion(rs.getInt("ID_LIQUIDACION"));
				liquidacionDefinitiva.setValorIndemnizacion(rs.getLong("VALOR_INDEMNIZACION"));
				liquidacionDefinitiva.setFechaLiquidacion(rs.getDate("FECHA_LIQUIDACION"));
				liquidacionDefinitiva.setVacacionesPendientes(rs.getInt("VACACIONES_PENDIENTES"));
				liquidacionDefinitiva.setPrimaProporcional(rs.getLong("PRIMA_PROPORCIONAL"));
				liquidacionDefinitiva.setSalarioPendiente(rs.getLong("SALARIO_PENDIENTE"));
				liquidacionDefinitiva.setTotalLiquidacion(rs.getLong("TOTAL_LIQUIDACION"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return liquidacionDefinitiva;
	}

	// Método para actualizar una Liquidación Definitiva
	public void actualizarLiquidacionDefinitiva(LiquidacionDefinitivaDTO liquidacionDefinitiva) {
		String sql = "UPDATE LIQUIDACION_DEFINITIVA SET VALOR_INDEMNIZACION = ?, FECHA_LIQUIDACION = ?, "
				+ "VACACIONES_PENDIENTES = ?, PRIMA_PROPORCIONAL = ?, SALARIO_PENDIENTE = ?, TOTAL_LIQUIDACION = ? "
				+ "WHERE ID_LIQUIDACION = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, liquidacionDefinitiva.getValorIndemnizacion());
			stmt.setDate(2, liquidacionDefinitiva.getFechaLiquidacion());
			stmt.setInt(3, liquidacionDefinitiva.getVacacionesPendientes());
			stmt.setLong(4, liquidacionDefinitiva.getPrimaProporcional());
			stmt.setLong(5, liquidacionDefinitiva.getSalarioPendiente());
			stmt.setLong(6, liquidacionDefinitiva.getTotalLiquidacion());
			stmt.setInt(7, liquidacionDefinitiva.getIdLiquidacion());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para eliminar una Liquidación Definitiva por su ID
	public void eliminarLiquidacionDefinitiva(int idLiquidacion) {
		String sql = "DELETE FROM LIQUIDACION_DEFINITIVA WHERE ID_LIQUIDACION = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idLiquidacion);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public LiquidacionDefinitivaDTO findLast() {
		String sql = "SELECT * FROM LIQUIDACION_DEFINITIVA ORDER BY ID_LIQUIDACION DESC LIMIT 1";
		LiquidacionDefinitivaDTO liquidacion = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				liquidacion = new LiquidacionDefinitivaDTO();
				liquidacion.setIdLiquidacion(rs.getInt("ID_LIQUIDACION"));
				liquidacion.setValorIndemnizacion(rs.getLong("VALOR_INDEMNIZACION"));
				liquidacion.setFechaLiquidacion(rs.getDate("FECHA_LIQUIDACION"));
				liquidacion.setVacacionesPendientes(rs.getInt("VACACIONES_PENDIENTES"));
				liquidacion.setPrimaProporcional(rs.getLong("PRIMA_PROPORCIONAL"));
				liquidacion.setSalarioPendiente(rs.getLong("SALARIO_PENDIENTE"));
				liquidacion.setTotalLiquidacion(rs.getLong("TOTAL_LIQUIDACION"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return liquidacion;
	}
}
