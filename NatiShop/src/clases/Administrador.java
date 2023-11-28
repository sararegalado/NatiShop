package clases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Administrador extends Usuario {
	protected String apellido;
	protected Date FInicEmpresa;
	protected Jornada jornadaLaboral;
	protected Puesto puesto;
	
	protected SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	public Administrador() {
		super();
		
	}
	public Administrador(String dni, String nombre, Date fNac, String correo, Provincia provincia, String tfno, String contrasenia, String apellido, Date FInicEmpresa,Jornada jornadaLaboral,Puesto puesto ) {
		super(dni, nombre, fNac, correo, tfno, provincia, contrasenia);
		this.apellido= apellido;
		this.FInicEmpresa= FInicEmpresa;
		this.jornadaLaboral= jornadaLaboral;
		this.puesto= puesto;
		
	}
	
	/**
	 * Constructor que cambia las fechas del format Date a Strings 
	 * @param dni: String
	 * @param nombre; String
	 * @param fNac: Date a String 
	 * @param correo: String
	 * @param contrasenia: String
	 * @param apellido: String
	 * @param FInicEmpresa: De date a String
	 * @param jornadaLaboral: enum
	 * @param puesto: enum
	 */

	public Administrador(String dni, String nombre, String fNac, String correo, String provincia, String tfno,String contrasenia, String apellido, String FInicEmpresa,Jornada jornadaLaboral,Puesto puesto) {
		super(dni, nombre, fNac, correo, tfno, provincia, contrasenia);
		this.apellido= apellido;
		try {
			this.FInicEmpresa= sdf.parse(FInicEmpresa);
		} catch (ParseException e) {
			this.FInicEmpresa= new Date(0);
		}
		this.jornadaLaboral= jornadaLaboral;
		this.puesto= puesto;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFInicEmpresa() {
		return FInicEmpresa;
	}

	public void setFInicEmpresa(Date fInicEmpresa) {
		FInicEmpresa = fInicEmpresa;
	}
	
	public String getFInicEmpresaStr() {
		return sdf.format(FInicEmpresa);
	}
	
	public void setFInicEmpresarstr(String FInicEmpresa) {
		try {
			this.FInicEmpresa= sdf.parse(FInicEmpresa);
		} catch (ParseException e) {
			this.FInicEmpresa= new Date();
		}
	}

	public Jornada getJornadaLaboral() {
		return jornadaLaboral;
	}

	public void setJornadaLaboral(Jornada jornadaLaboral) {
		this.jornadaLaboral = jornadaLaboral;
	}

	public Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}

	@Override
	public String toString() {
		return "Administrador [nombre" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", correo=" + correo +  ", fNac=" + getfNacStr() + ", FInicEmpresa=" + getFInicEmpresaStr() + ", jornadaLaboral="
				+ jornadaLaboral + ", puesto=" + puesto + "]";
	}

	
	
	

	

}
