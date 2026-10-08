package co.edu.unbosque.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import co.edu.unbosque.model.ArlDTO;
import co.edu.unbosque.model.BonificacionDTO;
import co.edu.unbosque.model.BonificacionNominaDTO;
import co.edu.unbosque.model.CajaCompensacionDTO;
import co.edu.unbosque.model.ContratoDTO;
import co.edu.unbosque.model.DeduccionDTO;
import co.edu.unbosque.model.DeduccionNominaDTO;
import co.edu.unbosque.model.EpsDTO;
import co.edu.unbosque.model.LiquidacionDefinitivaDTO;
import co.edu.unbosque.model.ModelFacade;
import co.edu.unbosque.model.NominaDTO;
import co.edu.unbosque.model.PensionDTO;
import co.edu.unbosque.model.PersonaDTO;
import co.edu.unbosque.model.PrestacionSocialDTO;
import co.edu.unbosque.model.SeguridadSocialDTO;

public class Controller {

	private static Scanner sc;
	private static ModelFacade mf;

	public Controller() {
		sc = new Scanner(System.in);
		mf = new ModelFacade();
	}

	public void run() {

		while (true) {
			System.out.println("Bienvenidos a la interfaz del programa. Elija una opción:");
			System.out.println("-------------------------------------------------------------");
			System.out.println("1. Gestión de Empleado.");
			System.out.println("2. Gestión de Liquidación de Nomina.");
			System.out.println("3. Gestión de Liquidación de Prestaciones.");
			System.out.println("4. Gestión de Liquidación Definitiva.");
			System.out.println("5. Gestión de Seguridad Social.");
			System.out.println("6. Reportes.");
			System.out.println("7. Salir");
			System.out.print("Elija una opción: ");
			int opcionMenuPrincipal;
			try {
				opcionMenuPrincipal = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				opcionMenuPrincipal = 0;
				sc.next();
			}

			switch (opcionMenuPrincipal) {
			case 1:
				gestionPersona();
				break;
			case 2:
				gestionNomina();
				break;
			case 3:
				gestionPrestaciones();
				break;
			case 4:
				gestionLiquidacionDefinitiva();
				break;
			case 5:
				gestionSeguridadSocial();
				break;
			case 6:
				reportes();
				break;
			case 7:
				System.out.println("Saliendo del sistema.");
				return;
			default:
				System.out.println("Opción no válida, intente nuevamente.");
				break;
			}
		}
	}

	public static void reportes() {
		while (true) {
			int opc;
			while (true) {
				try {
					System.out.println("---- Menú Reportes ----" + "\nIngrese su opción del menú."
							+ "\n1. Reporte Pagos Por Departamento." + "\n2. Reporte Seguridad Social por Persona."
							+ "\n3. Volver al menú principal.");
					opc = sc.nextInt();
					if (opc < 1 || opc > 3) {
						System.out.println("Opción no válida, intente de nuevo.");
						continue;
					}
					sc.nextLine();
					break;
				} catch (InputMismatchException e) {
					System.out.println("Opción no válida, intente de nuevo con valores númericos.");
					sc.next();
				}
			}

			switch (opc) {
			case 1: {
				reporteDepartamento();
				break;
			}
			case 2: {
				reportePersona();
				break;
			}
			case 3: {
				return;
			}
			}
		}
	}

	public static void reporteDepartamento() {
		int idDept;
		while (true) {
			try {
				System.out.println("Escoja uno de los siguientes departamentos" + "\n1. Finanzas." + "\n2. Tecnología."
						+ "\n3. Markting." + "\n4. Ventas.");
				idDept = sc.nextInt();
				if (idDept < 1 || idDept > 5) {
					System.out.println("Opción no válida, intente de nuevo.");
					continue;
				}
				sc.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Opción no válida, intente de nuevo con valores númericos.");
				sc.next();
			}
		}

		String fechaInicioStr;
		while (true) {
			System.out.print("Fecha de inicio (YYYY-MM-DD): ");
			fechaInicioStr = sc.nextLine();
			String[] valores = fechaInicioStr.split("-");
			try {
				for (String s : valores) {
					Integer.parseInt(s);
				}
			} catch (NumberFormatException e) {
				System.out.println("Fecha no válida, intente de nuevo utilizando únicamente fechas númericas");
				continue;
			}

			if (valores[0].length() != 4) {
				System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato YYYY");
			} else if (valores[1].length() != 2) {
				System.out.println("Fecha no válida, intente de nuevo utilizando el mes en formato MM");
			} else if (valores[2].length() != 2) {
				System.out.println("Fecha no válida, intente de nuevo utilizando el día en formato DD");
			} else if (LocalDate.parse(fechaInicioStr).isAfter(LocalDate.now())) {
				System.out.println("Fecha no válida, intente de nuevo utilizando una fecha de inicio ya pasada.");
			} else {
				break;
			}
		}

		String fechaFinalStr;
		while (true) {
			System.out.print("Fecha de corte (YYYY-MM-DD): ");
			fechaFinalStr = sc.nextLine();
			String[] valores = fechaFinalStr.split("-");
			try {
				for (String s : valores) {
					Integer.parseInt(s);
				}
			} catch (NumberFormatException e) {
				System.out.println("Fecha no válida, intente de nuevo utilizando únicamente fechas númericas");
				continue;
			}

			if (valores[0].length() != 4) {
				System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato YYYY");
			} else if (valores[1].length() != 2) {
				System.out.println("Fecha no válida, intente de nuevo utilizando el mes en formato MM");
			} else if (valores[2].length() != 2) {
				System.out.println("Fecha no válida, intente de nuevo utilizando el día en formato DD");
			} else if (LocalDate.parse(fechaFinalStr).isBefore(LocalDate.parse(fechaInicioStr))) {
				System.out.println(
						"Fecha no válida, intente de nuevo utilizando una fecha de finalización posterior a la fecha de inicio.");
			} else {
				break;
			}
		}

		mf.getReporteDAO().generarReporteDepartamento(Date.valueOf(fechaInicioStr), Date.valueOf(fechaFinalStr),
				idDept);
	}

	public static void reportePersona() {
		long cedula;
		while (true) {
			try {
				System.out.print("Cédula: ");
				cedula = sc.nextLong();
				if (mf.getPersonaDAO().findByCedula(cedula) == null) {
					System.out.println("Cédula no valida, no existen empleados con esa cédula.");
					continue;
				}
				if (cedula < 1000000 || cedula > Long.parseLong("9999999999")) {
					System.out.println("Cédula inválida, intente de nuevo usando número entre 7 y 10 dígitos.");
					continue;
				}
				sc.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Cédula no válida, intente de nuevo usando únicamente numeros.");
				sc.next();
			}
		}

		String fechaInicioStr;
		while (true) {
			System.out.print("Fecha de inicio (YYYY-MM-DD): ");
			fechaInicioStr = sc.nextLine();
			String[] valores = fechaInicioStr.split("-");
			try {
				for (String s : valores) {
					Integer.parseInt(s);
				}
			} catch (NumberFormatException e) {
				System.out.println("Fecha no válida, intente de nuevo utilizando únicamente fechas númericas");
				continue;
			}

			if (valores[0].length() != 4) {
				System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato YYYY");
			} else if (valores[1].length() != 2) {
				System.out.println("Fecha no válida, intente de nuevo utilizando el mes en formato MM");
			} else if (valores[2].length() != 2) {
				System.out.println("Fecha no válida, intente de nuevo utilizando el día en formato DD");
			} else if (LocalDate.parse(fechaInicioStr).isAfter(LocalDate.now())) {
				System.out.println("Fecha no válida, intente de nuevo utilizando una fecha de inicio ya pasada.");
			} else {
				break;
			}
		}

		String fechaFinalStr;
		while (true) {
			System.out.print("Fecha de corte (YYYY-MM-DD): ");
			fechaFinalStr = sc.nextLine();
			String[] valores = fechaFinalStr.split("-");
			try {
				for (String s : valores) {
					Integer.parseInt(s);
				}
			} catch (NumberFormatException e) {
				System.out.println("Fecha no válida, intente de nuevo utilizando únicamente fechas númericas");
				continue;
			}

			if (valores[0].length() != 4) {
				System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato YYYY");
			} else if (valores[1].length() != 2) {
				System.out.println("Fecha no válida, intente de nuevo utilizando el mes en formato MM");
			} else if (valores[2].length() != 2) {
				System.out.println("Fecha no válida, intente de nuevo utilizando el día en formato DD");
			} else if (LocalDate.parse(fechaFinalStr).isBefore(LocalDate.parse(fechaInicioStr))) {
				System.out.println(
						"Fecha no válida, intente de nuevo utilizando una fecha de finalización posterior a la fecha de inicio.");
			} else {
				break;
			}
		}

		mf.getReporteDAO().generarReportePersona(Date.valueOf(fechaInicioStr), Date.valueOf(fechaFinalStr), cedula);
	}

	public static void gestionSeguridadSocial() {
		long cedula;
		PersonaDTO persona = null;

		while (true) {
			try {
				System.out.println("Ingrese la cédula del empleado:");
				cedula = sc.nextLong();
				sc.nextLine();
				persona = mf.getPersonaDAO().findByCedula(cedula);
				if (persona == null) {
					System.out.println("Cédula no válida, no esá registrada en el sistema.");
					continue;
				}

				break;
			} catch (InputMismatchException e) {
				System.out.println("Cédula no válida, intente de nuevo con valores numericos.");
				sc.next();
			}
		}

		int opc;
		while (true) {
			try {
				System.out.println("---- Menú Seguridad Social ----" + "\nIngrese una opción del menú:"
						+ "\n1. Calcular Aporte a Salud." + "\n2. Calcular Aporte a Pensión."
						+ "\n3. Calcular Aporte de Caja de Compensación." + "\n4. Calcular aporte a ARL"
						+ "\n5. Volver al menú principal.");
				opc = sc.nextInt();
				if (opc < 1 || opc > 4) {
					System.out.println("Opción no válida, intente de nuevo.");
					continue;
				}
				sc.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Opción no válida, intente de nuevo con valores numericos.");
				sc.next();
			}
		}
		switch (opc) {
		case 1: {
			calcularEPS(persona);
			break;
		}
		case 2: {
			calcularPension(persona);
			break;
		}
		case 3: {
			calcularCajaCompensacion(persona);
			break;
		}
		case 4: {
			calcularArl(persona);
			break;
		}
		case 5: {
			return;
		}
		}
	}

	public static long calcularEPS(PersonaDTO persona) {
		ContratoDTO contrato = mf.getContratoDAO().findActivo(persona.getIdPersona());
		if (contrato == null) {
			System.out.println("No es posible calcular el aporte a salud, esa persona ya fue liquidada.");
			return 0;
		}
		SeguridadSocialDTO seguridad = mf.getSeguridadSocialDAO().findByIdPersona(persona.getIdPersona());

		long aporte = (long) seguridad.getAporteEps();

		System.out.println("Aporte a EPS: $" + aporte + "COP");

		return aporte;
	}

