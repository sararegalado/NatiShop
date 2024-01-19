package clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Compra {
	
	private int idCompra;
	private Cliente cliente;
	private long fecha;
	List<Articulo> articulos;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public long getFecha() {
		return fecha;
	}
	public void setFecha(long fecha) {
		this.fecha = fecha;
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
	public Compra(Cliente cliente, long fecha, List<Articulo> articulos) {
		super();
		this.cliente = cliente;
		this.fecha = fecha;
		this.articulos = articulos;
	}
	
	@Override
	public String toString() {
		return "Compra [idCompra=" + idCompra + ", cliente=" + cliente + ", fecha=" + fecha + ", articulos=" + articulos
				+ "]";
	}
	

}
