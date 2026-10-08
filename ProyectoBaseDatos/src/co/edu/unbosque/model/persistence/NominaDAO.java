package co.edu.unbosque.model.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.NominaDTO;
import co.edu.unbosque.model.persistence.util.ConexionDB;

public class NominaDAO {

	private ArrayList<NominaDTO> listaNominas;

	public NominaDAO() {
		listaNominas = new ArrayList<>();
	}

	public void insertarNomina(NominaDTO nomina) {
		String sql = "INSERT INTO NOMINA (SALARIO_BASE, FECHA_PAGO, SALARIO_NETO, CONTRATO_ID_CONTRATO) VALUES (?, ?, ?, ?)";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, nomina.getSalarioBase());
			stmt.setDate(2, nomina.getFechaPago());
			stmt.setLong(3, nomina.getSalarioNeto());
			stmt.setLong(4, nomina.getIdContrato());

			int filas = stmt.executeUpdate();
			if (filas > 0) {
				System.out.println("Nómina insertada correctamente.");
			}

		} catch (SQLException e) {
			System.out.println("Error al insertar nómina: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public NominaDTO findLast(int idContrato) {
		String sql = "SELECT * FROM NOMINA WHERE CONTRATO_ID_CONTRATO = ? AND FECHA_PAGO IS NOT NULL ORDER BY ID_NOMINA DESC LIMIT 1";
		NominaDTO nomina = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idContrato);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				nomina = new NominaDTO();
				nomina.setIdNomina(rs.getInt("ID_NOMINA"));
				nomina.setSalarioBase(rs.getLong("SALARIO_BASE"));
				nomina.setFechaPago(rs.getDate("FECHA_PAGO"));
				nomina.setSalarioNeto(rs.getLong("SALARIO_NETO"));
				nomina.setIdContrato(rs.getInt("CONTRATO_ID_CONTRATO"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nomina;
	}

	public NominaDTO findCurrent(int idContrato) {
		String sql = "SELECT * FROM NOMINA WHERE CONTRATO_ID_CONTRATO = ? AND FECHA_PAGO IS NULL";
		NominaDTO nomina = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idContrato);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				nomina = new NominaDTO();
				nomina.setIdNomina(rs.getInt("ID_NOMINA"));
				nomina.setSalarioBase(rs.getLong("SALARIO_BASE"));
				nomina.setFechaPago(rs.getDate("FECHA_PAGO"));
				nomina.setSalarioNeto(rs.getLong("SALARIO_NETO"));
				nomina.setIdContrato(rs.getInt("CONTRATO_ID_CONTRATO"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nomina;
	}

	// Método para obtener todas las Nóminas
	public String readAll() {
		String sql = "SELECT * FROM NOMINA";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				NominaDTO nomina = new NominaDTO();
				nomina.setIdNomina(rs.getInt("ID_NOMINA"));
				nomina.setSalarioBase(rs.getLong("SALARIO_BASE"));
				nomina.setFechaPago(rs.getDate("FECHA_PAGO"));
				nomina.setSalarioNeto(rs.getLong("SALARIO_NETO"));
				nomina.setIdContrato(rs.getInt("CONTRATO_ID_CONTRATO"));
				resultado.append(nomina.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}

	// Método para obtener una Nómina por su ID
	public NominaDTO findOne(int idNomina) {
		String sql = "SELECT * FROM NOMINA WHERE ID_NOMINA = ?";
		NominaDTO nomina = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idNomina);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				nomina = new NominaDTO();
				nomina.setIdNomina(rs.getInt("ID_NOMINA"));
				nomina.setSalarioBase(rs.getLong("SALARIO_BASE"));
				nomina.setFechaPago(rs.getDate("FECHA_PAGO"));
				nomina.setSalarioNeto(rs.getLong("SALARIO_NETO"));
				nomina.setIdContrato(rs.getInt("CONTRATO_ID_CONTRATO"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nomina;
	}

	public List<NominaDTO> findByIdContrato(int idContrato) {
		String sql = "SELECT * FROM NOMINA WHERE CONTRATO_ID_CONTRATO = ?";
		List<NominaDTO> lista = new ArrayList<>();

		try (Connection conn = ConexionDB.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				NominaDTO nomina = new NominaDTO();
				nomina.setIdNomina(rs.getInt("ID_NOMINA"));
				nomina.setSalarioBase(rs.getLong("SALARIO_BASE"));
				nomina.setFechaPago(rs.getDate("FECHA_PAGO"));
				nomina.setSalarioNeto(rs.getLong("SALARIO_NETO"));
				nomina.setIdContrato(rs.getInt("CONTRATO_ID_CONTRATO"));
				lista.add(nomina);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;

	}

	public NominaDTO findLast() {
		String sql = "SELECT * FROM NOMINA ORDER BY ID_NOMINA DESC LIMIT 1";
		NominaDTO nomina = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				nomina = new NominaDTO();
				nomina.setIdNomina(rs.getInt("ID_NOMINA"));
				nomina.setSalarioBase(rs.getLong("SALARIO_BASE"));
				nomina.setFechaPago(rs.getDate("FECHA_PAGO"));
				nomina.setSalarioNeto(rs.getLong("SALARIO_NETO"));
				nomina.setIdContrato(rs.getInt("CONTRATO_ID_CONTRATO"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nomina;
	}

	public void actualizarNomina(NominaDTO nomina) {
		String sql = "UPDATE NOMINA SET SALARIO_BASE = ?, FECHA_PAGO = ?, SALARIO_NETO = ? WHERE ID_NOMINA = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, nomina.getSalarioBase());
			stmt.setDate(2, nomina.getFechaPago());
			stmt.setLong(3, nomina.getSalarioNeto());
			stmt.setInt(4, nomina.getIdNomina());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para eliminar una Nómina por su ID
	public void eliminarNomina(int idNomina) {
		String sql = "DELETE FROM NOMINA WHERE ID_NOMINA = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idNomina);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
