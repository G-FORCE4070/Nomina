package co.edu.unbosque.model.persistence;

import java.sql.*;
import java.util.ArrayList;

import co.edu.unbosque.model.SeguridadSocialDTO;
import co.edu.unbosque.model.persistence.util.ConexionDB;

public class SeguridadSocialDAO {

	private ArrayList<SeguridadSocialDTO> listaSeguridades;

	public SeguridadSocialDAO() {
		listaSeguridades = new ArrayList<>();
	}

	// Método para insertar una Seguridad Social
	public void insertarSeguridadSocial(SeguridadSocialDTO seguridadSocial) {
		String sql = "INSERT INTO SEGURIDAD_SOCIAL (FECHA_PAGO, PERSONA_ID_PERSONA, ARL_NIT_ARL, EPS_NIT_EPS, "
				+ "PENSION_NIT_PENSION, CAJA_COMPENSACION_NIT_CAJA, APORTE_ARL, APORTE_CAJA, APORTE_EPS, APORTE_PENSION) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setDate(1, seguridadSocial.getFechaPago());
			stmt.setInt(2, seguridadSocial.getIdPersona());
			stmt.setLong(3, seguridadSocial.getArlNitArl());
			stmt.setLong(4, seguridadSocial.getEpsNitEps());
			stmt.setLong(5, seguridadSocial.getPensionNitPension());
			stmt.setLong(6, seguridadSocial.getCajaCompensacionNitCaja());
			stmt.setLong(7, seguridadSocial.getAporteArl());
			stmt.setLong(8, seguridadSocial.getAporteCaja());
			stmt.setLong(9, seguridadSocial.getAporteEps());
			stmt.setLong(10, seguridadSocial.getAportePension());

			int filas = stmt.executeUpdate();
			if (filas > 0) {
				System.out.println("Seguridad Social insertada correctamente.");
			}

		} catch (SQLException e) {
			System.out.println("Error al insertar seguridad social: ");
			e.printStackTrace();
		}
	}

	// Método para obtener todas las Seguridad Social
	public String readAll() {
		String sql = "SELECT * FROM SEGURIDAD_SOCIAL";
		StringBuilder resultado = new StringBuilder();

		try (Connection conn = ConexionDB.conectar();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				SeguridadSocialDTO seguridadSocial = new SeguridadSocialDTO();
				seguridadSocial.setFechaPago(rs.getDate("FECHA_PAGO"));
				seguridadSocial.setIdSeguridadSocial(rs.getLong("SEGURIDAD_SOCIAL_ID"));
				seguridadSocial.setIdPersona(rs.getInt("PERSONA_ID_PERSONA"));
				seguridadSocial.setArlNitArl(rs.getLong("ARL_NIT_ARL"));
				seguridadSocial.setEpsNitEps(rs.getLong("EPS_NIT_EPS"));
				seguridadSocial.setPensionNitPension(rs.getLong("PENSION_NIT_PENSION"));
				seguridadSocial.setCajaCompensacionNitCaja(rs.getLong("CAJA_COMPENSACION_NIT_CAJA"));
				seguridadSocial.setAporteArl(rs.getLong("APORTE_ARL"));
				seguridadSocial.setAporteCaja(rs.getLong("APORTE_CAJA"));
				seguridadSocial.setAporteEps(rs.getLong("APORTE_EPS"));
				seguridadSocial.setAportePension(rs.getLong("APORTE_PENSION"));
				resultado.append(seguridadSocial.toString()).append("\n");
				resultado.append("--------------------------------------------------\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado.toString();
	}

	// Método para obtener una Seguridad Social por su ID
	public SeguridadSocialDTO findOne(long idSeguridadSocial) {
		String sql = "SELECT * FROM SEGURIDAD_SOCIAL WHERE SEGURIDAD_SOCIAL_ID = ?";
		SeguridadSocialDTO seguridadSocial = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, idSeguridadSocial);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				seguridadSocial = new SeguridadSocialDTO();
				seguridadSocial.setFechaPago(rs.getDate("FECHA_PAGO"));
				seguridadSocial.setIdSeguridadSocial(rs.getLong("SEGURIDAD_SOCIAL_ID"));
				seguridadSocial.setIdPersona(rs.getInt("PERSONA_ID_PERSONA"));
				seguridadSocial.setArlNitArl(rs.getLong("ARL_NIT_ARL"));
				seguridadSocial.setEpsNitEps(rs.getLong("EPS_NIT_EPS"));
				seguridadSocial.setPensionNitPension(rs.getLong("PENSION_NIT_PENSION"));
				seguridadSocial.setCajaCompensacionNitCaja(rs.getLong("CAJA_COMPENSACION_NIT_CAJA"));
				seguridadSocial.setAporteArl(rs.getLong("APORTE_ARL"));
				seguridadSocial.setAporteCaja(rs.getLong("APORTE_CAJA"));
				seguridadSocial.setAporteEps(rs.getLong("APORTE_EPS"));
				seguridadSocial.setAportePension(rs.getLong("APORTE_PENSION"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return seguridadSocial;
	}

	// Método para buscar por ID de Persona
	public SeguridadSocialDTO findByIdPersona(int idPersona) {
		String sql = "SELECT * FROM SEGURIDAD_SOCIAL WHERE PERSONA_ID_PERSONA = ?";
		SeguridadSocialDTO seguridadSocial = null;

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idPersona);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				seguridadSocial = new SeguridadSocialDTO();
				seguridadSocial.setFechaPago(rs.getDate("FECHA_PAGO"));
				seguridadSocial.setIdSeguridadSocial(rs.getLong("SEGURIDAD_SOCIAL_ID"));
				seguridadSocial.setIdPersona(rs.getInt("PERSONA_ID_PERSONA"));
				seguridadSocial.setArlNitArl(rs.getLong("ARL_NIT_ARL"));
				seguridadSocial.setEpsNitEps(rs.getLong("EPS_NIT_EPS"));
				seguridadSocial.setPensionNitPension(rs.getLong("PENSION_NIT_PENSION"));
				seguridadSocial.setCajaCompensacionNitCaja(rs.getLong("CAJA_COMPENSACION_NIT_CAJA"));
				seguridadSocial.setAporteArl(rs.getLong("APORTE_ARL"));
				seguridadSocial.setAporteCaja(rs.getLong("APORTE_CAJA"));
				seguridadSocial.setAporteEps(rs.getLong("APORTE_EPS"));
				seguridadSocial.setAportePension(rs.getLong("APORTE_PENSION"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return seguridadSocial;
	}

	// Método para actualizar la fecha de pago (puedes expandirlo si deseas)
	public void actualizarSeguridadSocial(SeguridadSocialDTO seguridadSocial) {
		String sql = "UPDATE SEGURIDAD_SOCIAL SET FECHA_PAGO = ? WHERE SEGURIDAD_SOCIAL_ID = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setDate(1, seguridadSocial.getFechaPago());
			stmt.setLong(2, seguridadSocial.getIdSeguridadSocial());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para eliminar una Seguridad Social por su ID
	public void eliminarSeguridadSocial(long idSeguridadSocial) {
		String sql = "DELETE FROM SEGURIDAD_SOCIAL WHERE SEGURIDAD_SOCIAL_ID = ?";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, idSeguridadSocial);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
