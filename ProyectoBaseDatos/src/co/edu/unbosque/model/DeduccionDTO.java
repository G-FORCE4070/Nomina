package co.edu.unbosque.model;

public class DeduccionDTO {

	private int idDeduccion;
	private String tipoDeduccion;
	private long valor;
	private String razon;

	public DeduccionDTO() {
	}

	public DeduccionDTO(int idDeduccion, String tipoDeduccion, long valor, String razon) {
		super();
		this.idDeduccion = idDeduccion;
		this.tipoDeduccion = tipoDeduccion;
		this.valor = valor;
		this.razon = razon;
	}

	public int getIdDeduccion() {
		return idDeduccion;
	}

	public void setIdDeduccion(int idDeduccion) {
		this.idDeduccion = idDeduccion;
	}

	public String getTipoDeduccion() {
		return tipoDeduccion;
	}

	public void setTipoDeduccion(String tipoDeduccion) {
		this.tipoDeduccion = tipoDeduccion;
	}

	public long getValor() {
		return valor;
	}

	public void setValor(long valor) {
		this.valor = valor;
	}

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	@Override
	public String toString() {
		return "Deduccion: \nId Deduccion: " + idDeduccion + "\nTipo Deduccion: " + tipoDeduccion + "\nValor: " + valor
				+ "\nRazon: " + razon;
	}

}
