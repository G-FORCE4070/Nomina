package co.edu.unbosque.model;

public class DeduccionNominaDTO {

	private int idNomina;
	private int idDeduccion;

	public DeduccionNominaDTO() {
		// TODO Auto-generated constructor stub
	}

	public DeduccionNominaDTO(int idNomina, int idDeduccion) {
		super();
		this.idNomina = idNomina;
		this.idDeduccion = idDeduccion;
	}

	public int getIdNomina() {
		return idNomina;
	}

	public void setIdNomina(int idNomina) {
		this.idNomina = idNomina;
	}

	public int getIdDeduccion() {
		return idDeduccion;
	}

	public void setIdDeduccion(int idDeduccion) {
		this.idDeduccion = idDeduccion;
	}

	@Override
	public String toString() {
		return "Deduccion Nomina: \nId Nomina: " + idNomina + "\nId Deduccion: " + idDeduccion;
	}

}
