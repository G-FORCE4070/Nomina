package co.edu.unbosque.model;

public class ArlDTO {

	private long nitArl;
	private String nombreArl;
	private long numeroContacto;

	public ArlDTO() {
	}

	public ArlDTO(long nitArl, String nombreArl, long numeroContacto) {
		this.nitArl = nitArl;
		this.nombreArl = nombreArl;
		this.numeroContacto = numeroContacto;
	}

	public long getNitArl() {
		return nitArl;
	}

	public void setNitArl(long nitArl) {
		this.nitArl = nitArl;
	}

	public String getNombreArl() {
		return nombreArl;
	}

	public void setNombreArl(String nombreArl) {
		this.nombreArl = nombreArl;
	}

	public long getNumeroContacto() {
		return numeroContacto;
	}

	public void setNumeroContacto(long numeroContacto) {
		this.numeroContacto = numeroContacto;
	}

	@Override
	public String toString() {
		return "ARL:\nNit ARL: " + nitArl + "\nNombre ARL: " + nombreArl + "\nNúmero de Contacto: " + numeroContacto;
	}
}