	public static long calcularPension(PersonaDTO persona) {
		ContratoDTO contrato = mf.getContratoDAO().findActivo(persona.getIdPersona());
		if (contrato == null) {
			System.out.println("No es posible calcular el aporte a pensión, esa persona ya fue liquidada.");
			return 0;
		}
		SeguridadSocialDTO seguridad = mf.getSeguridadSocialDAO().findByIdPersona(persona.getIdPersona());

		long aporte = (long) seguridad.getAportePension();

		System.out.println("Aporte a pensión: $" + aporte + "COP");

		return aporte;
	}

	public static long calcularCajaCompensacion(PersonaDTO persona) {
		ContratoDTO contrato = mf.getContratoDAO().findActivo(persona.getIdPersona());
		if (contrato == null) {
			System.out
					.println("No es posible calcular el aporte a caja de compensación, esa persona ya fue liquidada.");
			return 0;
		}
		SeguridadSocialDTO seguridad = mf.getSeguridadSocialDAO().findByIdPersona(persona.getIdPersona());

		long aporte = (long) seguridad.getAporteCaja();

		System.out.println("Aporte a caja de Compensación: $" + aporte + "COP");

		return aporte;
	}

	public static long calcularArl(PersonaDTO persona) {
		ContratoDTO contrato = mf.getContratoDAO().findActivo(persona.getIdPersona());
		if (contrato == null) {
			System.out.println("No es posible calcular el aporte al seguro de riesgos, esa persona ya fue liquidada.");
			return 0;
		}
		SeguridadSocialDTO seguridad = mf.getSeguridadSocialDAO().findByIdPersona(persona.getIdPersona());

		long aporte = (long) seguridad.getAporteArl();

		System.out.println("Aporte a seguro ARL: $" + aporte + "COP");

		return aporte;
	}

	public static void gestionLiquidacionDefinitiva() {
		long cedula;
		PersonaDTO persona = null;

		while (true) {
			try {
				System.out.println("Ingrese la cédula del empleado:");
				cedula = sc.nextLong();
				sc.nextLine();
				persona = mf.getPersonaDAO().findByCedula(cedula);
				if (persona == null) {
					System.out.println("Cédula no válida, no esá registrada en el sistema.");
					continue;
				}

				break;
			} catch (InputMismatchException e) {
				System.out.println("Cédula no válida, intente de nuevo con valores numericos.");
				sc.next();
			}
		}
		while (true) {
			int opc;
			while (true) {
				try {
					System.out
							.println("---- Menú Liquidación Definitiva ----" + "\nIngrese una de las opciones del menú:"
									+ "\n1. Salario Base y Días Trabajados desde la Última Liquidación."
									+ "\n2. Generar Liquidación." + "\n3. Vover al Menú Principal.");
					opc = sc.nextInt();
					sc.nextLine();
					if (opc < 1 || opc > 3) {
						System.out.println("Opción no válida, intente de nuevo.");
						continue;
					}
					break;
				} catch (InputMismatchException e) {
					System.out.println("Opción no válida, intente de nuevo con valores numericos.");
					sc.next();
				}

			}
			switch (opc) {
			case 1: {
				infoUltimaLiq(persona);
				break;
			}
			case 2: {
				generarLiquidacion(persona);
				break;
			}
			case 3: {

				return;
			}
			}
		}

	}

