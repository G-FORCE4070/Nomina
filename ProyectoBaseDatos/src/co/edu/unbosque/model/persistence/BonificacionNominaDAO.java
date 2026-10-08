package co.edu.unbosque.model.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.BonificacionNominaDTO;
import co.edu.unbosque.model.persistence.util.ConexionDB;

public class BonificacionNominaDAO {

	private ArrayList<BonificacionNominaDTO> listaBonificacionesNominas;

	public BonificacionNominaDAO() {
		listaBonificacionesNominas = new ArrayList<>();
	}
	
	public long calcularTotal(int idNomina) {
	    String sql = "SELECT NOMINA_ID_NOMINA, SUM(VALOR) AS TOTAL "
	               + "FROM BONIFICACION_NOMINA  "
	               + "JOIN BONIFICACION"
	               + "  ON BONIFICACION_ID_BONIFICACION = ID_BONIFICACION "
	               + "WHERE NOMINA_ID_NOMINA = ? "
	               + "GROUP BY NOMINA_ID_NOMINA";

	    long total = 0;

	    try (Connection conn = ConexionDB.conectar();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        // 1. Fijar el parámetro
	        stmt.setInt(1, idNomina);

	        // 2. Ejecutar la consulta
	        try (ResultSet rs = stmt.executeQuery()) {

	            // 3. Avanzar al primer registro
	            if (rs.next()) {
	                // 4. Leer el valor agregado
	                total = rs.getLong("TOTAL");
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return total;
	}

	// Método para insertar una relación de bonificación y nómina
	public void insertarBonificacionNomina(BonificacionNominaDTO bonificacionNomina) {
		String sql = "INSERT INTO BONIFICACION_NOMINA (NOMINA_ID_NOMINA, BONIFICACION_ID_BONIFICACION) VALUES (?, ?)";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, bonificacionNomina.getIdNomina());
			stmt.setInt(2, bonificacionNomina.getIdBonificacion());

			int filas = stmt.executeUpdate();
			if (filas > 0) {
				System.out.println("Relación Bonificación-Nómina insertada correctamente.");
			}

		} catch (SQLException e) {
			System.out.println("Error al insertar relación Bonificación-Nómina: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public List<BonificacionNominaDTO> findAll() {
		String sql = "SELECT * FROM BONIFICACION_NOMINA";
		List<BonificacionNominaDTO> lista =  new ArrayList<>();
		try (Connection conn = ConexionDB.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				BonificacionNominaDTO bonificacionNomina = new BonificacionNominaDTO();
				bonificacionNomina.setIdNomina(rs.getInt("NOMINA_ID_NOMINA"));
				bonificacionNomina.setIdBonificacion(rs.getInt("BONIFICACION_ID_BONIFICACION"));
				lista.add(bonificacionNomina);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	// Método para obtener todas las relaciones de bonificación y nómina
	public String readAll() {
		String sql = "SELECT * FROM BONIFICACION_NOMINA";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				BonificacionNominaDTO bonificacionNomina = new BonificacionNominaDTO();
				bonificacionNomina.setIdNomina(rs.getInt("NOMINA_ID_NOMINA"));
				bonificacionNomina.setIdBonificacion(rs.getInt("BONIFICACION_ID_BONIFICACION"));
				resultado.append(bonificacionNomina.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}

	// Método para obtener una relación de bonificación y nómina por los IDs
	public BonificacionNominaDTO findOne(int idNomina, int idBonificacion) {
		String sql = "SELECT * FROM BONIFICACION_NOMINA WHERE NOMINA_ID_NOMINA = ? AND BONIFICACION_ID_BONIFICACION = ?";
		BonificacionNominaDTO bonificacionNomina = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idNomina);
			stmt.setInt(2, idBonificacion);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				bonificacionNomina = new BonificacionNominaDTO();
				bonificacionNomina.setIdNomina(rs.getInt("NOMINA_ID_NOMINA"));
				bonificacionNomina.setIdBonificacion(rs.getInt("BONIFICACION_ID_BONIFICACION"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bonificacionNomina;
	}

	// Método para eliminar una relación de bonificación y nómina
	public void eliminarBonificacionNomina(int idNomina, int idBonificacion) {
		String sql = "DELETE FROM BONIFICACION_NOMINA WHERE NOMINA_ID_NOMINA = ? AND BONIFICACION_ID_BONIFICACION = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idNomina);
			stmt.setInt(2, idBonificacion);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
