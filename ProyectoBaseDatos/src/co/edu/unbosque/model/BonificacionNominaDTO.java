package co.edu.unbosque.model;

public class BonificacionNominaDTO {

	private int idNomina;
	private int idBonificacion;

	public BonificacionNominaDTO() {
		// TODO Auto-generated constructor stub
	}

	public BonificacionNominaDTO(int idNomina, int idBonificacion) {
		super();
		this.idNomina = idNomina;
		this.idBonificacion = idBonificacion;
	}

	public int getIdNomina() {
		return idNomina;
	}

	public void setIdNomina(int idNomina) {
		this.idNomina = idNomina;
	}

	public int getIdBonificacion() {
		return idBonificacion;
	}

	public void setIdBonificacion(int idBonificacion) {
		this.idBonificacion = idBonificacion;
	}

	@Override
	public String toString() {
		return "Bonificacion Nomina: \nId Nomina: " + idNomina + "\nId Bonificacion: " + idBonificacion;
	}

}
