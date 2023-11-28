package clases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Usuario implements Comparable<Usuario> {
	protected String dni;
	protected String nombre;
	protected Date fNac;
	protected String tlf;
	protected Provincia provincia;
	protected String contrasenia;
	protected String correo;
	
	protected SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	
	public Usuario() {
		super();
	
	}
	
	public Usuario(String dni, String nombre, Date fNac, String correo, String tlf, Provincia provincia, String contrasenia) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.fNac = fNac;
		this.tlf = tlf;
		this.provincia = provincia;
		this.contrasenia = contrasenia;
		this.correo = correo;
	}
	
	/**
	 * Contructor de usuario que cambia el formato Date de la fNac a formato String
	 * @param dni: string
	 * @param nombre: string
	 * @param fNac : De Date a String
	 * @param correo: String
	 * @param contrasenia: String
	 */
	
	public Usuario(String dni, String nombre, String fNac,String correo, String tlf, String provincia, String contrasenia) {
		
		super();
		this.dni = dni;
		this.nombre = nombre;
		try {
			this.fNac= sdf.parse(fNac);
		}catch (ParseException e) {
			this.fNac= new Date(0);
		}
		this.tlf = tlf;
		setProvinciaStr(provincia);
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
	
	public String getfNacStr() {
		return sdf.format(fNac);
	}


	public void setfNac(Date fNac) {
		this.fNac= fNac;
	}
	
	public void setfNacStr(String fechaNac) {
		try {
			this.fNac = sdf.parse(fechaNac);
		} catch (ParseException e) {
			this.fNac = new Date(0); //01-01-1970
		}
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
	
	public String getTlf() {
		return tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	public String getProvinciaStr() {
		return provincia.toString(); //Convierte de enum a String
	}
	
	public void setProvinciaStr(String provincia) {
		this.provincia = Provincia.valueOf(provincia); //Convierte de String a enum
	}

	@Override
	public int hashCode() {
		return Objects.hash(contrasenia, correo, dni, fNac, nombre, provincia, tlf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(contrasenia, other.contrasenia) && Objects.equals(correo, other.correo)
				&& Objects.equals(dni, other.dni) && Objects.equals(fNac, other.fNac)
				&& Objects.equals(nombre, other.nombre) && provincia == other.provincia
				&& Objects.equals(tlf, other.tlf);
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
