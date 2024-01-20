package clases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Compra {
	
	private int idCompra;
	private Cliente cliente;
	private Date fecha;
	List<Articulo> articulos;
	private float precio;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getFechaStr() {
		return sdf.format(fecha);
	}
	
	public void setFechaStr(String fecha) {
		try {
			this.fecha= sdf.parse(fecha);
		} catch (ParseException e) {
			this.fecha= new Date();
		}
	}
	public List<Articulo> getArticulos() {
		return articulos;
	}
	public void setArticulos(List<Articulo> articulos) {
		this.articulos = articulos;
	}
	
	
	public int getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}
	
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public Compra(Cliente cliente, String fecha, List<Articulo> articulos, float precio) {
		super();
		this.cliente = cliente;
		setFechaStr(fecha);
		this.articulos = articulos;
		this.precio = precio;
	}
	
	public Compra(Cliente cliente, Date fecha, List<Articulo> articulos, float precio) {
		super();
		this.cliente = cliente;
		this.fecha = fecha;
		this.articulos = articulos;
		this.precio = precio;
	}
	
	
	
	@Override
	public String toString() {
		return "Compra [idCompra=" + idCompra + ", cliente=" + cliente + ", fecha=" + fecha + ", articulos=" + articulos
				+ ", precio=" + precio + "]";
	}

}
