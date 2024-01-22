package clases;

import java.util.Date;

public class Cliente extends Usuario{
	private String numTarjeta = "";
	private double saldo;

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Cliente(String dni, String nombre, Date fNac, String correo, String tlf, Provincia provincia,
			String contrasenia, String numTarjeta, double saldo) {
		super(dni, nombre, fNac, correo, tlf, provincia, contrasenia);
		this.numTarjeta = numTarjeta;
		this.saldo = saldo;
	}

	public Cliente(String dni, String nombre, String fNac, String correo, String tlf, String provincia,
			String contrasenia, String numTarjeta, double saldo) {
		super(dni, nombre, fNac, correo, tlf, provincia, contrasenia);
		this.numTarjeta = numTarjeta;
		this.saldo = saldo;
	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}
	
	
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	
	

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", fNac=" + fNac + ", tlf=" + tlf + 
				", provincia=" + provincia + ", contrasenia=" + contrasenia + ", correo=" + correo + 
				", sdf=" + sdf + "numTarjeta=" + numTarjeta + ", saldo=" + saldo + "]";
	}


}

