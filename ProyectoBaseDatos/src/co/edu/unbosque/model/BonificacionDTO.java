package co.edu.unbosque.model;

public class BonificacionDTO {

	private int idBonificacion;
	private long valor;
	private String tipoBonificacion;

	public BonificacionDTO() {
	}

	public BonificacionDTO(int idBonificacion, long valor, String tipoBonificacion) {
		super();
		this.idBonificacion = idBonificacion;
		this.valor = valor;
		this.tipoBonificacion = tipoBonificacion;
	}

	public int getIdBonificacion() {
		return idBonificacion;
	}

	public void setIdBonificacion(int idBonificacion) {
		this.idBonificacion = idBonificacion;
	}

	public long getValor() {
		return valor;
	}

	public void setValor(long valor) {
		this.valor = valor;
	}

	public String getTipoBonificacion() {
		return tipoBonificacion;
	}

	public void setTipoBonificacion(String tipoBonificacion) {
		this.tipoBonificacion = tipoBonificacion;
	}

	@Override
	public String toString() {
		return "Bonificacion: \nId Bonificacion: " + idBonificacion + "\nValor: " + valor + "\nTipo Bonificacion: "
				+ tipoBonificacion;
	}

}
