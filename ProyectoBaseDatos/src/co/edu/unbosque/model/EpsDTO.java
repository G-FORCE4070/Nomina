package co.edu.unbosque.model;

public class EpsDTO {

	private long nitEps;
	private String nombreEps;

	public EpsDTO() {
	}

	public EpsDTO(long nitEps, String nombreEps) {
		this.nitEps = nitEps;
		this.nombreEps = nombreEps;
	}

	public long getNitEps() {
		return nitEps;
	}

	public void setNitEps(long nitEps) {
		this.nitEps = nitEps;
	}

	public String getNombreEps() {
		return nombreEps;
	}

	public void setNombreEps(String nombreEps) {
		this.nombreEps = nombreEps;
	}

	@Override
	public String toString() {
		return "EPS:\nNIT EPS: " + nitEps + "\nNombre EPS: " + nombreEps;
	}
}
