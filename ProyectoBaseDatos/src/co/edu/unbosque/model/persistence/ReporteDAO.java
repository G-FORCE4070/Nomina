package co.edu.unbosque.model.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.edu.unbosque.model.persistence.util.ConexionDB;

public class ReporteDAO {

	public ReporteDAO() {
	}

	public void generarReportePersona(Date fechaInicio, Date fechaFinal, long cedula) {
		String sql = """
					SELECT
				    P.CEDULA AS CEDULA,
				    CONCAT(P.NOMBRE, ' ', P.APELLIDO) AS NOMBRE,
				    COALESCE(S.FECHA_PAGO, 'PENDIENTE') AS FECHA_PAGO,
				    EPS.NOMBRE_EPS AS EPS,
				    ARL.NOMBRE_ARL AS ARL,
				    PEN.NOMBRE_FONDO AS PENSION,
				    CAJA.NOMBRE_CAJA AS CAJA,
				    COALESCE(SUM(S.APORTE_EPS), 0) AS APORTES_EPS,
				    COALESCE(SUM(S.APORTE_ARL), 0) AS APORTES_ARL,
				    COALESCE(SUM(S.APORTE_PENSION), 0) AS APORTES_PENSION,
				    COALESCE(SUM(S.APORTE_CAJA), 0) AS APORTES_CAJA,
				    COALESCE(SUM(S.APORTE_EPS), 0) + COALESCE(SUM(S.APORTE_ARL), 0) + COALESCE(SUM(S.APORTE_PENSION), 0) + COALESCE(SUM(S.APORTE_CAJA), 0) AS TOTAL_APORTE
				FROM PERSONA P
				    JOIN SEGURIDAD_SOCIAL S ON P.ID_PERSONA = S.PERSONA_ID_PERSONA
				    JOIN EPS ON S.EPS_NIT_EPS = EPS.NIT_EPS
				    JOIN ARL ON S.ARL_NIT_ARL = ARL.NIT_ARL
				    JOIN PENSION PEN ON S.PENSION_NIT_PENSION = PEN.NIT_PENSION
				    JOIN CAJA_COMPENSACION CAJA ON S.CAJA_COMPENSACION_NIT_CAJA = CAJA.NIT_CAJA
				WHERE S.FECHA_PAGO BETWEEN ? AND ? AND P.CEDULA = ?
				GROUP BY P.CEDULA
					""";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setDate(1, fechaInicio);
			stmt.setDate(2, fechaFinal);
			stmt.setLong(3, cedula);

			ResultSet rs = stmt.executeQuery();

			System.out.println(
					"\n========================================================================= REPORTE POR EMPLEADO =========================================================================");

			System.out.printf("%-15s %-25s %-12s %-10s %-10s %-15s %-10s %-10s %-10s %-10s %-15s  %-10s%n", "CÉDULA",
					"NOMBRE", "FECHA", "EPS", "ARL", "PENSIÓN", "CAJA", "AP. EPS", "AP. ARL", "AP. PENS", "AP. CAJA",
					"TOTAL APORTADO");

			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

			int cont = 0;
			long totalEps = 0;
			long totalPension = 0;
			long totalArl = 0;
			long totalCaja = 0;
			long totalPagado = 0;
			while (rs.next()) {
				String nombre = rs.getString("NOMBRE");
				String fecha = rs.getString("FECHA_PAGO");
				String eps = rs.getString("EPS");
				String arl = rs.getString("ARL");
				String pension = rs.getString("PENSION");
				String caja = rs.getString("CAJA");
				long ap_eps = rs.getLong("APORTES_EPS");
				long ap_arl = rs.getLong("APORTES_ARL");
				long ap_pen = rs.getLong("APORTES_PENSION");
				long ap_caja = rs.getLong("APORTES_CAJA");
				long total = rs.getLong("TOTAL_APORTE");

				totalEps += ap_eps;
				totalArl += ap_arl;
				totalPension += ap_pen;
				totalCaja += ap_caja;
				totalPagado += total;

				System.out.printf("%-15s %-25s %-12s %-10s %-10s %-15s %-10s %-10d %-10d %-10d %-10d %-10d%n",
						cedula, nombre, fecha, eps, arl, pension, caja, ap_eps, ap_arl, ap_pen, ap_caja, total);
				cont++;
			}

			System.out.println("\n================== RESUMEN ======================");
			System.out.printf("Cédula                               : %d%n", cedula);
			System.out.println("N° de pagos                          : " + cont);
			System.out.println("Fecha inicio                         : " + fechaInicio);
			System.out.println("Fecha corte                          : " + fechaFinal);
			System.out.printf("Total aportes salud                  : $%d COP%n", totalEps);
			System.out.printf("Total aportes pension                : $%d COP%n", totalPension);
			System.out.printf("Total aportes arl                    : $%d COP%n", totalArl);
			System.out.printf("Total aportes a caja de compensación : $%d COP%n", totalArl);
			System.out.printf("Total pagado                         : $%d COP%n", totalPagado);
			System.out.println("==================================================");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void generarReporteDepartamento(Date fechaInicio, Date fechaFinal, int idDepartamento) {
		String sql = """
					    SELECT
				        D.NOMBRE_DEPARTAMENTO AS DEPARTAMENTO,
				        P.CEDULA AS CEDULA,
				        CONCAT(P.NOMBRE, ' ', P.APELLIDO) AS NOMBRE,
				        N.SALARIO_BASE AS SALARIO,
				        COALESCE(SUM(BO.VALOR), 0) AS BONIFICACIONES,
				        COALESCE(SUM(DE.VALOR), 0) AS DEDUCCIONES,
				        SUM(N.SALARIO_BASE) AS SALARIO_TOTAL,
				        MIN(C.FECHA_INICIO) AS FECHA_INICIO,
				        MAX(N.FECHA_PAGO) AS FECHA_CORTE,
				        SUM(N.SALARIO_BASE + COALESCE(BO.VALOR, 0) - COALESCE(DE.VALOR, 0)) AS TOTAL_PAGADO
				    FROM NOMINA N
				        JOIN CONTRATO C ON N.CONTRATO_ID_CONTRATO = C.ID_CONTRATO
				        JOIN PERSONA P ON C.PERSONA_ID_PERSONA = P.ID_PERSONA
				        JOIN DEPARTAMENTO D ON P.DEPARTAMENTO_ID_DEPARTAMENTO = D.ID_DEPARTAMENTO
				        LEFT JOIN BONIFICACION_NOMINA BN ON N.ID_NOMINA = BN.NOMINA_ID_NOMINA
				        LEFT JOIN BONIFICACION BO ON BN.BONIFICACION_ID_BONIFICACION = BO.ID_BONIFICACION
				        LEFT JOIN DEDUCCION_NOMINA DN ON N.ID_NOMINA = DN.NOMINA_ID_NOMINA
				        LEFT JOIN DEDUCCION DE ON DN.DEDUCCION_ID_DEDUCCION = DE.ID_DEDUCCION
				    WHERE N.FECHA_PAGO IS NOT NULL AND N.FECHA_PAGO BETWEEN ? AND ? AND D.ID_DEPARTAMENTO = ?
				    GROUP BY D.NOMBRE_DEPARTAMENTO, P.CEDULA, P.NOMBRE, P.APELLIDO, N.SALARIO_BASE
				    ORDER BY D.NOMBRE_DEPARTAMENTO, P.CEDULA
				""";

		try (Connection conn = ConexionDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setDate(1, fechaInicio);
			stmt.setDate(2, fechaFinal);
			stmt.setInt(3, idDepartamento);

			ResultSet rs = stmt.executeQuery();

			int count = 0;
			double totalBonificaciones = 0;
			double totalDeducciones = 0;
			double totalPagado = 0;
			Date inicio;
			Date finalf;

			System.out.println(
					"\n================================================= REPORTE POR DEPARTAMENTO =================================================");

			System.out.printf("%-5s %-25s %-15s %-12s %-15s %-15s %-15s %s%n", "#", "NOMBRE", "CÉDULA", "SALARIO",
					"BONIFICACIONES", "DEDUCCIONES", "TOTAL PAGADO", "DEPARTAMENTO");

			System.out.println(
					"-----------------------------------------------------------------------------------------------------------------------------");

			while (rs.next()) {
				count++;
				String nombre = rs.getString("NOMBRE");
				String cedula = rs.getString("CEDULA");
				double salario = rs.getDouble("SALARIO");
				double bonificaciones = rs.getDouble("BONIFICACIONES");
				double deducciones = rs.getDouble("DEDUCCIONES");
				double totalPersona = rs.getDouble("TOTAL_PAGADO");
				String departamento = rs.getString("DEPARTAMENTO");

				totalBonificaciones += bonificaciones;
				totalDeducciones += deducciones;
				totalPagado += totalPersona;

				System.out.printf("%-5d %-25s %-15s %-12.2f %-15.2f %-15.2f %-15.2f %s%n", count, nombre, cedula,
						salario, bonificaciones, deducciones, totalPersona, departamento);
			}

			System.out.println("\n================== RESUMEN =================");
			System.out.println("N° de empleados      : " + count);
			System.out.println("Fecha inicio         : " + fechaInicio);
			System.out.println("Fecha corte          : " + fechaFinal);
			System.out.printf("Total bonificaciones : $%.2f COP%n", totalBonificaciones);
			System.out.printf("Total deducciones    : $%.2f COP%n", totalDeducciones);
			System.out.printf("Total pagado         : $%.2f COP%n", totalPagado);
			System.out.println("============================================");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
