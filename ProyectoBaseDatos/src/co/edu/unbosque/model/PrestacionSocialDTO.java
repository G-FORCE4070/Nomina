package co.edu.unbosque.model;

import java.sql.Date;

public class PrestacionSocialDTO {

	private int idPrestacion;
	private long valorCesantia;
	private long interes;
	private long valorPrima;
	private int diasVacaciones;
	private Date fechaLiquidacion;
	private int idContrato;

	public PrestacionSocialDTO() {
	}

	public PrestacionSocialDTO(int idPrestacion, long valorCesantia, long interes, long valorPrima, int diasVacaciones,
			Date fechaLiquidacion, int idContrato) {
		super();
		this.idPrestacion = idPrestacion;
		this.valorCesantia = valorCesantia;
		this.interes = interes;
		this.valorPrima = valorPrima;
		this.diasVacaciones = diasVacaciones;
		this.fechaLiquidacion = fechaLiquidacion;
		this.idContrato = idContrato;
	}

	public int getIdPrestacion() {
		return idPrestacion;
	}

	public void setIdPrestacion(int idPrestacion) {
		this.idPrestacion = idPrestacion;
	}

	public long getValorCesantia() {
		return valorCesantia;
	}

	public void setValorCesantia(long valorCesantia) {
		this.valorCesantia = valorCesantia;
	}

	public long getInteres() {
		return interes;
	}

	public void setInteres(long interes) {
		this.interes = interes;
	}

	public long getValorPrima() {
		return valorPrima;
	}

	public void setValorPrima(long valorPrima) {
		this.valorPrima = valorPrima;
	}

	public int getDiasVacaciones() {
		return diasVacaciones;
	}

	public void setDiasVacaciones(int diasVacaciones) {
		this.diasVacaciones = diasVacaciones;
	}

	public Date getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	public void setFechaLiquidacion(Date fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	public int getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}

	@Override
	public String toString() {
		return "Prestacion Social: \nId Prestacion: " + idPrestacion + "\nValor Cesantia: " + valorCesantia
				+ "\nInteres: " + interes + "\nValor Prima: " + valorPrima + "\nDias Vacaciones: " + diasVacaciones
				+ "\nFecha Liquidacion: " + fechaLiquidacion;
	}

}