	public static void generarLiquidacion(PersonaDTO persona) {
		ContratoDTO currentContrato = mf.getContratoDAO().findActivo(persona.getIdPersona());
		if (currentContrato == null) {
			System.out.println("Ese empleado ya fue liquidado.");
			return;
		}
		LiquidacionDefinitivaDTO liquidacion = mf.getLiquidacionDefinitivaDAO()
				.findOne(currentContrato.getIdLiquidacionDefinitiva());
		NominaDTO nomina = (mf.getNominaDAO().findLast(currentContrato.getIdContrato()) == null)
				? mf.getNominaDAO().findCurrent(persona.getIdPersona())
				: mf.getNominaDAO().findLast(currentContrato.getIdContrato());
		long salario = nomina.getSalarioBase();
		Date fechaInicio = (nomina.getFechaPago() == null) ? Date.valueOf(LocalDate.now().withDayOfMonth(1))
				: nomina.getFechaPago();
		long diasTrabajados = (fechaInicio == null) ? 0
				: ChronoUnit.DAYS.between(fechaInicio.toLocalDate(), LocalDate.now());
		long salarioPendiente = (long) (salario / 30) * diasTrabajados;
		long primaProporcional = calcularPrima(persona);
		long vacacionesNoUsadas = calculoVacaciones(persona);
		long indemnizacion = 0;
		int opc;
		while (true) {
			try {
				System.out.println(
						"¿Liquidación de contrato por justa causa/mutuo acurdo o renuncia?" + "\n1. Si" + "\n2. No");
				opc = sc.nextInt();
				sc.nextLine();
				if (opc < 1 || opc > 2) {
					System.out.println("Opción no válida, intente de nuevo.");
					continue;
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Opción no válida, intente de nuevo con valores númericos.");
				sc.next();
			}
		}
		long trabajadoContrato = mf.getContratoDAO().totalTrabajado(persona.getIdPersona());
		switch (currentContrato.getTipoContrato()) {
		case "término indefinido": {
			indemnizacion = (opc == 1) ? 0
					: (trabajadoContrato == 0) ? salario
							: salario * (2
									- (mf.getPersonaDAO().totalTrabajado(persona.getIdPersona()) / trabajadoContrato));
			break;
		}
		case "término fijo": {
			indemnizacion = (opc == 1) ? 0
					: (trabajadoContrato == 0) ? salario
							: salario * (2 - (mf.getPersonaDAO().totalTrabajado(persona.getIdPersona())
									/ mf.getContratoDAO().totalTrabajado(persona.getIdPersona())));
			break;
		}
		case "obra o labor determinado": {
			indemnizacion = (opc == 1 || trabajadoContrato == 0) ? 0
					: (trabajadoContrato == 0) ? salario
							: salario * (2 - (mf.getPersonaDAO().totalTrabajado(persona.getIdPersona())
									/ mf.getContratoDAO().totalTrabajado(persona.getIdPersona())));
			break;
		}
		case "aprendizaje": {
			indemnizacion = 0;
			break;
		}
		}
		long total = primaProporcional + vacacionesNoUsadas + indemnizacion;

		liquidacion.setFechaLiquidacion(Date.valueOf(LocalDate.now()));
		liquidacion.setPrimaProporcional(primaProporcional);
		liquidacion.setSalarioPendiente(salarioPendiente);
		liquidacion.setTotalLiquidacion(total);
		liquidacion.setVacacionesPendientes((int) vacacionesNoUsadas);
		liquidacion.setValorIndemnizacion(indemnizacion);

		System.out.println("\n====================================================");
		System.out.printf("%30s\n", "COMPROBANTE DE LIQUIDACIÓN");
		System.out.println("====================================================");
		System.out.printf("Fecha de liquidación:     %s\n", LocalDate.now());
		System.out.printf("Empleado(a):              %s %s\n", persona.getNombre(), persona.getApellido());
		System.out.printf("Tipo de contrato:         %s\n", currentContrato.getTipoContrato());
		System.out.printf("Salario base mensual:     $%,d\n", salario);
		System.out.printf("Días trabajados:          %d días\n", diasTrabajados);
		System.out.printf("Salario prorrateado:      $%,d\n", salarioPendiente);
		System.out.printf("Prima proporcional:       $%,d\n", primaProporcional);
		System.out.printf("Vacaciones pendientes:    $%,d\n", vacacionesNoUsadas);
		System.out.printf("Indemnización:            $%,d\n", indemnizacion);
		System.out.println("----------------------------------------------------");
		System.out.printf("TOTAL LIQUIDACIÓN:        $%,d\n", total);
		System.out.println("====================================================");
		System.out.println("Gracias por tu compromiso y excelente trabajo.");
		mf.getLiquidacionDefinitivaDAO().actualizarLiquidacionDefinitiva(liquidacion);
		currentContrato.setEstado("finalizado");
		currentContrato.setFechaTerminacion(Date.valueOf(LocalDate.now()));
		mf.getContratoDAO().actualizarContrato(currentContrato);
	}

	public static long infoUltimaLiq(PersonaDTO persona) {
		ContratoDTO contrato = mf.getContratoDAO().findLastByPersona(persona.getIdPersona());
		LiquidacionDefinitivaDTO ultimaLiquidacion = mf.getLiquidacionDefinitivaDAO()
				.findOne(contrato.getIdLiquidacionDefinitiva());
		NominaDTO nomina = mf.getNominaDAO().findLast(contrato.getIdContrato());
		long salario = nomina.getSalarioBase();
		Date fechaInicio = ultimaLiquidacion.getFechaLiquidacion();
		long diasTrabajados = (fechaInicio == null) ? 0
				: ChronoUnit.DAYS.between(fechaInicio.toLocalDate(), LocalDate.now());
		System.out.println();
		System.out.println("Empleado CC" + persona.getCedula() + ": " + persona.getNombre() + " "
				+ persona.getApellido() + "." + "\nSalario Base: $" + salario + " COP"
				+ "\nDias desde última liquidación: " + diasTrabajados);
		return diasTrabajados;
	}

	public static void gestionPrestaciones() {
		long cedula;
		PersonaDTO persona = null;

		while (true) {
			try {
				System.out.println("Ingrese la cédula del empleado:");
				cedula = sc.nextLong();
				sc.nextLine();
				persona = mf.getPersonaDAO().findByCedula(cedula);
				if (persona == null) {
					System.out.println("Cédula no válida, no esá registrada en el sistema.");
					continue;
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Cédula no válida, intente de nuevo con valores numericos.");
				sc.next();
			}
		}
		while (true) {
			int opc;
			while (true) {
				try {
					System.out.println("---- Menú Prestaciones ----" + "\nIngrese una de las opciones del menú:"
							+ "\n1. Cálculo de Cesantías." + "\n2. Cálculo de Intereses Sobre Cesantías."
							+ "\n3. Cálculo de Prima de Servicios." + "\n4. Cálculo de Vacaciones."
							+ "\n5. Volver al menú principal.");
					opc = sc.nextInt();
					sc.nextLine();
					if (opc < 1 || opc > 5) {
						System.out.println("Opción no válida, intente de nuevo.");
						continue;
					}
					break;
				} catch (InputMismatchException e) {
					System.out.println("Opción no válida, intente de nuevo con valores numericos.");
					sc.next();
				}

			}

			switch (opc) {
			case 1: {
				calcularCesantias(persona);

				break;
			}
			case 2: {
				interesesCesantías(persona);
				break;
			}
			case 3: {
				primaServicios(persona);
				break;
			}
			case 4: {
				calculoVacaciones(persona);
				break;
			}
			case 5: {
				return;
			}
			default: {
				break;
			}

			}
		}
	}

	public static int calculoVacaciones(PersonaDTO persona) {
		ContratoDTO contrato = mf.getContratoDAO().findActivo(persona.getIdPersona());
		if (contrato == null) {
			System.out.println("No es posible calcular la cantidad, este empleado ya fue liquidado.");
			return 0;
		}
		NominaDTO nomina = mf.getNominaDAO().findLast(contrato.getIdContrato());
		long salario = nomina.getSalarioBase();
		PrestacionSocialDTO prestacionAnterior = mf.getPrestacionDAO().findLastByContrato(contrato.getIdContrato());
		Date fechaInicio = (prestacionAnterior.getFechaLiquidacion() == null) ? contrato.getFechaInicio()
				: prestacionAnterior.getFechaLiquidacion();
		long diasTrabajados = ChronoUnit.DAYS.between(fechaInicio.toLocalDate(), LocalDate.now());
		int vacaciones = Math.round((diasTrabajados * 15) / 360);
		System.out.println("Usted tiene " + vacaciones + " días de vacaciones acumulados gracias a sus "
				+ diasTrabajados + " días trabajados.");
		PrestacionSocialDTO prestacion = mf.getPrestacionDAO().findLastByContrato(contrato.getIdContrato());
		prestacion.setDiasVacaciones(vacaciones);
		mf.getPrestacionDAO().actualizarPrestacionSocial(prestacion);
		return vacaciones;

	}

	public static long calcularPrima(PersonaDTO persona) {
		ContratoDTO contrato = mf.getContratoDAO().findActivo(persona.getIdPersona());
		LocalDate fecha = LocalDate.now();
		if (contrato.getFechaTerminacion() != null && fecha.isAfter(contrato.getFechaTerminacion().toLocalDate())) {
			contrato.setEstado("finalizado");
			mf.getContratoDAO().actualizarContrato(contrato);
			int opcRenovar;
			while (true) {
				try {
					System.out.println(
							"¿Desea usted renovar el contrato para pagar la nomina?" + "\n1. Si." + "\n2. No.");
					opcRenovar = sc.nextInt();
					sc.nextLine();
					if (opcRenovar == 1) {
						ContratoDTO nuevoContrato = new ContratoDTO();
						nuevoContrato.setEstado("activo");
						nuevoContrato.setFechaInicio(Date.valueOf(LocalDate.now()));
						String fechaTerminacion;
						while (true) {
							System.out.print("Fecha de terminación de contrato (YYYY-MM-DD): ");
							fechaTerminacion = sc.nextLine();
							String[] valores = fechaTerminacion.split("-");
							try {
								for (String s : valores) {
									Integer.parseInt(s);
								}
							} catch (NumberFormatException e) {
								System.out.println(
										"Fecha no válida, intente de nuevo utilizando únicamente fechas númericas");
								continue;
							}

							if (valores[0].length() != 4) {
								System.out
										.println("Fecha no válida, intente de nuevo utilizando el año en formato YYYY");
							} else if (valores[1].length() != 2) {
								System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato MM");
							} else if (valores[2].length() != 2) {
								System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato DD");
							}else if(LocalDate.parse(fechaTerminacion).isBefore(LocalDate.now().plusDays(7))) {
								System.out.println("Fecha no válida, los contratos deben durar al menos 1 semana.");
							} else {
								break;
							}
						}
						nuevoContrato.setFechaTerminacion(Date.valueOf(fechaTerminacion));
						nuevoContrato.setIdLiquidacionDefinitiva(contrato.getIdLiquidacionDefinitiva());
						nuevoContrato.setIdPersona(persona.getIdPersona());
						nuevoContrato.setTipoContrato(contrato.getTipoContrato());
						mf.getContratoDAO().insertarContrato(nuevoContrato);
						contrato = mf.getContratoDAO().findActivo(persona.getIdPersona());
					} else {
						System.out.println(
								"No fue posible hacer el calculo de prima, el contrato de este empleado ha finalizado.\nPorfavor dirigase al modulo de liquidación definitiva.");
						return 0;
					}
					break;
				} catch (InputMismatchException e) {
					System.out.println("Opción no válida. intente de nuevo con valores númericos.");
					sc.next();
				}
			}
		}
		NominaDTO nomina = mf.getNominaDAO().findLast(contrato.getIdContrato());
		long salario = nomina.getSalarioBase();
		PrestacionSocialDTO prestacionAnterior = mf.getPrestacionDAO().findLastByContrato(contrato.getIdContrato());
		Date fechaInicio = (prestacionAnterior.getFechaLiquidacion() == null) ? contrato.getFechaInicio()
				: prestacionAnterior.getFechaLiquidacion();
		long mesesTrabajados = ChronoUnit.MONTHS.between(fechaInicio.toLocalDate(), LocalDate.now());
		long prima = (salario / 12) * mesesTrabajados;
		return prima;
	}

	public static void primaServicios(PersonaDTO persona) {
		ContratoDTO contrato = mf.getContratoDAO().findActivo(persona.getIdPersona());
		if (contrato == null) {
			System.out.println("No es posible calcular la prima de servicios, este empleado ya fue liquidado.");
			return;
		}
		LocalDate fecha = LocalDate.now();
		if (contrato.getFechaTerminacion() != null && fecha.isAfter(contrato.getFechaTerminacion().toLocalDate())) {
			contrato.setEstado("finalizado");
			mf.getContratoDAO().actualizarContrato(contrato);
			int opcRenovar;
			while (true) {
				try {
					System.out.println(
							"¿Desea usted renovar el contrato para pagar la nomina?" + "\n1. Si." + "\n2. No.");
					opcRenovar = sc.nextInt();
					sc.nextLine();
					if (opcRenovar == 1) {
						ContratoDTO nuevoContrato = new ContratoDTO();
						nuevoContrato.setEstado("activo");
						nuevoContrato.setFechaInicio(Date.valueOf(LocalDate.now()));
						String fechaTerminacion;
						while (true) {
							System.out.print("Fecha de terminación de contrato (YYYY-MM-DD): ");
							fechaTerminacion = sc.nextLine();
							String[] valores = fechaTerminacion.split("-");
							try {
								for (String s : valores) {
									Integer.parseInt(s);
								}
							} catch (NumberFormatException e) {
								System.out.println(
										"Fecha no válida, intente de nuevo utilizando únicamente fechas númericas");
								continue;
							}

							if (valores[0].length() != 4) {
								System.out
										.println("Fecha no válida, intente de nuevo utilizando el año en formato YYYY");
							} else if (valores[1].length() != 2) {
								System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato MM");
							} else if (valores[2].length() != 2) {
								System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato DD");
							} else {
								break;
							}
						}
						nuevoContrato.setFechaTerminacion(Date.valueOf(fechaTerminacion));
						nuevoContrato.setIdLiquidacionDefinitiva(contrato.getIdLiquidacionDefinitiva());
						nuevoContrato.setIdPersona(persona.getIdPersona());
						nuevoContrato.setTipoContrato(contrato.getTipoContrato());
						mf.getContratoDAO().insertarContrato(nuevoContrato);
						contrato = mf.getContratoDAO().findActivo(persona.getIdPersona());
					} else {
						System.out.println(
								"No fue posible hacer el calculo de prima, el contrato de este empleado ha finalizado.\nPorfavor dirigase al modulo de liquidación definitiva.");
						return;
					}
					break;
				} catch (InputMismatchException e) {
					System.out.println("Opción no válida. intente de nuevo con valores númericos.");
					sc.next();
				}
			}
		}
		NominaDTO nomina = mf.getNominaDAO().findLast(contrato.getIdContrato());
		PrestacionSocialDTO prestacionAnterior = mf.getPrestacionDAO().findLastByContrato(contrato.getIdContrato());
		long salario = nomina.getSalarioBase();
		Date fechaInicio = (prestacionAnterior.getFechaLiquidacion() == null) ? contrato.getFechaInicio()
				: prestacionAnterior.getFechaLiquidacion();
		long mesesTrabajados = ChronoUnit.MONTHS.between(fechaInicio.toLocalDate(), LocalDate.now());
		long prima = calcularPrima(persona);
		if (LocalDate.now().getMonth().equals(LocalDate.parse("2005-06-01").getMonth())
				|| LocalDate.now().getMonth().equals(LocalDate.parse("2005-12-01").getMonth())) {
			System.out.println("Debido a que estamos en " + LocalDate.now().getMonth() + "se le pagará una prima de $"
					+ prima + "COP. Correspondiente a " + mesesTrabajados + "meses trabajados.");
			PrestacionSocialDTO prestacion = mf.getPrestacionDAO().findLastByContrato(contrato.getIdContrato());
			prestacion.setValorPrima(prima);
			prestacion.setFechaLiquidacion(Date.valueOf(LocalDate.now()));
			mf.getPrestacionDAO().actualizarPrestacionSocial(prestacion);

			if (mesesTrabajados >= 12) {
				int opc;
				while (true) {
					try {
						System.out.println("¿Desea usted reclamar su tiempo de vacaciones?" + "\n1. Si" + "\n2. No");
						opc = sc.nextInt();
						sc.nextLine();
						if (opc < 1 || opc > 2) {
							System.out.println("Opción no válida, intente de nuevo.");
							continue;
						}
						break;
					} catch (InputMismatchException e) {
						System.out.println("Opción no válida, intente de nuevo utilizando valores numericos.");
						sc.next();
					}
				}
				int vacas = calculoVacaciones(persona);
				switch (opc) {
				case 1: {
					System.out.println("Usted usará " + vacas + " días de vacaciones.");
					mf.getPrestacionDAO().insertarPrestacionSocial(
							new PrestacionSocialDTO(0, 0, 0, 0, 0, null, contrato.getIdContrato()));
					break;
				}
				case 2: {
					System.out.println("Usted ha acumulado " + vacas + " días de vacaciones.");
					mf.getPrestacionDAO().insertarPrestacionSocial(
							new PrestacionSocialDTO(0, 0, 0, 0, vacas, null, contrato.getIdContrato()));
					break;
				}
				}
			}

		} else {
			System.out.println("Usted tiene $" + prima + "COP de prima pero aún no es final de semestre. \n"
					+ "Recuerde que los pagos de prima se realizan en Junio y Diciembre.");
		}

	}

	public static long interesesCesantías(PersonaDTO persona) {
		ContratoDTO contrato = mf.getContratoDAO().findActivo(persona.getIdPersona());
		if (contrato == null) {
			System.out.println(
					"No es posible calcular los intereses sobre las cesantías, este empleado ya fue liquidado.");
			return 0;
		}
		LocalDate fecha = LocalDate.now();
		if (contrato.getFechaTerminacion() != null && fecha.isAfter(contrato.getFechaTerminacion().toLocalDate())) {
			contrato.setEstado("finalizado");
			mf.getContratoDAO().actualizarContrato(contrato);
			int opcRenovar;
			while (true) {
				try {
					System.out.println(
							"¿Desea usted renovar el contrato para pagar la nomina?" + "\n1. Si." + "\n2. No.");
					opcRenovar = sc.nextInt();
					sc.nextLine();
					if (opcRenovar == 1) {
						ContratoDTO nuevoContrato = new ContratoDTO();
						nuevoContrato.setEstado("activo");
						nuevoContrato.setFechaInicio(Date.valueOf(LocalDate.now()));
						String fechaTerminacion;
						while (true) {
							System.out.print("Fecha de terminación de contrato (YYYY-MM-DD): ");
							fechaTerminacion = sc.nextLine();
							String[] valores = fechaTerminacion.split("-");
							try {
								for (String s : valores) {
									Integer.parseInt(s);
								}
							} catch (NumberFormatException e) {
								System.out.println(
										"Fecha no válida, intente de nuevo utilizando únicamente fechas númericas");
								continue;
							}

							if (valores[0].length() != 4) {
								System.out
										.println("Fecha no válida, intente de nuevo utilizando el año en formato YYYY");
							} else if (valores[1].length() != 2) {
								System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato MM");
							} else if (valores[2].length() != 2) {
								System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato DD");
							} else {
								break;
							}
						}
						nuevoContrato.setFechaTerminacion(Date.valueOf(fechaTerminacion));
						nuevoContrato.setIdLiquidacionDefinitiva(contrato.getIdLiquidacionDefinitiva());
						nuevoContrato.setIdPersona(persona.getIdPersona());
						nuevoContrato.setTipoContrato(contrato.getTipoContrato());
						mf.getContratoDAO().insertarContrato(nuevoContrato);
						contrato = mf.getContratoDAO().findActivo(persona.getIdPersona());
					} else {
						System.out.println(
								"No fue posible calcular los intereses, el contrato de este empleado ha finalizado.\nPorfavor dirigase al modulo de liquidación definitiva");
						return 0;
					}
					break;
				} catch (InputMismatchException e) {
					System.out.println("Opción no válida. intente de nuevo con valores númericos..");
					sc.next();
				}
			}
		}
		NominaDTO nomina = mf.getNominaDAO().findLast(contrato.getIdContrato());
		long salario = nomina.getSalarioBase();
		Date fechaInicio = (nomina.getFechaPago() == null) ? contrato.getFechaInicio() : nomina.getFechaPago();
		long diasTrabajados = ChronoUnit.DAYS.between(fechaInicio.toLocalDate(), LocalDate.now());
		double añosTrabajados = diasTrabajados / 360.0;
		long valorCesantia = Math.round(salario * añosTrabajados);
		long intereses = Math.round(valorCesantia * 0.12);
		System.out.printf(
				"\nEl valor de los intereses sobre las cesantías a los %.2f años para el empleado %s con CC:%d es de $%d COP\n",
				añosTrabajados, persona.getNombre(), persona.getCedula(), intereses);
		PrestacionSocialDTO prestacion = mf.getPrestacionDAO().findLastByContrato(contrato.getIdContrato());
		prestacion.setInteres(intereses);
		mf.getPrestacionDAO().actualizarPrestacionSocial(prestacion);
		return intereses;
	}

	public static long calcularCesantias(PersonaDTO persona) {
		ContratoDTO contrato = mf.getContratoDAO().findActivo(persona.getIdPersona());
		if (contrato == null) {
			System.out.println("No es posible calcular las cesantías, este empleado ya fue liquidado.");
			return 0;
		}
		LocalDate fecha = LocalDate.now();
		if (contrato.getFechaTerminacion() != null && fecha.isAfter(contrato.getFechaTerminacion().toLocalDate())) {
			contrato.setEstado("finalizado");
			mf.getContratoDAO().actualizarContrato(contrato);
			int opcRenovar;
			while (true) {
				try {
					System.out.println(
							"¿Desea usted renovar el contrato para pagar la nomina?" + "\n1. Si." + "\n2. No.");
					opcRenovar = sc.nextInt();
					sc.nextLine();
					if (opcRenovar == 1) {
						ContratoDTO nuevoContrato = new ContratoDTO();
						nuevoContrato.setEstado("activo");
						nuevoContrato.setFechaInicio(Date.valueOf(LocalDate.now()));
						String fechaTerminacion;
						while (true) {
							System.out.print("Fecha de terminación de contrato (YYYY-MM-DD): ");
							fechaTerminacion = sc.nextLine();
							String[] valores = fechaTerminacion.split("-");
							try {
								for (String s : valores) {
									Integer.parseInt(s);
								}
							} catch (NumberFormatException e) {
								System.out.println(
										"Fecha no válida, intente de nuevo utilizando únicamente fechas númericas");
								continue;
							}

							if (valores[0].length() != 4) {
								System.out
										.println("Fecha no válida, intente de nuevo utilizando el año en formato YYYY");
							} else if (valores[1].length() != 2) {
								System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato MM");
							} else if (valores[2].length() != 2) {
								System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato DD");
							} else {
								break;
							}
						}
						nuevoContrato.setFechaTerminacion(Date.valueOf(fechaTerminacion));
						nuevoContrato.setIdLiquidacionDefinitiva(contrato.getIdLiquidacionDefinitiva());
						nuevoContrato.setIdPersona(persona.getIdPersona());
						nuevoContrato.setTipoContrato(contrato.getTipoContrato());
						mf.getContratoDAO().insertarContrato(nuevoContrato);
						contrato = mf.getContratoDAO().findActivo(persona.getIdPersona());
					} else {
						System.out.println(
								"No fue posible calcular la cesantía, el contrato de este empleado ha finalizado.\nPorfavor dirigase al modulo de liquidación definitiva.");
						return 0;
					}
					break;
				} catch (InputMismatchException e) {
					System.out.println("Opción no válida. intente de nuevo con valores númericos.");
					sc.next();
				}
			}
		}
		NominaDTO nomina = (mf.getNominaDAO().findLast(contrato.getIdContrato()) == null)
				? mf.getNominaDAO().findCurrent(contrato.getIdContrato())
				: mf.getNominaDAO().findLast(contrato.getIdContrato());
		long salario = nomina.getSalarioBase();
		Date fechaInicio = (nomina.getFechaPago() == null) ? contrato.getFechaInicio() : nomina.getFechaPago();
		long diasTrabajados = ChronoUnit.DAYS.between(fechaInicio.toLocalDate(), LocalDate.now());
		double añosTrabajados = diasTrabajados / 360.0;
		long valorCesantia = Math.round(salario * añosTrabajados);
		System.out.printf("\nEl valor de las cesantías a los %.2f años para el empleado %s con CC:%d es de $%d COP\n",
				añosTrabajados, persona.getNombre(), persona.getCedula(), valorCesantia);
		PrestacionSocialDTO prestacion = mf.getPrestacionDAO().findLastByContrato(contrato.getIdContrato());
		prestacion.setValorCesantia(valorCesantia);
		mf.getPrestacionDAO().actualizarPrestacionSocial(prestacion);
		return valorCesantia;
	}

	public static void gestionNomina() {
		int opc;
		long cedula;
		PersonaDTO persona;
		while (true) {
			try {
				System.out.println("Ingrese la cédula del empleado:");
				cedula = sc.nextLong();
				sc.nextLine();
				persona = mf.getPersonaDAO().findByCedula(cedula);
				if (persona == null) {
					System.out.println("Cédula no válida, no esá registrada en el sistema.");
					continue;
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Cédula no válida, intente de nuevo con valores numericos.");
				sc.next();
			}
		}

		while (true) {
			System.out.println("\n---- Menú de Liquidación de Nómina ----" + "\n1. Determinar Salario Neto."
					+ "\n2. Agregar Deducciones/Bonificaciones." + "\n3. Generar Comprobante."
					+ "\n4. Volver al menú principal.");
			try {
				opc = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				opc = 0;
				sc.next();
			}
			switch (opc) {
			case 1: {
				calcularSalarioNeto(persona);
				break;
			}
			case 2: {
				agregarDeducBonif(persona);
				break;
			}
			case 3: {
				generarComprobantePago(persona);
				break;
			}
			case 4: {

				return;
			}
			default: {
				System.out.println("Opción no válida, intente nuevamente.");
				break;
			}
			}
		}

	}

	public static void calcularSalarioNeto(PersonaDTO persona) {
		List<ContratoDTO> contratos = mf.getContratoDAO().findAllByPersonaId(persona.getIdPersona());
		ContratoDTO contrato = mf.getContratoDAO().findActivo(persona.getIdPersona());
		if (contrato == null) {
			System.out.println("No es posible calcular el salario neto, este empleado ya fue liquidado.");
			return;
		}
		NominaDTO nomina = mf.getNominaDAO().findCurrent(contrato.getIdContrato());
		long salarioBase = nomina.getSalarioBase();
		Date inicio = mf.getNominaDAO().findLast().getFechaPago();
		int diasTrabajados;
		if (inicio == null) {
			diasTrabajados = (int) ChronoUnit.DAYS
					.between(mf.getContratoDAO().findLast().getFechaInicio().toLocalDate(), LocalDate.now());
		} else {
			diasTrabajados = (int) ChronoUnit.DAYS.between(mf.getNominaDAO().findLast().getFechaPago().toLocalDate(),
					LocalDate.now());
		}
		long bonificaciones = mf.getBonificacionNominaDAO().calcularTotal(nomina.getIdNomina());
		long deducciones = mf.getDeduccionNominaDAO().calcularTotal(nomina.getIdNomina());
		long salarioNeto = ((salarioBase * diasTrabajados) / 30) + bonificaciones - deducciones;
		System.out.println("El salario neto de: " + persona.getNombre() + " " + persona.getApellido()
				+ " identificado con CC: " + persona.getCedula() + " es de: $" + salarioNeto + "COP");
		nomina.setSalarioNeto(salarioNeto);
		mf.getNominaDAO().actualizarNomina(nomina);
	}

	public static void agregarDeducBonif(PersonaDTO persona) {
		int opc;
		while (true) {
			try {
				System.out.println("Ingrese su opción:" + "\n1. Deducción." + "\n2. Bonificación.");
				opc = sc.nextInt();
				sc.nextLine();

				if (opc < 1 || opc > 2) {
					System.out.println("Opción no válida,  intente de nuevo.");
					continue;
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Opción no válida, intente  de nuevo con un valor numerico.");
				sc.next();
			}
		}
		ContratoDTO contrato = mf.getContratoDAO().findActivo(persona.getIdPersona());
		if (contrato == null) {
			System.out.println("No es posible agregar deducciones ni bonificaciones, este empleado ya fue liquidado.");
			return;
		}
		NominaDTO nomina = mf.getNominaDAO().findCurrent(contrato.getIdContrato());
		switch (opc) {
		case 1: {
			int opcDeduc;
			String tipo;
			while (true) {
				try {
					System.out.println("Ingrese el tipo de deducción. " + "\n1. Retención en la fuente."
							+ "\n2. Embargo de salario." + "\n3. Libranza." + "\n4. Seguro no obligatorio.");
					opcDeduc = sc.nextInt();
					sc.nextLine();
					switch (opcDeduc) {
					case 1: {
						tipo = "retencion en el fuente";
						break;
					}
					case 2: {
						tipo = "embargo de salario";
						break;
					}
					case 3: {
						tipo = "libranza";
						break;
					}
					case 4: {
						tipo = "seguro no obligatorio";
						break;
					}
					default: {
						System.out.println("Opción no válida, intente de nuevo.");
						continue;
					}
					}
					break;
				} catch (InputMismatchException e) {
					System.out.println("Opción no válida, intente  de nuevo con un valor numerico.");
					sc.next();
				}
			}

			String razon = "";
			while (true) {
				System.out.println("Escriba la razón de la deducción en menos de 150 caracteres, si lo ve necesario");
				razon = sc.nextLine();
				if (razon.length() > 150) {
					System.out.println("Límite de caracteres excedido, porfavor escriba una razón mas corta.");
					continue;
				}
				break;
			}

			long valor;
			while (true) {
				try {
					System.out.println("Ingrese el valor de la deducción: ");
					valor = sc.nextLong();
					if (valor < 1) {
						System.out.println("Porfavor intente de nuevo con una bonificación mayor a 0.");
						continue;
					}
					sc.nextLine();
					break;
				} catch (InputMismatchException e) {
					System.out.println("Valor no válido, intente de nuevo utilizando valores numericos.");
					sc.next();
				}
			}
			DeduccionDTO deduccion = new DeduccionDTO();
			deduccion.setTipoDeduccion(tipo);
			deduccion.setRazon(razon);
			deduccion.setValor(valor);
			mf.getDeduccionDAO().insertarDeduccion(deduccion);

			deduccion = mf.getDeduccionDAO().findLast();
			mf.getDeduccionNominaDAO()
					.insertarDeduccionNomina(new DeduccionNominaDTO(nomina.getIdNomina(), deduccion.getIdDeduccion()));

			System.out.println("Deduccion agregada exitosamente al empleado " + persona.getNombre() + " "
					+ persona.getApellido() + " con CC" + persona.getCedula());

			break;
		}
		case 2: {
			int opcBonif;
			String tipo;
			while (true) {
				try {
					System.out.println("Ingrese el tipo de bonificación. " + "\n1. Bonificación navideña."
							+ "\n2. Bonificación por productividad." + "\n3. Bonificación por retención de talento."
							+ "\n4. Bonificación por recomendación personal.");
					opcBonif = sc.nextInt();
					sc.nextLine();
					switch (opcBonif) {
					case 1: {
						tipo = "bonificacion navideña";
						break;
					}
					case 2: {
						tipo = "bonificacion por productividad";
						break;
					}
					case 3: {
						tipo = "bonificacion por retencion de talento";
						break;
					}
					case 4: {
						tipo = "bonificacion por recomendacion personal";
						break;
					}
					default: {
						System.out.println("Opción no válida, intente de nuevo.");
						continue;
					}
					}
					break;
				} catch (InputMismatchException e) {
					System.out.println("Opción no válida, intente  de nuevo con un valor numerico.");
					sc.next();
				}
			}

			long valor;
			while (true) {
				try {
					System.out.println("Ingrese el valor de la bonificación: ");
					valor = sc.nextLong();
					sc.nextLine();
					if (valor < 1) {
						System.out.println("Porfavor intente de nuevo con una bonificación mayor a 0.");
						continue;
					}
					break;
				} catch (InputMismatchException e) {
					System.out.println("Valor no válido, intente de nuevo utilizando valores numericos.");
					sc.next();
				}
			}
			BonificacionDTO bonificacion = new BonificacionDTO();
			bonificacion.setTipoBonificacion(tipo);
			bonificacion.setValor(valor);
			mf.getBonificacionDAO().insertarBonificacion(bonificacion);

			bonificacion = mf.getBonificacionDAO().findLast();
			mf.getBonificacionNominaDAO().insertarBonificacionNomina(
					new BonificacionNominaDTO(nomina.getIdNomina(), bonificacion.getIdBonificacion()));

			System.out.println("Bonificación agregada exitosamente al empleado: " + persona.getNombre() + " "
					+ persona.getApellido() + " con CC: " + persona.getCedula());

			break;
		}
		}
	}

	public static void generarComprobantePago(PersonaDTO persona) {
		String nombre = persona.getNombre();
		String apellido = persona.getApellido();
		ContratoDTO contrato = mf.getContratoDAO().findActivo(persona.getIdPersona());
		if (contrato == null) {
			System.out.println("Este empleado no tiene ningún contrato activo, ya fue liquidado.");
			return;
		}
		NominaDTO nomina = (mf.getNominaDAO().findLast(contrato.getIdContrato()) == null)
				? mf.getNominaDAO().findCurrent(contrato.getIdContrato())
				: mf.getNominaDAO().findLast(contrato.getIdContrato());
		Date inicio = (nomina.getFechaPago() == null) ? contrato.getFechaInicio() : nomina.getFechaPago();
		int diasTrabajados;

		diasTrabajados = (int) ChronoUnit.DAYS.between(inicio.toLocalDate(), LocalDate.now());

		LocalDate fecha = LocalDate.now();
		if (contrato.getFechaTerminacion() != null && fecha.isAfter(contrato.getFechaTerminacion().toLocalDate())) {
			contrato.setEstado("finalizado");
			mf.getContratoDAO().actualizarContrato(contrato);
			int opcRenovar;
			while (true) {
				try {
					System.out.println(
							"¿Desea usted renovar el contrato para pagar la nomina?" + "\n1. Si." + "\n2. No.");
					opcRenovar = sc.nextInt();
					sc.nextLine();
					if (opcRenovar == 1) {
						ContratoDTO nuevoContrato = new ContratoDTO();
						nuevoContrato.setEstado("activo");
						nuevoContrato.setFechaInicio(Date.valueOf(LocalDate.now()));
						String fechaTerminacion;
						while (true) {
							System.out.print("Fecha de terminación de contrato (YYYY-MM-DD): ");
							fechaTerminacion = sc.nextLine();
							String[] valores = fechaTerminacion.split("-");
							try {
								for (String s : valores) {
									Integer.parseInt(s);
								}
							} catch (NumberFormatException e) {
								System.out.println(
										"Fecha no válida, intente de nuevo utilizando únicamente fechas númericas");
								continue;
							}

							if (valores[0].length() != 4) {
								System.out
										.println("Fecha no válida, intente de nuevo utilizando el año en formato YYYY");
							} else if (valores[1].length() != 2) {
								System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato MM");
							} else if (valores[2].length() != 2) {
								System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato DD");
							} else {
								break;
							}
						}
						nuevoContrato.setFechaTerminacion(Date.valueOf(fechaTerminacion));
						nuevoContrato.setIdLiquidacionDefinitiva(contrato.getIdLiquidacionDefinitiva());
						nuevoContrato.setIdPersona(persona.getIdPersona());
						nuevoContrato.setTipoContrato(contrato.getTipoContrato());
						mf.getContratoDAO().insertarContrato(nuevoContrato);
						contrato = mf.getContratoDAO().findActivo(persona.getIdPersona());
					} else {
						System.out.println(
								"No fue posible generar un comprobante, el contrato de este empleado ha finalizado.\nPorfavor dirigase al modulo de liquidación definitiva.");
						return;
					}
					break;
				} catch (InputMismatchException e) {
					System.out.println("Opción no válida. intente de nuevo con valores númericos.");
					sc.next();
				}
			}
		}
		long salarioBase = nomina.getSalarioBase();
		long deducciones = mf.getDeduccionNominaDAO().calcularTotal(nomina.getIdNomina());
		long bonificaciones = mf.getBonificacionNominaDAO().calcularTotal(nomina.getIdNomina());
		long salarioNeto = ((salarioBase * diasTrabajados) / 30) + bonificaciones - deducciones;
		System.out.println();
		System.out.println("====================================================");
		System.out.println("                COMPROBANTE DE PAGO");
		System.out.println("====================================================");
		System.out.printf("Fecha de pago:             %s%n", fecha);
		System.out.printf("Empleado(a):               %s %s%n", nombre, apellido);
		System.out.printf("Cargo:                     %s%n", persona.getCargo());
		System.out.printf("Días trabajados:           %d%n", diasTrabajados);
		System.out.printf("Salario base mensual:      $%,d%n", salarioBase);
		System.out.printf("Bonificaciones totales:    $%,d%n", bonificaciones);
		System.out.printf("Deducciones totales:       $%,d%n", deducciones);
		long salarioProrrateado = salarioBase * diasTrabajados / 30;
		System.out.printf("Salario prorrateado (%2d días): $%,d%n", diasTrabajados, salarioProrrateado);
		System.out.printf("SALARIO NETO A PAGAR:      $%,d%n", salarioNeto);
		System.out.println("====================================================");
		System.out.println("Gracias por tu compromiso y excelente trabajo.");
		System.out.println();

		SeguridadSocialDTO seguridad = mf.getSeguridadSocialDAO().findByIdPersona(persona.getIdPersona());
		seguridad.setFechaPago(Date.valueOf(LocalDate.now()));
		mf.getSeguridadSocialDAO().actualizarSeguridadSocial(seguridad);

		NominaDTO nuevaNomina = mf.getNominaDAO().findCurrent(contrato.getIdContrato());
		nuevaNomina.setFechaPago(Date.valueOf(fecha));
		mf.getNominaDAO().actualizarNomina(nuevaNomina);

		nuevaNomina = new NominaDTO();
		nuevaNomina.setSalarioBase(salarioBase);
		nuevaNomina.setIdContrato(contrato.getIdContrato());
		mf.getNominaDAO().insertarNomina(nuevaNomina);
		nuevaNomina = mf.getNominaDAO().findLast();

		DeduccionDTO deduccionEPS = new DeduccionDTO();
		deduccionEPS.setTipoDeduccion("salud");
		deduccionEPS.setValor((long) seguridad.getAporteEps());
		mf.getDeduccionDAO().insertarDeduccion(deduccionEPS);
		deduccionEPS = mf.getDeduccionDAO().findLast();
		mf.getDeduccionNominaDAO().insertarDeduccionNomina(
				new DeduccionNominaDTO(nuevaNomina.getIdNomina(), deduccionEPS.getIdDeduccion()));

		DeduccionDTO deduccionCaja = new DeduccionDTO();
		deduccionCaja.setTipoDeduccion("caja compensación");
		deduccionCaja.setValor(seguridad.getAporteCaja());
		mf.getDeduccionDAO().insertarDeduccion(deduccionEPS);
		deduccionCaja = mf.getDeduccionDAO().findLast();
		mf.getDeduccionNominaDAO().insertarDeduccionNomina(
				new DeduccionNominaDTO(nuevaNomina.getIdNomina(), deduccionCaja.getIdDeduccion()));

		DeduccionDTO deduccionPension = new DeduccionDTO();
		deduccionPension.setTipoDeduccion("pensión");
		deduccionPension.setValor(seguridad.getAportePension());
		mf.getDeduccionDAO().insertarDeduccion(deduccionPension);
		deduccionPension = mf.getDeduccionDAO().findLast();
		mf.getDeduccionNominaDAO().insertarDeduccionNomina(
				new DeduccionNominaDTO(nuevaNomina.getIdNomina(), deduccionPension.getIdDeduccion()));

		DeduccionDTO deduccionArl = new DeduccionDTO();
		deduccionArl.setTipoDeduccion("ARL");
		deduccionArl.setValor(seguridad.getAporteArl());
		mf.getDeduccionDAO().insertarDeduccion(deduccionArl);
		deduccionArl = mf.getDeduccionDAO().findLast();
		mf.getDeduccionNominaDAO().insertarDeduccionNomina(
				new DeduccionNominaDTO(nuevaNomina.getIdNomina(), deduccionArl.getIdDeduccion()));

	}

	public static void gestionPersona() {
		int opcionMenuPersona;

		while (true) {
			System.out.println("\n---- Menú de Empleados ----");
			System.out.println("1. Crear empleado");
			System.out.println("2. Mostrar empleado");
			System.out.println("3. Actualizar empleado");
			System.out.println("4. Eliminar empleado");
			System.out.println("5. Volver al menú principal");
			System.out.print("Elija una opción: ");

			try {
				opcionMenuPersona = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				opcionMenuPersona = 0;
				sc.next();
			}

			switch (opcionMenuPersona) {
			case 1:
				crearPersona();
				break;
			case 2:
				mostrarPersonas();
				break;
			case 3:
				actualizarPersona();
				break;
			case 4:
				eliminarPersona();
				break;
			case 5:
				return;
			default:
				System.out.println("Opción no válida, intente nuevamente.");
				break;
			}
		}
	}

	public static void crearPersona() {
		System.out.println("\nIngrese los datos de la persona:");
		long cedula;
		while (true) {
			try {
				System.out.print("Cédula: ");
				cedula = sc.nextLong();
				if (mf.getPersonaDAO().findByCedula(cedula) != null) {
					System.out.println("Cédula no valida, ya hay un empleado registrado con esta cédula.");
					continue;
				}
				if (cedula < 1000000 || cedula > Long.parseLong("9999999999")) {
					System.out.println("Cédula inválida, intente de nuevo usando número entre 7 y 10 dígitos.");
					sc.nextLine();
					continue;
				}
				sc.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Cédula no válida, intente de nuevo usando únicamente numeros.");
				sc.next();
			}
		}

		String apellido;
		while (true) {
			System.out.print("Apellido: ");
			apellido = sc.nextLine();
			if (apellido.length() > 20) {
				System.out.println(
						"Apellido no válido, ha excedio la cantida de caracteres perimitida (20) intente de nuevo.");
			} else if (!apellido.matches(".*[aeiouAEIOUáéíóúÁÉÍÓÚ].*")) {
				System.out.println("Apellido no válido el apellido debe contener vocales.");
			} else if (apellido.equals(" ")) {
				System.out.println("Apellido no válida, porfavor no ingrese únicamente un carácter vacío.");
			} else if (apellido.matches("[A-Za-zñÑ áéíóúÁÉÍÓÚ]+$")) {
				break;
			} else {
				System.out.println("Apellido no válido, intente de nuevo utilizando únicamente letras.");
			}
		}

		String nombre;
		while (true) {
			System.out.print("Nombre: ");
			nombre = sc.nextLine();
			if (nombre.length() > 20) {
				System.out.println(
						"Nombre no válido, ha excedio la cantida de caracteres perimitida (20) intente de nuevo.");
			} else if (!nombre.matches(".*[aeiouAEIOUáéíóúÁÉÍÓÚ].*")) {
				System.out.println("Nombre no válido el nombre debe contener vocales.");
			} else if (nombre.equals(" ")) {
				System.out.println("Nombre no válida, porfavor no ingrese únicamente un carácter vacío.");
			} else if (nombre.matches("[A-Za-zñÑ áéíóúÁÉÍÓÚ]+$")) {
				break;
			} else {
				System.out.println("Nombre no válido, intente de nuevo utilizando únicamente letras.");
			}
		}

		String fechaNacimientoStr;
		while (true) {
			System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
			fechaNacimientoStr = sc.nextLine();
			String[] valores = fechaNacimientoStr.split("-");
			try {
				for (String s : valores) {
					Integer.parseInt(s);
				}
			} catch (NumberFormatException e) {
				System.out.println("Fecha no válida, intente de nuevo utilizando únicamente fechas númericas");
				continue;
			}

			if (valores[0].length() != 4) {
				System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato YYYY");
			} else if (valores[1].length() != 2) {
				System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato MM");
			} else if (valores[2].length() != 2) {
				System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato DD");
			} else if (Integer.valueOf(valores[0]) < 1900) {
				System.out
						.println("Fecha no válida, intente de nuevo utilizando un año de nacimiento posterior al 1900");
			} else if (Integer.valueOf(valores[0]) > (LocalDate.now().getYear() - 18)) {
				System.out.println("Fecha no válida, intente de nuevo utilizando un año de nacimiento previo al "
						+ (LocalDate.now().getYear() - 18));
			} else {
				break;
			}
		}
		String direccion;
		while (true) {
			System.out.print("Dirección: ");
			direccion = sc.nextLine();
			if (direccion.matches(
					"^(?i)(Calle|Carrera|Avenida|Diagonal|Transversal)\\s\\d{1,3}[A-Z]?\\s*(#|No\\.?|Nº)\\s*\\d{1,3}-\\d{1,3}$")) {
				break;
			}
			System.out.println(
					"Dirección no válida, intente de nuevo utilizando las palabras clave (Calle, Carrera, Diagonal Transversal) numeros, letras y #");
		}

		long contacto;
		while (true) {
			try {
				System.out.print("Contacto: ");
				contacto = sc.nextLong();
				if (contacto < 1000000000 || contacto > 9999999999L) {
					System.out.println("Contacto no válido, intente únicamente con números de 10 dígitos.");
					continue;
				}
				if (mf.getPersonaDAO().findByContacto(contacto) != null) {
					System.out.println("Contacto no válido, ya existe una persona con este numero de contacto.");
					continue;
				}
				sc.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Contacto no válido, intente de nuevo utilizando números únicamente.");
			}
		}

		Map<Integer, List<String>> cargosPorDepartamento = new HashMap<>();
		cargosPorDepartamento.put(1,
				Arrays.asList("Analista de Selección", "Coordinador de Nómina", "Psicólogo Organizacional"));
		cargosPorDepartamento.put(2, Arrays.asList("Contador", "Auditor Financiero", "Analista de Costos"));
		cargosPorDepartamento.put(3,
				Arrays.asList("Desarrollador Backend", "Administrador de Base de Datos", "Soporte Técnico"));
		cargosPorDepartamento.put(4, Arrays.asList("Diseñador Gráfico", "Community Manager", "Especialista SEO"));
		cargosPorDepartamento.put(5,
				Arrays.asList("Ejecutivo Comercial", "Asesor de Ventas", "Coordinador de Cuentas"));

		int departamentoId;
		while (true) {
			try {
				System.out
						.println("Indique el departamento de la persona:\n" + "1. Recursos Humanos\n" + "2. Finanzas\n"
								+ "3. Tecnología\n" + "4. Marketing\n" + "5. Ventas\n" + "Seleccione una opción: ");
				departamentoId = sc.nextInt();

				if (departamentoId < 1 || departamentoId > 5) {
					System.out.println("Departamento inválido, intente nuevamente.");
					continue;
				}
				sc.nextLine(); // Consumir salto de línea
				break;
			} catch (InputMismatchException e) {
				System.out.println("Departamento inválido, intente nuevamente usando un valor numérico.");
				sc.next();
			}
		}

		// Selección del cargo
		List<String> cargosDisponibles = cargosPorDepartamento.get(departamentoId);
		int cargoId;
		String cargoStr;
		NominaDTO nomina = new NominaDTO();

		while (true) {
			try {
				System.out.println("Seleccione el cargo de la persona:");
				for (int i = 0; i < cargosDisponibles.size(); i++) {
					System.out.println((i + 1) + ". " + cargosDisponibles.get(i));
				}
				System.out.print("Seleccione una opción: ");
				cargoId = sc.nextInt();

				if (cargoId < 1 || cargoId > cargosDisponibles.size()) {
					System.out.println("Cargo inválido, intente nuevamente.");
					continue;
				}

				cargoStr = cargosDisponibles.get(cargoId - 1);

				// Asignar salario base según el cargo (opcional, aquí un ejemplo)
				switch (cargoStr) {
				case "Analista de Selección", "Soporte Técnico":
					nomina.setSalarioBase(2600000);
					break;
				case "Coordinador de Nómina", "Administrador de Base de Datos":
					nomina.setSalarioBase(4000000);
					break;
				case "Psicólogo Organizacional", "Diseñador Gráfico", "Community Manager":
					nomina.setSalarioBase(3200000);
					break;
				case "Contador", "Analista de Costos":
					nomina.setSalarioBase(3800000);
					break;
				case "Auditor Financiero":
					nomina.setSalarioBase(4200000);
					break;
				case "Desarrollador Backend":
					nomina.setSalarioBase(4500000);
					break;
				case "Especialista SEO":
					nomina.setSalarioBase(3900000);
					break;
				case "Ejecutivo Comercial":
					nomina.setSalarioBase(3000000);
					break;
				case "Asesor de Ventas":
					nomina.setSalarioBase(2800000);
					break;
				case "Coordinador de Cuentas":
					nomina.setSalarioBase(3500000);
					break;
				default:
					nomina.setSalarioBase(3000000);
					break;
				}

				break;
			} catch (InputMismatchException e) {
				System.out.println("Cargo inválido, intente nuevamente usando un valor numérico.");
				sc.next();
			}
		}

		PersonaDTO persona = new PersonaDTO();
		persona.setCedula(cedula);
		persona.setApellido(apellido);
		persona.setNombre(nombre);
		persona.setFechaNacimiento(Date.valueOf(fechaNacimientoStr));
		persona.setDireccion(direccion);
		persona.setContacto(contacto);
		persona.setDepartamentoId(departamentoId);
		persona.setCargo(cargoStr);

		ContratoDTO contrato = new ContratoDTO();
		contrato.setFechaInicio(Date.valueOf(LocalDate.now()));

		int idxContrato;
		String tipoContrato;
		while (true) {
			try {
				System.out.println("Escoja el tipo de contrato:" + "\n1. Contrato a término indefinido."
						+ "\n2. Contrato a término fijo. " + "\n3. Contrato por obra determinada."
						+ "\n4. Contrato de aprendizaje.");
				idxContrato = sc.nextInt();
				sc.nextLine();
				switch (idxContrato) {
				case 1: {
					tipoContrato = "término indefinido";
					break;
				}
				case 2: {
					tipoContrato = "término fijo";
					break;
				}
				case 3: {
					tipoContrato = "obra o labor determinado";
					break;
				}
				case 4: {
					tipoContrato = "aprendizaje";
					break;
				}
				default: {
					System.out.println("Opción no válida, intente nuevamente.");
					continue;
				}
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Opción no válida, intente de nuevo con un valor númerico.");
				sc.next();
			}
		}
		if (!tipoContrato.equals("término indefinido")) {
			String fechaTerminacion;
			while (true) {
				System.out.print("Fecha de terminación de contrato (YYYY-MM-DD): ");
				fechaTerminacion = sc.nextLine();
				String[] valores = fechaTerminacion.split("-");
				try {
					for (String s : valores) {
						Integer.parseInt(s);
					}
				} catch (NumberFormatException e) {
					System.out.println("Fecha no válida, intente de nuevo utilizando únicamente fechas númericas");
					continue;
				}

				if (valores[0].length() != 4) {
					System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato YYYY");
				} else if (valores[1].length() != 2) {
					System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato MM");
				} else if (valores[2].length() != 2) {
					System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato DD");
				} else if (LocalDate.parse(fechaTerminacion).isBefore(LocalDate.now().plusDays(7))) {
					System.out.println(
							"Fecha no válida, no es posible hacer contratos por menos de 7 días, intente de nuevo");
				} else {
					break;
				}
			}
			contrato.setFechaTerminacion(Date.valueOf(fechaTerminacion));
		}
		mf.getPersonaDAO().insertarPersona(persona);

		PersonaDTO personaInsertada = mf.getPersonaDAO().findByCedula(cedula);

		LiquidacionDefinitivaDTO liquidacionDef = new LiquidacionDefinitivaDTO();
		PrestacionSocialDTO presSocial = new PrestacionSocialDTO();
		SeguridadSocialDTO seguridad = new SeguridadSocialDTO();
		int opcEPS;
		String eps;
		long nitEPS;
		while (true) {
			try {
				System.out.println("Seleccione su EPS:" + "\n1. Famisanar." + "\n2. Sanitas." + "\n3. Compensar."
						+ "\n4. Nueva EPS.");
				opcEPS = sc.nextInt();
				sc.nextLine();
				switch (opcEPS) {
				case 1: {
					eps = "famisanar";
					nitEPS = 8300035647L;
					break;
				}
				case 2: {
					eps = "sanitas";
					nitEPS = 8002514406L;
					break;
				}
				case 3: {
					eps = "compensar";
					nitEPS = 8600669427L;
					break;
				}
				case 4: {
					eps = "nueva eps";
					nitEPS = 9001562642L;
					break;
				}
				default: {
					System.out.println("Opción no válida, intente de nuevo");
					continue;
				}
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Opción no válida, intente de nuevo con un valor númerico.");
				sc.next();
			}
		}
		EpsDTO epsDto = new EpsDTO();
		epsDto.setNitEps(nitEPS);
		epsDto.setNombreEps(eps);
		seguridad.setAporteEps(Math.round(nomina.getSalarioBase() * 0.04));

		int opcCompensacion;
		String compensacion;
		long nitCompensacion;
		while (true) {
			try {
				System.out.println(
						"Seleccione su caja de compesacion:" + "\n1. Cafam." + "\n2. Colsubsidio." + "\n3. Compensar.");
				opcCompensacion = sc.nextInt();
				sc.nextLine();
				switch (opcCompensacion) {
				case 1: {
					compensacion = "cafam";
					nitCompensacion = 8600135703L;
					break;
				}
				case 2: {
					compensacion = "colsubsidio";
					nitCompensacion = 8600073361L;
					break;
				}
				case 3: {
					compensacion = "compensar";
					nitCompensacion = 8600669427L;
					break;
				}

				default: {
					System.out.println("Opción no válida, intente de nuevo");
					continue;
				}
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Opción no válida, intente de nuevo con un valor númerico.");
				sc.next();
			}
		}
		CajaCompensacionDTO compensacionDto = new CajaCompensacionDTO();
		compensacionDto.setNitCaja(nitCompensacion);
		compensacionDto.setNombreCaja(compensacion);
		seguridad.setAporteCaja(Math.round(nomina.getSalarioBase() * 0.04));

		int opcPension;
		String pension;
		long nitPension;
		while (true) {
			try {
				System.out.println("Seleccione su fondo de pensiones:" + "\n1. Colfondos." + "\n2. Colpensiones."
						+ "\n3. Porvenir.");
				opcPension = sc.nextInt();
				sc.nextLine();
				switch (opcPension) {
				case 1: {
					pension = "colfondos";
					nitPension = 8001494962L;
					break;
				}
				case 2: {
					pension = "colpensiones";
					nitPension = 9003360047L;
					break;
				}
				case 3: {
					pension = "porvenir";
					nitPension = 8001443313L;
					break;
				}

				default: {
					System.out.println("Opción no válida, intente de nuevo");
					continue;
				}
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Opción no válida, intente de nuevo con un valor númerico.");
				sc.next();
			}
		}

		PensionDTO pensionDto = new PensionDTO();
		pensionDto.setNitPension(nitPension);
		pensionDto.setNombreFondo(pension);
		seguridad.setAportePension(Math.round(nomina.getSalarioBase() * 0.04));

		int opcARL;
		String arl;
		long nitARL;
		long contactoArl;
		while (true) {
			try {
				System.out.println("Seleccione su seguro contra riesgos:" + "\n1. Sura." + "\n2. Colpatria."
						+ "\n3. Colmena." + "\n4. Seguros Bolivar." + "\n5. No Aplica (Riesgo Bajo)");
				opcARL = sc.nextInt();
				sc.nextLine();
				switch (opcARL) {
				case 1: {
					arl = "sura";
					nitARL = 8909037905L;
					contactoArl = 6014897941L;
					break;
				}
				case 2: {
					arl = "colpatria";
					nitARL = 8600345941L;
					contactoArl = 6014235757L;
					break;
				}
				case 3: {
					arl = "colmena";
					nitARL = 9015287311L;
					contactoArl = 6014010447L;
					break;
				}
				case 4: {
					arl = "seguros bolivar";
					nitARL = 8600025032L;
					contactoArl = 6013122122L;
					break;
				}
				case 5: {
					arl = "N.A";
					nitARL = 0;
					contactoArl = 0;
					break;
				}

				default: {
					System.out.println("Opción no válida, intente de nuevo");
					continue;
				}
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("Opción no válida, intente de nuevo con un valor númerico.");
				sc.next();
			}
		}
		ArlDTO arlDto = new ArlDTO();
		long valorArl = 0;
		if (!arl.equals("N.A")) {
			valorArl = Math.round(nomina.getSalarioBase() * 0.04);
		}
		seguridad.setAporteArl(valorArl);
		arlDto.setNitArl(nitARL);
		arlDto.setNombreArl(arl);
		arlDto.setNumeroContacto(contactoArl);

		seguridad.setArlNitArl(nitARL);
		seguridad.setCajaCompensacionNitCaja(nitCompensacion);
		seguridad.setEpsNitEps(nitEPS);
		seguridad.setIdPersona(personaInsertada.getIdPersona());
		seguridad.setPensionNitPension(nitPension);

		mf.getSeguridadSocialDAO().insertarSeguridadSocial(seguridad);

		contrato.setIdPersona(personaInsertada.getIdPersona());
		mf.getLiquidacionDefinitivaDAO().insertarLiquidacionDefinitiva(liquidacionDef);
		liquidacionDef = mf.getLiquidacionDefinitivaDAO().findLast();
		contrato.setTipoContrato(tipoContrato);
		contrato.setEstado("activo");
		contrato.setIdLiquidacionDefinitiva(liquidacionDef.getIdLiquidacion());
		mf.getContratoDAO().insertarContrato(contrato);
		contrato = mf.getContratoDAO().findLast();
		presSocial.setIdContrato(contrato.getIdContrato());
		mf.getPrestacionDAO().insertarPrestacionSocial(presSocial);

		ContratoDTO contratoActivo = mf.getContratoDAO().findActivo(personaInsertada.getIdPersona());
		if (contratoActivo == null) {
			System.out.println("No se encontró un contrato activo para la persona.");
			return;
		}

		nomina.setIdContrato(contratoActivo.getIdContrato());

		mf.getNominaDAO().insertarNomina(nomina);
		nomina = mf.getNominaDAO().findCurrent(contratoActivo.getIdContrato());
		System.out.println("Persona, contrato y nómina creados correctamente.");

		DeduccionDTO deduccionEPS = new DeduccionDTO();
		deduccionEPS.setTipoDeduccion("salud");
		deduccionEPS.setValor(seguridad.getAporteEps());
		mf.getDeduccionDAO().insertarDeduccion(deduccionEPS);
		deduccionEPS = mf.getDeduccionDAO().findLast();
		mf.getDeduccionNominaDAO()
				.insertarDeduccionNomina(new DeduccionNominaDTO(nomina.getIdNomina(), deduccionEPS.getIdDeduccion()));

		DeduccionDTO deduccionCaja = new DeduccionDTO();
		deduccionCaja.setTipoDeduccion("caja compensación");
		deduccionCaja.setValor(seguridad.getAporteCaja());
		mf.getDeduccionDAO().insertarDeduccion(deduccionEPS);
		deduccionCaja = mf.getDeduccionDAO().findLast();
		mf.getDeduccionNominaDAO()
				.insertarDeduccionNomina(new DeduccionNominaDTO(nomina.getIdNomina(), deduccionCaja.getIdDeduccion()));

		DeduccionDTO deduccionPension = new DeduccionDTO();
		deduccionPension.setTipoDeduccion("pensión");
		deduccionPension.setValor(seguridad.getAportePension());
		mf.getDeduccionDAO().insertarDeduccion(deduccionPension);
		deduccionPension = mf.getDeduccionDAO().findLast();
		mf.getDeduccionNominaDAO().insertarDeduccionNomina(
				new DeduccionNominaDTO(nomina.getIdNomina(), deduccionPension.getIdDeduccion()));

		DeduccionDTO deduccionArl = new DeduccionDTO();
		deduccionArl.setTipoDeduccion("ARL");
		deduccionArl.setValor(seguridad.getAporteArl());
		mf.getDeduccionDAO().insertarDeduccion(deduccionArl);
		deduccionArl = mf.getDeduccionDAO().findLast();
		mf.getDeduccionNominaDAO()
				.insertarDeduccionNomina(new DeduccionNominaDTO(nomina.getIdNomina(), deduccionArl.getIdDeduccion()));

	}

	public static void mostrarPersonas() {
		int opc;
		loop: while (true) {
			try {
				System.out.println("\n---- Menú Mostrar ----" + "\n1. Mostrar todos." + "\n2. Mostrar por nombre."
						+ "\n3. Mostrar por apellido." + "\n4. Mostrar por cédula." + "\n5. Mostrar por cargo."
						+ "\n6. Volver.");
				opc = sc.nextInt();
				sc.nextLine();

				switch (opc) {
				case 1: {
					System.out.println("Lista de todas las personas:");
					System.out.println(mf.getPersonaDAO().readAll());
					break;
				}
				case 2: {
					System.out.println("Ingrese el nombre del empleado:");
					String nombre = sc.nextLine();
					System.out.println("Lista de todas las personas donde el nombre es " + nombre + ":");
					System.out.println(mf.getPersonaDAO().findByNombre(nombre));
					break;
				}
				case 3: {
					System.out.println("Ingrese el apellido del empleado:");
					String apellido = sc.nextLine();
					System.out.println("Lista de todas las personas donde el apellido es " + apellido + ":");
					System.out.println(mf.getPersonaDAO().findByApellido(apellido));
					break;
				}
				case 4: {
					System.out.println("Ingrese la cédula del empleado:");
					long cedula = sc.nextLong();
					if (cedula < 1000000 || cedula > Long.parseLong("9999999999")) {
						System.out.println("Cédula inválida, intente de nuevo usando numero entre 7 y 10 dígitos.");
						sc.next();
						continue;
					}
					sc.nextLine();
					System.out.println("Lista de todas las personas donde la cédula " + cedula + ":");
					System.out.println(mf.getPersonaDAO().findByCedula(cedula));
					break;
				}
				case 5: {
					System.out.println("Ingrese el cargo del empleado:");
					String cargo = sc.nextLine();
					System.out.println("Lista de todas las personas con cargo de " + cargo + ":");
					System.out.println(mf.getPersonaDAO().findByCargo(cargo));
					break;
				}
				case 6: {
					System.out.println("Volviendo al menú de empleados ...");
					gestionPersona();
					return;
				}
				default: {
					System.out.println("Opción no válida, porfavor intente nuevamente");
					continue loop;
				}

				}
				System.out.println();

				break;
			} catch (InputMismatchException e) {
				System.out.println("Opción no válida, intente de nuevo.");
				sc.next();
			}
		}
	}

	public static void actualizarPersona() {
		System.out.print("Ingrese la cédula de la persona a actualizar: ");
		long cedula;
		while (true) {
			try {
				cedula = sc.nextLong();
				if (cedula < 1000000 || cedula > Long.parseLong("9999999999")) {
					System.out.println("Cédula inválida, intente de nuevo usando numero entre 7 y 10 dígitos.");
					sc.next();
					continue;
				}
				sc.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Cédula inválida, intente de nuevo usando valores númericos.");
				sc.next();
			}
		}

		PersonaDTO personaExistente = mf.getPersonaDAO().findByCedula(cedula);
		if (personaExistente == null) {
			System.out.println("Persona no encontrada.");
			return;
		}

		System.out.println("Ingrese los nuevos datos de la persona:");

		String apellido;
		while (true) {
			System.out.print("Apellido: ");
			apellido = sc.nextLine();
			if (apellido.length() > 20) {
				System.out.println(
						"Apellido no válido, ha excedio la cantida de caracteres perimitida (20) intente de nuevo.");
			} else if (!apellido.matches(".*[aeiouAEIOUáéíóúÁÉÍÓÚ].*")) {
				System.out.println("Apellido no válido el apellido debe contener vocales.");
			} else if (apellido.equals(" ")) {
				System.out.println("Apellido no válida, porfavor no ingrese únicamente un carácter vacío.");
			} else if (apellido.matches("[A-Za-zñÑ áéíóúÁÉÍÓÚ]+$")) {
				break;
			} else {
				System.out.println("Apellido no válido, intente de nuevo utilizando únicamente letras.");
			}
		}

		String nombre;
		while (true) {
			System.out.print("Nombre: ");
			nombre = sc.nextLine();
			if (nombre.length() > 20) {
				System.out.println(
						"Nombre no válido, ha excedio la cantida de caracteres perimitida (20) intente de nuevo.");
			} else if (!nombre.matches(".*[aeiouAEIOUáéíóúÁÉÍÓÚ].*")) {
				System.out.println("Nombre no válido el nombre debe contener vocales.");
			} else if (nombre.equals(" ")) {
				System.out.println("Nombre no válido, porfavor no ingrese únicamente un carácter vacío.");
			} else if (nombre.matches("[A-Za-zñÑ áéíóúÁÉÍÓÚ]+$")) {
				break;
			} else {
				System.out.println("Nombre no válido, intente de nuevo utilizando únicamente letras.");
			}
		}

		String fechaNacimientoStr;
		while (true) {
			System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
			fechaNacimientoStr = sc.nextLine();
			String[] valores = fechaNacimientoStr.split("-");
			try {
				for (String s : valores) {
					Integer.parseInt(s);
				}
			} catch (NumberFormatException e) {
				System.out.println("Fecha no válida, intente de nuevo utilizando únicamente fechas númericas");
				continue;
			}

			if (valores[0].length() != 4) {
				System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato YYYY");
			} else if (valores[1].length() != 2) {
				System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato MM");
			} else if (valores[2].length() != 2) {
				System.out.println("Fecha no válida, intente de nuevo utilizando el año en formato DD");
			} else if (Integer.valueOf(valores[0]) < 1900) {
				System.out
						.println("Fecha no válida, intente de nuevo utilizando un año de nacimiento posterior al 1900");
			} else if (Integer.valueOf(valores[0]) > (LocalDate.now().getYear() - 18)) {
				System.out.println("Fecha no válida, intente de nuevo utilizando un año de nacimiento previo al "
						+ (LocalDate.now().getYear() - 18));
			} else {
				break;
			}
		}
		String direccion;
		while (true) {
			System.out.print("Dirección: ");
			direccion = sc.nextLine();
			if (direccion.matches(
					"^(?i)(Calle|Carrera|Avenida|Diagonal|Transversal)\\s\\d{1,3}[A-Z]?\\s*(#|No\\.?|Nº)\\s*\\d{1,3}-\\d{1,3}$")) {
				break;
			}
			System.out.println(
					"Dirección no válida, intente de nuevo utilizando las palabras clave (Calle, Carrera, Diagonal Transversal) numeros, letras y #");
		}

		long contacto;
		while (true) {
			try {
				System.out.print("Contacto: ");
				contacto = sc.nextLong();
				if (contacto < 1000000000 || contacto > 9999999999L) {
					System.out.println("Contacto no válido, intente únicamente con números de 10 dígitos.");
					continue;
				}
				sc.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Contacto no válido, intente de nuevo utilizando números únicamente.");
				sc.next();
			}
		}

		Map<Integer, List<String>> cargosPorDepartamento = new HashMap<>();
		cargosPorDepartamento.put(1,
				Arrays.asList("Analista de Selección", "Coordinador de Nómina", "Psicólogo Organizacional"));
		cargosPorDepartamento.put(2, Arrays.asList("Contador", "Auditor Financiero", "Analista de Costos"));
		cargosPorDepartamento.put(3,
				Arrays.asList("Desarrollador Backend", "Administrador de Base de Datos", "Soporte Técnico"));
		cargosPorDepartamento.put(4, Arrays.asList("Diseñador Gráfico", "Community Manager", "Especialista SEO"));
		cargosPorDepartamento.put(5,
				Arrays.asList("Ejecutivo Comercial", "Asesor de Ventas", "Coordinador de Cuentas"));

		int departamentoId;
		while (true) {
			try {
				System.out
						.println("Indique el departamento de la persona:\n" + "1. Recursos Humanos\n" + "2. Finanzas\n"
								+ "3. Tecnología\n" + "4. Marketing\n" + "5. Ventas\n" + "Seleccione una opción: ");
				departamentoId = sc.nextInt();

				if (departamentoId < 1 || departamentoId > 5) {
					System.out.println("Departamento inválido, intente nuevamente.");
					continue;
				}
				sc.nextLine(); // Consumir salto de línea
				break;
			} catch (InputMismatchException e) {
				System.out.println("Departamento inválido, intente nuevamente usando un valor numérico.");
				sc.next();
			}
		}

		// Selección del cargo
		List<String> cargosDisponibles = cargosPorDepartamento.get(departamentoId);
		int cargoId;
		String cargoStr;
		NominaDTO nomina = new NominaDTO();

		while (true) {
			try {
				System.out.println("Seleccione el cargo de la persona:");
				for (int i = 0; i < cargosDisponibles.size(); i++) {
					System.out.println((i + 1) + ". " + cargosDisponibles.get(i));
				}
				System.out.print("Seleccione una opción: ");
				cargoId = sc.nextInt();

				if (cargoId < 1 || cargoId > cargosDisponibles.size()) {
					System.out.println("Cargo inválido, intente nuevamente.");
					continue;
				}

				cargoStr = cargosDisponibles.get(cargoId - 1);

				// Asignar salario base según el cargo (opcional, aquí un ejemplo)
				switch (cargoStr) {
				case "Analista de Selección", "Soporte Técnico":
					nomina.setSalarioBase(2600000);
					break;
				case "Coordinador de Nómina", "Administrador de Base de Datos":
					nomina.setSalarioBase(4000000);
					break;
				case "Psicólogo Organizacional", "Diseñador Gráfico", "Community Manager":
					nomina.setSalarioBase(3200000);
					break;
				case "Contador", "Analista de Costos":
					nomina.setSalarioBase(3800000);
					break;
				case "Auditor Financiero":
					nomina.setSalarioBase(4200000);
					break;
				case "Desarrollador Backend":
					nomina.setSalarioBase(4500000);
					break;
				case "Especialista SEO":
					nomina.setSalarioBase(3900000);
					break;
				case "Ejecutivo Comercial":
					nomina.setSalarioBase(3000000);
					break;
				case "Asesor de Ventas":
					nomina.setSalarioBase(2800000);
					break;
				case "Coordinador de Cuentas":
					nomina.setSalarioBase(3500000);
					break;
				default:
					nomina.setSalarioBase(3000000);
					break;
				}

				break;
			} catch (InputMismatchException e) {
				System.out.println("Cargo inválido, intente nuevamente usando un valor numérico.");
				sc.next();
			}
		}
		personaExistente.setApellido(apellido);
		personaExistente.setNombre(nombre);
		personaExistente.setFechaNacimiento(Date.valueOf(fechaNacimientoStr));
		personaExistente.setDireccion(direccion);
		personaExistente.setContacto(contacto);
		personaExistente.setDepartamentoId(departamentoId);
		personaExistente.setCargo(cargoStr);

		mf.getPersonaDAO().actualizarPersona(personaExistente);
	}

	public static void eliminarPersona() {
		System.out.print("Ingrese la cédula de la persona a eliminar: ");
		long cedula;
		while (true) {
			try {
				cedula = sc.nextLong();
				if (cedula < 1000000 || cedula > Long.parseLong("9999999999")) {
					System.out.println("Cédula inválida, intente de nuevo usando número entre 7 y 10 dígitos.");
					sc.nextLine();
					continue;
				}
				if (mf.getPersonaDAO().findByCedula(cedula) == null) {
					System.out.println("Cédula inválida, intente de nuevo con una cédula que esté registrada.");
					sc.nextLine();
					continue;
				}
				sc.nextLine();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Cédula inválida, intente de nuevo usando valores numéricos.");
				sc.next();
			}
		}

		PersonaDTO persona = mf.getPersonaDAO().findByCedula(cedula);
		if (persona == null) {
			System.out.println("No se encontró una persona con esa cédula.");
			return;
		}

		mf.getPersonaDAO().eliminarPersonaByCedula(cedula);
	}
}
