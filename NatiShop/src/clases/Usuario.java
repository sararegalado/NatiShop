package clases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Usuario implements Comparable<Usuario> {
	private String dni;
	private String nombre;
	private Date fNac;
	private String contrasenia;
	private String correo;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	
	public Usuario() {
		super();
	
	}

	public Usuario(String dni, String nombre, Date fNac,String correo, String contrasenia) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.fNac = fNac;
		this.correo = correo;
		this.contrasenia = contrasenia;
		
	}
	/**
	 * Contructor de usuario que cambia el formato Date de la fNac a formato String
	 * @param dni: string
	 * @param nombre: string
	 * @param fNac : De Date a String
	 * @param correo: String
	 * @param contrasenia: String
	 */
	
	public Usuario(String dni, String nombre, String fNac,String correo, String contrasenia) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		try {
			this.fNac= sdf.parse(fNac);
		}catch (ParseException e) {
			this.fNac= new Date(0);
		}
		this.correo = correo;
		this.contrasenia = contrasenia;
		
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

	public Date getfNac() {
		return fNac;
	}

	public void setfNac(Date fNac) {
		this.fNac= fNac;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	
	public String getfNacStr() {
		return sdf.format(fNac);
	}
	

	@Override
	public String toString() {
		return "Usuario [dni=" + dni + ", nombre=" + nombre + ", fNac=" + getfNacStr() + ", correo=" + correo + "]";
	}

	@Override
	public int compareTo(Usuario o) {
		
	return this.dni.compareTo(o.dni);
	}
	
	
	
	
	

}
