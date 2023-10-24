package clases;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Administrador implements Comparable<Administrador>{
	private String dni;
	private String nombre;
	private String correo;
	private String contraseña;
	private Date fcontrato;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	
	Administrador() {
		super();
		
	}

	public Administrador(String dni, String nombre, String correo, String contraseña, Date fcontrato) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.correo = correo;
		this.contraseña = contraseña;
		this.fcontrato = fcontrato;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Date getFcontrato() {
		return fcontrato;
	}

	public void setFcontrato(Date fcontrato) {
		this.fcontrato = fcontrato;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	
	
	

	@Override
	public String toString() {
		return "Administrador [dni=" + dni + ", nombre=" + nombre + ", correo=" + correo + ", contraseña=" + contraseña
				+ ", fcontrato=" + fcontrato + ", sdf=" + sdf + "]";
	}

	@Override
	public int compareTo(Administrador o) {
		
		return this.dni.compareTo(o.dni);
	}

}
