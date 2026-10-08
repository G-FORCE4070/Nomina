package co.edu.unbosque.model;

import java.sql.Date;

public class PersonaDTO {

	private long cedula;
	private String apellido;
	private String nombre;
	private Date fechaNacimiento;
	private String direccion;
	private long contacto;
	private int departamentoId;
	private String cargo;
	private int idPersona;

	public PersonaDTO() {
	}

	public PersonaDTO(long cedula, String apellido, String nombre, Date fechaNacimiento, String direccion,
			long contacto, int departamentoId, String cargo, int idPersona) {
		super();
		this.cedula = cedula;
		this.apellido = apellido;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.contacto = contacto;
		this.departamentoId = departamentoId;
		this.cargo = cargo;
		this.idPersona = idPersona;
	}

	public long getCedula() {
		return cedula;
	}

	public void setCedula(long cedula) {
		this.cedula = cedula;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public long getContacto() {
		return contacto;
	}

	public void setContacto(long contacto) {
		this.contacto = contacto;
	}

	public int getDepartamentoId() {
		return departamentoId;
	}

	public void setDepartamentoId(int departamentoId) {
		this.departamentoId = departamentoId;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	@Override
	public String toString() {
		return "Persona: \nCedula: " + cedula + "\nApellido: " + apellido + "\nNombre: " + nombre
				+ "\nFecha Nacimiento: " + fechaNacimiento + "\nDireccion: " + direccion + "\nContacto: " + contacto
				+ "\nDepartamento Id: " + departamentoId + "\nCargo: " + cargo + "\nId Persona: " + idPersona;
	}

}