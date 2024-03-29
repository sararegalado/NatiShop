package clases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Administrador extends Usuario {
	protected String apellido;
	protected Date FInicEmpresa;
	protected Jornada jornadaLaboral;
	protected Puesto puesto;
	
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public Administrador() {
		super();
		
	}
	
	public Administrador(String dni, String nombre, String apellido, Date fNac, String correo, String tfno, Provincia provincia,  Date FInicEmpresa,Jornada jornadaLaboral,Puesto puesto, String contrasenia ) {
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
		this.apellido = apellido;
		//this.FInicEmpresa =sdf.parse(fInicEmpresa);
		setFInicEmpresarstr(fInicEmpresa);
		this.jornadaLaboral = Jornada.valueOf(jornadaLaboral);
		this.puesto = Puesto.valueOf(puesto);
	 */
	public Administrador(String dni, String nombre, String apellido, String fNac,  String correo, String tlf, String provincia,
			String fInicEmpresa, String jornadaLaboral, String puesto, String contrasenia) {
		super(dni, nombre, fNac, correo, tlf, provincia, contrasenia);
		this.apellido = apellido;
		setFInicEmpresarstr(fInicEmpresa);
		this.jornadaLaboral = Jornada.valueOf(jornadaLaboral);
		this.puesto = Puesto.valueOf(puesto);
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
	
	public String getJornadaLaboralStr() {
		return jornadaLaboral.toString();
	}
	
	public void setJornadaLaboralStr(String jornadaLaboral) {
		this.jornadaLaboral = Jornada.valueOf(jornadaLaboral);
	}
	

	public Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}
	public String getPuestoStr() {
		return puesto.toString();
	}
	
	public void setPuestoStr(String puesto) {
		this.puesto = Puesto.valueOf(puesto);
	}

	@Override
	public String toString() {
		return "Administrador [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", correo=" + correo +  ", fNac=" + getfNacStr() + ", FInicEmpresa=" + getFInicEmpresaStr() + ", jornadaLaboral="
				+ jornadaLaboral + ", puesto=" + puesto + "]";
	}

	
	
	

	

}
