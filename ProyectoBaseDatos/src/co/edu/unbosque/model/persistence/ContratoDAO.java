package co.edu.unbosque.model.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.ContratoDTO;
import co.edu.unbosque.model.persistence.util.ConexionDB;

public class ContratoDAO {

	private ArrayList<ContratoDTO> listaContratos;

	public ContratoDAO() {
		listaContratos = new ArrayList<>();
	}

	public void insertarContrato(ContratoDTO contrato) {
		String sql = "INSERT INTO CONTRATO (FECHA_INICIO, TIPO_CONTRATO, LIQUIDACION_DEFINITIVA_ID_LIQUIDACION, ESTADO, PERSONA_ID_PERSONA, FECHA_TERMINACION) "
				+ "VALUES (?, ?, ?, ?, ?,?)";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setDate(1, contrato.getFechaInicio());
			stmt.setString(2, contrato.getTipoContrato());
			stmt.setInt(3, contrato.getIdLiquidacionDefinitiva());
			stmt.setString(4, contrato.getEstado());
			stmt.setInt(5, contrato.getIdPersona());
			stmt.setDate(6, contrato.getFechaTerminacion());

			int filas = stmt.executeUpdate();
			if (filas > 0) {
				System.out.println("Contrato insertado correctamente.");
			}

		} catch (SQLException e) {
			System.out.println("Error al insertar contrato: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public ContratoDTO findActivo(int personaId) {
		String sql = "SELECT * FROM CONTRATO WHERE PERSONA_ID_PERSONA = ? AND ESTADO ='activo'";
		ContratoDTO contrato = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, personaId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				contrato = new ContratoDTO();
				contrato.setIdContrato(rs.getInt("ID_CONTRATO"));
				contrato.setFechaInicio(rs.getDate("FECHA_INICIO"));
				contrato.setTipoContrato(rs.getString("TIPO_CONTRATO"));
				contrato.setIdLiquidacionDefinitiva(rs.getInt("LIQUIDACION_DEFINITIVA_ID_LIQUIDACION"));
				contrato.setEstado(rs.getString("ESTADO"));
				contrato.setIdPersona(rs.getInt("PERSONA_ID_PERSONA"));
				contrato.setFechaTerminacion(rs.getDate("FECHA_TERMINACION"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contrato;
	}

	public List<ContratoDTO> findAllByPersonaId(int personaId) {
		String sql = "SELECT * FROM CONTRATO WHERE PERSONA_ID_PERSONA = ?";

		List<ContratoDTO> lista = new ArrayList<>();

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.setInt(1, personaId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ContratoDTO contrato = new ContratoDTO();
				contrato.setIdContrato(rs.getInt("ID_CONTRATO"));
				contrato.setFechaInicio(rs.getDate("FECHA_INICIO"));
				contrato.setTipoContrato(rs.getString("TIPO_CONTRATO"));
				contrato.setIdLiquidacionDefinitiva(rs.getInt("LIQUIDACION_DEFINITIVA_ID_LIQUIDACION"));
				contrato.setEstado(rs.getString("ESTADO"));
				contrato.setIdPersona(rs.getInt("PERSONA_ID_PERSONA"));
				contrato.setFechaTerminacion(rs.getDate("FECHA_TERMINACION"));
				lista.add(contrato);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public String readAll() {
		String sql = "SELECT * FROM CONTRATO";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				ContratoDTO contrato = new ContratoDTO();
				contrato.setIdContrato(rs.getInt("ID_CONTRATO"));
				contrato.setFechaInicio(rs.getDate("FECHA_INICIO"));
				contrato.setTipoContrato(rs.getString("TIPO_CONTRATO"));
				contrato.setIdLiquidacionDefinitiva(rs.getInt("LIQUIDACION_DEFINITIVA_ID_LIQUIDACION"));
				contrato.setEstado(rs.getString("ESTADO"));
				contrato.setIdPersona(rs.getInt("PERSONA_ID_PERSONA"));
				contrato.setFechaTerminacion(rs.getDate("FECHA_TERMINACION"));
				resultado.append(contrato.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}

	public ContratoDTO findOne(int idContrato) {
		String sql = "SELECT * FROM CONTRATO WHERE ID_CONTRATO = ?";
		ContratoDTO contrato = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idContrato);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				contrato = new ContratoDTO();
				contrato.setIdContrato(rs.getInt("ID_CONTRATO"));
				contrato.setFechaInicio(rs.getDate("FECHA_INICIO"));
				contrato.setTipoContrato(rs.getString("TIPO_CONTRATO"));
				contrato.setIdLiquidacionDefinitiva(rs.getInt("LIQUIDACION_DEFINITIVA_ID_LIQUIDACION"));
				contrato.setEstado(rs.getString("ESTADO"));
				contrato.setIdPersona(rs.getInt("PERSONA_ID_PERSONA"));
				contrato.setFechaTerminacion(rs.getDate("FECHA_TERMINACION"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return contrato;
	}
	
	public long totalTrabajado(int idPersona) {
		String sql = "SELECT DATEDIFF(SYSDATE(), FECHA_INICIO)/30 AS TOTAL "
				+ "FROM CONTRATO "
				+ "WHERE PERSONA_ID_PERSONA = ? "
				+ "ORDER BY ID_CONTRATO DESC "
				+ "LIMIT 1;";
		long total = 0;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, idPersona);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				total = rs.getLong("TOTAL");
			}

		} catch (SQLException e) {
			System.out.println("Error al buscar el último contrato: " + e.getMessage());
			e.printStackTrace();
		}
		
		return total;
	}
	
	public ContratoDTO findLastByPersona(int idPersona) {
		String sql = "SELECT * FROM CONTRATO WHERE PERSONA_ID_PERSONA = ? ORDER BY ID_CONTRATO DESC LIMIT 1";
		ContratoDTO contrato = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setInt(1, idPersona);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				contrato = new ContratoDTO();
				contrato.setIdContrato(rs.getInt("ID_CONTRATO"));
				contrato.setFechaInicio(rs.getDate("FECHA_INICIO"));
				contrato.setTipoContrato(rs.getString("TIPO_CONTRATO"));
				contrato.setIdLiquidacionDefinitiva(rs.getInt("LIQUIDACION_DEFINITIVA_ID_LIQUIDACION"));
				contrato.setEstado(rs.getString("ESTADO"));
				contrato.setIdPersona(rs.getInt("PERSONA_ID_PERSONA"));
				contrato.setFechaTerminacion(rs.getDate("FECHA_TERMINACION"));
			}

		} catch (SQLException e) {
			System.out.println("Error al buscar el último contrato: " + e.getMessage());
			e.printStackTrace();
		}

		return contrato;
	}

	public ContratoDTO findLast() {
		String sql = "SELECT * FROM CONTRATO ORDER BY ID_CONTRATO DESC LIMIT 1";
		ContratoDTO contrato = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				contrato = new ContratoDTO();
				contrato.setIdContrato(rs.getInt("ID_CONTRATO"));
				contrato.setFechaInicio(rs.getDate("FECHA_INICIO"));
				contrato.setTipoContrato(rs.getString("TIPO_CONTRATO"));
				contrato.setIdLiquidacionDefinitiva(rs.getInt("LIQUIDACION_DEFINITIVA_ID_LIQUIDACION"));
				contrato.setEstado(rs.getString("ESTADO"));
				contrato.setIdPersona(rs.getInt("PERSONA_ID_PERSONA"));
				contrato.setFechaTerminacion(rs.getDate("FECHA_TERMINACION"));
			}

		} catch (SQLException e) {
			System.out.println("Error al buscar el último contrato: " + e.getMessage());
			e.printStackTrace();
		}

		return contrato;
	}

	public void actualizarContrato(ContratoDTO contrato) {
		String sql = "UPDATE CONTRATO SET FECHA_INICIO = ?, TIPO_CONTRATO = ?, LIQUIDACION_DEFINITIVA_ID_LIQUIDACION = ?, "
				+ "ESTADO = ?, PERSONA_ID_PERSONA = ?, FECHA_TERMINACION = ? WHERE ID_CONTRATO = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setDate(1, contrato.getFechaInicio());
			stmt.setString(2, contrato.getTipoContrato());
			stmt.setInt(3, contrato.getIdLiquidacionDefinitiva());
			stmt.setString(4, contrato.getEstado());
			stmt.setInt(5, contrato.getIdPersona());
			stmt.setDate(6, contrato.getFechaTerminacion()); // <-- Aquí debe ir la fecha de terminación
			stmt.setInt(7, contrato.getIdContrato());        // <-- Aquí el ID para el WHERE

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para eliminar un Contrato por su ID
	public void eliminarContrato(int idContrato) {
		String sql = "DELETE FROM CONTRATO WHERE ID_CONTRATO = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idContrato);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
