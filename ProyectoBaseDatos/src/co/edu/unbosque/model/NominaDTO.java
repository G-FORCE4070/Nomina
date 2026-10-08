package co.edu.unbosque.model;

import java.sql.Date;

public class NominaDTO {

	private int idNomina;
	private long salarioBase;
	private Date fechaPago;
	private long salarioNeto;
	private int idContrato;

	public NominaDTO() {
	}

	public NominaDTO(int idNomina, long salarioBase, Date fechaPago, long salarioNeto, int idContrato) {
		super();
		this.idNomina = idNomina;
		this.salarioBase = salarioBase;
		this.fechaPago = fechaPago;
		this.salarioNeto = salarioNeto;
		this.idContrato = idContrato;
	}

	public int getIdNomina() {
		return idNomina;
	}

	public void setIdNomina(int idNomina) {
		this.idNomina = idNomina;
	}

	public long getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(long salarioBase) {
		this.salarioBase = salarioBase;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public long getSalarioNeto() {
		return salarioNeto;
	}

	public void setSalarioNeto(long salarioNeto) {
		this.salarioNeto = salarioNeto;
	}

	public int getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(int idContrato) {
		this.idContrato = idContrato;
	}

	@Override
	public String toString() {
		return "Nomina: \nId Nomina: " + idNomina + "\nSalario Base: " + salarioBase + "\nFecha Pago: " + fechaPago
				+ "\nSalario Neto: " + salarioNeto ;
	}

}
