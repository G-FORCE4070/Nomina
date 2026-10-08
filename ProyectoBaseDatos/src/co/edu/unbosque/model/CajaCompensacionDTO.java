package co.edu.unbosque.model;

public class CajaCompensacionDTO {

	private long nitCaja;
	private String nombreCaja;

	public CajaCompensacionDTO() {
	}

	public CajaCompensacionDTO(long nitCaja, String nombreCaja) {
		this.nitCaja = nitCaja;
		this.nombreCaja = nombreCaja;
	}

	public long getNitCaja() {
		return nitCaja;
	}

	public void setNitCaja(long nitCaja) {
		this.nitCaja = nitCaja;
	}

	public String getNombreCaja() {
		return nombreCaja;
	}

	public void setNombreCaja(String nombreCaja) {
		this.nombreCaja = nombreCaja;
	}

	@Override
	public String toString() {
		return "Caja Compensación:\nNIT Caja: " + nitCaja + "\nNombre Caja: " + nombreCaja;
	}
}
