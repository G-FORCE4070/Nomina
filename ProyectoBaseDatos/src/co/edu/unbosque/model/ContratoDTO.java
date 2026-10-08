package co.edu.unbosque.model;

import java.sql.Date;

public class ContratoDTO {

	private int idContrato;
	private Date fechaInicio;
	private String tipoContrato;
	private int idLiquidacionDefinitiva;
	private String estado;
	private int idPersona;
	private Date fechaTerminacion;

	public ContratoDTO() {
	}

	public ContratoDTO(int idContrato, Date fechaInicio, String tipoContrato, int idLiquidacionDefinitiva,
			String estado, int idPersona, Date fechaTerminacion) {
		super();
		this.idContrato = idContrato;
		this.fechaInicio = fechaInicio;
		this.tipoContrato = tipoContrato;
		this.idLiquidacionDefinitiva = idLiquidacionDefinitiva;
		this.estado = estado;
		this.idPersona = idPersona;
		this.fechaTerminacion = fechaTerminacion;
	}

	public int getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public int getIdLiquidacionDefinitiva() {
		return idLiquidacionDefinitiva;
	}

	public void setIdLiquidacionDefinitiva(int idLiquidacionDefinitiva) {
		this.idLiquidacionDefinitiva = idLiquidacionDefinitiva;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	
	

	public Date getFechaTerminacion() {
		return fechaTerminacion;
	}

	public void setFechaTerminacion(Date fechaTerminacion) {
		this.fechaTerminacion = fechaTerminacion;
	}

	@Override
	public String toString() {
		return "Contrato: \nId Contrato: " + idContrato + "\nFecha Inicio: " + fechaInicio + "\nTipo Contrato: "
				+ tipoContrato + "\nId Liquidacion Definitiva: " + idLiquidacionDefinitiva + "\nEstado: " + estado
				+ "\nId Persona: " + idPersona + "\nFecha terminacion: "+fechaTerminacion;
	}
}
