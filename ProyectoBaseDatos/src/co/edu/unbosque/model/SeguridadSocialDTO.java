package co.edu.unbosque.model;

import java.sql.Date;

public class SeguridadSocialDTO {

	private Date fechaPago;
	private long idSeguridadSocial;
	private int idPersona;
	private long arlNitArl;
	private long epsNitEps;
	private long pensionNitPension;
	private long cajaCompensacionNitCaja;
	private long aporteArl;
	private long aporteCaja;
	private long aporteEps;
	private long aportePension;

	public SeguridadSocialDTO() {
	}

	public SeguridadSocialDTO(Date fechaPago, long idSeguridadSocial, int idPersona, long arlNitArl, long epsNitEps,
			long pensionNitPension, long cajaCompensacionNitCaja, long aporteArl, long aporteCaja, long aporteEps,
			long aportePension) {
		this.fechaPago = fechaPago;
		this.idSeguridadSocial = idSeguridadSocial;
		this.idPersona = idPersona;
		this.arlNitArl = arlNitArl;
		this.epsNitEps = epsNitEps;
		this.pensionNitPension = pensionNitPension;
		this.cajaCompensacionNitCaja = cajaCompensacionNitCaja;
		this.aporteArl = aporteArl;
		this.aporteCaja = aporteCaja;
		this.aporteEps = aporteEps;
		this.aportePension = aportePension;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public long getIdSeguridadSocial() {
		return idSeguridadSocial;
	}

	public void setIdSeguridadSocial(long idSeguridadSocial) {
		this.idSeguridadSocial = idSeguridadSocial;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public long getArlNitArl() {
		return arlNitArl;
	}

	public void setArlNitArl(long arlNitArl) {
		this.arlNitArl = arlNitArl;
	}

	public long getEpsNitEps() {
		return epsNitEps;
	}

	public void setEpsNitEps(long epsNitEps) {
		this.epsNitEps = epsNitEps;
	}

	public long getPensionNitPension() {
		return pensionNitPension;
	}

	public void setPensionNitPension(long pensionNitPension) {
		this.pensionNitPension = pensionNitPension;
	}

	public long getCajaCompensacionNitCaja() {
		return cajaCompensacionNitCaja;
	}

	public void setCajaCompensacionNitCaja(long cajaCompensacionNitCaja) {
		this.cajaCompensacionNitCaja = cajaCompensacionNitCaja;
	}

	public long getAporteArl() {
		return aporteArl;
	}

	public void setAporteArl(long aporteArl) {
		this.aporteArl = aporteArl;
	}

	public long getAporteCaja() {
		return aporteCaja;
	}

	public void setAporteCaja(long aporteCaja) {
		this.aporteCaja = aporteCaja;
	}

	public long getAporteEps() {
		return aporteEps;
	}

	public void setAporteEps(long aporteEps) {
		this.aporteEps = aporteEps;
	}

	public long getAportePension() {
		return aportePension;
	}

	public void setAportePension(long aportePension) {
		this.aportePension = aportePension;
	}

	@Override
	public String toString() {
		return "Seguridad Social:\n" + "Fecha de Pago: " + fechaPago + "\nID Seguridad Social: " + idSeguridadSocial
				+ "\nID Persona: " + idPersona + "\nNIT ARL: " + arlNitArl + "\nNIT EPS: " + epsNitEps
				+ "\nNIT Pensión: " + pensionNitPension + "\nNIT Caja Compensación: " + cajaCompensacionNitCaja
				+ "\nAporte ARL: " + aporteArl + "\nAporte Caja: " + aporteCaja + "\nAporte EPS: " + aporteEps
				+ "\nAporte Pensión: " + aportePension;
	}
}
