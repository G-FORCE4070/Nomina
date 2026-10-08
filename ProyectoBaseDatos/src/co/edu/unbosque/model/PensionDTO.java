package co.edu.unbosque.model;

public class PensionDTO {

	private long nitPension;
	private String nombreFondo;

	public PensionDTO() {
	}

	public PensionDTO(long nitPension, String nombreFondo) {
		this.nitPension = nitPension;
		this.nombreFondo = nombreFondo;
	}

	public long getNitPension() {
		return nitPension;
	}

	public void setNitPension(long nitPension) {
		this.nitPension = nitPension;
	}

	public String getNombreFondo() {
		return nombreFondo;
	}

	public void setNombreFondo(String nombreFondo) {
		this.nombreFondo = nombreFondo;
	}

	@Override
	public String toString() {
		return "Pensión:\nNIT Pensión: " + nitPension + "\nNombre del Fondo: " + nombreFondo;
	}
}
