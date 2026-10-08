package co.edu.unbosque.model.persistence;

import java.sql.*;
import java.util.ArrayList;
import co.edu.unbosque.model.PersonaDTO;
import co.edu.unbosque.model.persistence.util.ConexionDB;

public class PersonaDAO {

	private ArrayList<PersonaDTO> listapersonas;

	public PersonaDAO() {
		listapersonas = new ArrayList<>();
	}

	public void insertarPersona(PersonaDTO per) {
		String sql = "INSERT INTO PERSONA (CEDULA, APELLIDO, NOMBRE, FECHA_NACIMIENTO, DIRECCION, CONTACTO, DEPARTAMENTO_ID_DEPARTAMENTO, CARGO) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, per.getCedula());
			stmt.setString(2, per.getApellido());
			stmt.setString(3, per.getNombre());
			stmt.setDate(4, per.getFechaNacimiento());
			stmt.setString(5, per.getDireccion());
			stmt.setLong(6, per.getContacto());
			stmt.setInt(7, per.getDepartamentoId());
			stmt.setString(8, per.getCargo());

			int filas = stmt.executeUpdate();
			if (filas > 0) {
				System.out.println("Persona insertada correctamente.");
			}

		} catch (SQLException e) {
			System.out.println("Error al insertar persona: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public long totalTrabajado(int idPersona) {
		String sql = "SELECT DATEDIFF(SYSDATE(), FECHA_INICIO) / 30 AS TOTAL " + "FROM PERSONA P LEFT JOIN CONTRATO C "
				+ "ON P.ID_PERSONA = C.PERSONA_ID_PERSONA " + "WHERE P.ID_PERSONA = ? " + "ORDER BY C.FECHA_INICIO ASC "
				+ "LIMIT 1";
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

	public String readAll() {
		String sql = "SELECT * FROM PERSONA";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				PersonaDTO pers = new PersonaDTO();
				pers.setCedula(rs.getLong("CEDULA"));
				pers.setApellido(rs.getString("APELLIDO"));
				pers.setNombre(rs.getString("NOMBRE"));
				pers.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
				pers.setDireccion(rs.getString("DIRECCION"));
				pers.setContacto(rs.getLong("CONTACTO"));
				pers.setDepartamentoId(rs.getInt("DEPARTAMENTO_ID_DEPARTAMENTO"));
				pers.setCargo(rs.getString("CARGO"));
				pers.setIdPersona(rs.getInt("ID_PERSONA"));
				resultado.append(pers.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}

	public String findByNombre(String nombre) {
		String sql = "SELECT * FROM PERSONA WHERE NOMBRE = ?";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, nombre);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				PersonaDTO pers = new PersonaDTO();
				pers.setCedula(rs.getLong("CEDULA"));
				pers.setApellido(rs.getString("APELLIDO"));
				pers.setNombre(rs.getString("NOMBRE"));
				pers.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
				pers.setDireccion(rs.getString("DIRECCION"));
				pers.setContacto(rs.getLong("CONTACTO"));
				pers.setDepartamentoId(rs.getInt("DEPARTAMENTO_ID_DEPARTAMENTO"));
				pers.setCargo(rs.getString("CARGO"));
				pers.setIdPersona(rs.getInt("ID_PERSONA"));
				resultado.append(pers.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}

	public String findByApellido(String apellido) {
		String sql = "SELECT * FROM PERSONA WHERE APELLIDO = ?";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, apellido);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				PersonaDTO pers = new PersonaDTO();
				pers.setCedula(rs.getLong("CEDULA"));
				pers.setApellido(rs.getString("APELLIDO"));
				pers.setNombre(rs.getString("NOMBRE"));
				pers.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
				pers.setDireccion(rs.getString("DIRECCION"));
				pers.setContacto(rs.getLong("CONTACTO"));
				pers.setDepartamentoId(rs.getInt("DEPARTAMENTO_ID_DEPARTAMENTO"));
				pers.setCargo(rs.getString("CARGO"));
				pers.setIdPersona(rs.getInt("ID_PERSONA"));
				resultado.append(pers.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}

	public String findByNombreAndApellido(String nombre, String apellido) {
		String sql = "SELECT * FROM PERSONA WHERE NOMBRE = ? AND APELLIDO = ?";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, nombre);
			stmt.setString(2, apellido);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				PersonaDTO pers = new PersonaDTO();
				pers.setCedula(rs.getLong("CEDULA"));
				pers.setApellido(rs.getString("APELLIDO"));
				pers.setNombre(rs.getString("NOMBRE"));
				pers.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
				pers.setDireccion(rs.getString("DIRECCION"));
				pers.setContacto(rs.getLong("CONTACTO"));
				pers.setDepartamentoId(rs.getInt("DEPARTAMENTO_ID_DEPARTAMENTO"));
				pers.setCargo(rs.getString("CARGO"));
				pers.setIdPersona(rs.getInt("ID_PERSONA"));
				resultado.append(pers.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}

	public PersonaDTO findOne(int idPersona) {
		String sql = "SELECT * FROM PERSONA WHERE ID_PERSONA = ?";
		PersonaDTO pers = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idPersona);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				pers = new PersonaDTO();
				pers.setCedula(rs.getLong("CEDULA"));
				pers.setApellido(rs.getString("APELLIDO"));
				pers.setNombre(rs.getString("NOMBRE"));
				pers.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
				pers.setDireccion(rs.getString("DIRECCION"));
				pers.setContacto(rs.getLong("CONTACTO"));
				pers.setDepartamentoId(rs.getInt("DEPARTAMENTO_ID_DEPARTAMENTO"));
				pers.setCargo(rs.getString("CARGO"));
				pers.setIdPersona(rs.getInt("ID_PERSONA"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pers;
	}

	public PersonaDTO findByCedula(long cedula) {
		String sql = "SELECT * FROM PERSONA WHERE CEDULA = ?";
		PersonaDTO pers = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, cedula);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				pers = new PersonaDTO();
				pers.setCedula(rs.getLong("CEDULA"));
				pers.setApellido(rs.getString("APELLIDO"));
				pers.setNombre(rs.getString("NOMBRE"));
				pers.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
				pers.setDireccion(rs.getString("DIRECCION"));
				pers.setContacto(rs.getLong("CONTACTO"));
				pers.setDepartamentoId(rs.getInt("DEPARTAMENTO_ID_DEPARTAMENTO"));
				pers.setCargo(rs.getString("CARGO"));
				pers.setIdPersona(rs.getInt("ID_PERSONA"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pers;
	}

	public PersonaDTO findByContacto(long contacto) {
		String sql = "SELECT * FROM PERSONA WHERE CONTACTO = ?";
		PersonaDTO pers = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, contacto);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				pers = new PersonaDTO();
				pers.setCedula(rs.getLong("CEDULA"));
				pers.setApellido(rs.getString("APELLIDO"));
				pers.setNombre(rs.getString("NOMBRE"));
				pers.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
				pers.setDireccion(rs.getString("DIRECCION"));
				pers.setContacto(rs.getLong("CONTACTO"));
				pers.setDepartamentoId(rs.getInt("DEPARTAMENTO_ID_DEPARTAMENTO"));
				pers.setCargo(rs.getString("CARGO"));
				pers.setIdPersona(rs.getInt("ID_PERSONA"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pers;
	}

	public String findByCargo(String cargo) {
		String sql = "SELECT * FROM PERSONA WHERE CARGO = ?";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, cargo);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				PersonaDTO pers = new PersonaDTO();
				pers.setCedula(rs.getLong("CEDULA"));
				pers.setApellido(rs.getString("APELLIDO"));
				pers.setNombre(rs.getString("NOMBRE"));
				pers.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO"));
				pers.setDireccion(rs.getString("DIRECCION"));
				pers.setContacto(rs.getLong("CONTACTO"));
				pers.setDepartamentoId(rs.getInt("DEPARTAMENTO_ID_DEPARTAMENTO"));
				pers.setCargo(rs.getString("CARGO"));
				pers.setIdPersona(rs.getInt("ID_PERSONA"));
				resultado.append(pers.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}

	public void actualizarPersona(PersonaDTO per) {
		String sql = "UPDATE PERSONA SET CEDULA = ?, APELLIDO = ?, NOMBRE = ?, FECHA_NACIMIENTO = ?, DIRECCION = ?, CONTACTO = ?, DEPARTAMENTO_ID_DEPARTAMENTO = ?, CARGO = ? WHERE ID_PERSONA = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, per.getCedula());
			stmt.setString(2, per.getApellido());
			stmt.setString(3, per.getNombre());
			stmt.setDate(4, per.getFechaNacimiento());
			stmt.setString(5, per.getDireccion());
			stmt.setLong(6, per.getContacto());
			stmt.setInt(7, per.getDepartamentoId());
			stmt.setString(8, per.getCargo());
			stmt.setInt(9, per.getIdPersona());

			int filas = stmt.executeUpdate();
			if (filas > 0) {
				System.out.println("Persona actualizada correctamente.");
			} else {
				System.out.println("No se encontró una persona con ese ID para actualizar.");
			}

		} catch (SQLException e) {
			System.out.println("Error al actualizar persona: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void eliminarPersona(int idPersona) {
		String sql = "DELETE FROM PERSONA WHERE ID_PERSONA = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, idPersona);
			int filas = stmt.executeUpdate();

			if (filas > 0) {
				System.out.println("Persona eliminada correctamente.");
			} else {
				System.out.println("No se encontró una persona con ese ID.");
			}

		} catch (SQLException e) {
			System.out.println("Error al eliminar persona por ID: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void eliminarPersonaByCedula(long cedula) {
		Connection conn = null;

		try {
			conn = ConexionDB.conectar();

			PreparedStatement eliminarBonificacionesNomina = conn
					.prepareStatement("DELETE FROM BONIFICACION_NOMINA WHERE BONIFICACION_ID_BONIFICACION IN ("
							+ " SELECT B.BONIFICACION_ID_BONIFICACION" + " FROM PERSONA P LEFT JOIN CONTRATO C"
							+ " ON P.ID_PERSONA = C.PERSONA_ID_PERSONA" + " LEFT JOIN NOMINA N"
							+ " ON C.ID_CONTRATO = N.CONTRATO_ID_CONTRATO" + " LEFT JOIN BONIFICACION_NOMINA B"
							+ " ON N.ID_NOMINA = B.NOMINA_ID_NOMINA" + " WHERE P.CEDULA = ?" + ")");
			eliminarBonificacionesNomina.setLong(1, cedula);
			eliminarBonificacionesNomina.executeUpdate();

			PreparedStatement eliminarDeduccionesNomina = conn
					.prepareStatement("DELETE FROM DEDUCCION_NOMINA WHERE DEDUCCION_ID_DEDUCCION IN("
							+ " SELECT D.DEDUCCION_ID_DEDUCCION" + " FROM PERSONA P LEFT JOIN CONTRATO C"
							+ " ON P.ID_PERSONA = C.PERSONA_ID_PERSONA" + " LEFT JOIN NOMINA N"
							+ " ON C.ID_CONTRATO = N.CONTRATO_ID_CONTRATO" + " LEFT JOIN DEDUCCION_NOMINA D"
							+ " ON N.ID_NOMINA = D.NOMINA_ID_NOMINA" + " WHERE P.CEDULA = ?" + ")");
			eliminarDeduccionesNomina.setLong(1, cedula);
			eliminarDeduccionesNomina.executeUpdate();

			// Eliminar NOMINA
			PreparedStatement eliminarNominas = conn
					.prepareStatement("DELETE FROM NOMINA WHERE CONTRATO_ID_CONTRATO IN ("
							+ "  SELECT N.CONTRATO_ID_CONTRATO " + "  FROM PERSONA P LEFT JOIN CONTRATO C "
							+ "  ON P.ID_PERSONA = C.PERSONA_ID_PERSONA " + "  LEFT JOIN NOMINA N "
							+ "  ON C.ID_CONTRATO = N.CONTRATO_ID_CONTRATO " + "  WHERE P.CEDULA = ? " + ")");
			eliminarNominas.setLong(1, cedula);
			eliminarNominas.executeUpdate();

			PreparedStatement eliminarPrestaciones = conn
					.prepareStatement("DELETE FROM PRESTACION_SOCIAL WHERE CONTRATO_ID_CONTRATO IN ("
							+ "  SELECT PS.CONTRATO_ID_CONTRATO " + "  FROM PERSONA P LEFT JOIN CONTRATO C "
							+ "  ON P.ID_PERSONA = C.PERSONA_ID_PERSONA " + "  LEFT JOIN PRESTACION_SOCIAL PS "
							+ "  ON C.ID_CONTRATO = PS.CONTRATO_ID_CONTRATO " + "  WHERE P.CEDULA = ? " + ")");
			eliminarPrestaciones.setLong(1, cedula);
			eliminarPrestaciones.executeUpdate();

			PreparedStatement eliminarContratos = conn.prepareStatement("DELETE FROM CONTRATO WHERE ID_CONTRATO IN ("
					+ "SELECT C.ID_CONTRATO " + "FROM PERSONA P LEFT JOIN CONTRATO C "
					+ "ON P.ID_PERSONA = C.PERSONA_ID_PERSONA " + "WHERE P.CEDULA = ?" + ")");
			eliminarContratos.setLong(1, cedula);
			eliminarContratos.executeUpdate();

			PreparedStatement eliminarSeguridades = conn
					.prepareStatement("DELETE FROM SEGURIDAD_SOCIAL WHERE SEGURIDAD_SOCIAL_ID IN ("
							+ "SELECT S.SEGURIDAD_SOCIAL_ID " + "FROM PERSONA P LEFT JOIN SEGURIDAD_SOCIAL S "
							+ "ON P.ID_PERSONA = S.PERSONA_ID_PERSONA " + "WHERE P.CEDULA = ?" + ")");
			eliminarSeguridades.setLong(1, cedula);
			eliminarSeguridades.executeUpdate();

			PreparedStatement eliminarPersona = conn.prepareStatement("DELETE FROM PERSONA WHERE CEDULA = ?");
			eliminarPersona.setLong(1, cedula);
			int filas = eliminarPersona.executeUpdate();

			if (filas > 0) {
				System.out.println("Persona y todos sus registros asociados fueron eliminados.");
			} else {
				System.out.println("No se pudo eliminar la persona.");
			}

		} catch (SQLException e) {
			System.out.println("Error al eliminar al empleado");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException closeEx) {
				closeEx.printStackTrace();
			}
		}
	}

}
