package co.edu.unbosque.model;

import java.sql.Date;

public class LiquidacionDefinitivaDTO {

	private int idLiquidacion;
	private long valorIndemnizacion;
	private Date fechaLiquidacion;
	private int vacacionesPendientes;
	private long primaProporcional;
	private long salarioPendiente;
	private long totalLiquidacion;

	public LiquidacionDefinitivaDTO() {
	}

	public LiquidacionDefinitivaDTO(int idLiquidacion, long valorIndemnizacion, Date fechaLiquidacion,
			int vacacionesPendientes, long primaProporcional, long salarioPendiente, long totalLiquidacion) {
		super();
		this.idLiquidacion = idLiquidacion;
		this.valorIndemnizacion = valorIndemnizacion;
		this.fechaLiquidacion = fechaLiquidacion;
		this.vacacionesPendientes = vacacionesPendientes;
		this.primaProporcional = primaProporcional;
		this.salarioPendiente = salarioPendiente;
		this.totalLiquidacion = totalLiquidacion;
	}

	public int getIdLiquidacion() {
		return idLiquidacion;
	}

	public void setIdLiquidacion(int idLiquidacion) {
		this.idLiquidacion = idLiquidacion;
	}

	public long getValorIndemnizacion() {
		return valorIndemnizacion;
	}

	public void setValorIndemnizacion(long valorIndemnizacion) {
		this.valorIndemnizacion = valorIndemnizacion;
	}

	public Date getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	public void setFechaLiquidacion(Date fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	public int getVacacionesPendientes() {
		return vacacionesPendientes;
	}

	public void setVacacionesPendientes(int vacacionesPendientes) {
		this.vacacionesPendientes = vacacionesPendientes;
	}

	public long getPrimaProporcional() {
		return primaProporcional;
	}

	public void setPrimaProporcional(long primaProporcional) {
		this.primaProporcional = primaProporcional;
	}

	public long getSalarioPendiente() {
		return salarioPendiente;
	}

	public void setSalarioPendiente(long salarioPendiente) {
		this.salarioPendiente = salarioPendiente;
	}

	public long getTotalLiquidacion() {
		return totalLiquidacion;
	}

	public void setTotalLiquidacion(long totalLiquidacion) {
		this.totalLiquidacion = totalLiquidacion;
	}

	@Override
	public String toString() {
		return "Liquidacion Definitiva: \nId Liquidacion: " + idLiquidacion + "\nValor Indemnizacion: "
				+ valorIndemnizacion + "\nFecha Liquidacion: " + fechaLiquidacion + "\nVacaciones Pendientes: "
				+ vacacionesPendientes + "\nPrima Proporcional: " + primaProporcional + "\nSalario Pendiente: "
				+ salarioPendiente + "\nTotal Liquidacion: " + totalLiquidacion;
	}

}
