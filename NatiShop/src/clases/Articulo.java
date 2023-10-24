package clases;


public class Articulo implements Comparable<Articulo>{
	protected String id;
	protected int unidades;
	protected double precio;
	protected Talla talla;
	
	public Articulo() {
		super();
	}
	public Articulo(String id, int unidades, double precio, Talla talla) {
		super();
		this.id = id;
		this.unidades = unidades;
		this.precio = precio;
		this.talla= talla;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public Talla getTalla() {
		return talla;
	}
	public void setTalla(Talla talla) {
		this.talla = talla;
	}
	
	@Override
	public String toString() {
		return "Articulo [id=" + id + ", unidades=" + unidades + ", precio=" + precio + ", talla=" + talla + "]";
	}
	@Override
	public int compareTo(Articulo o) {
		
		return this.id.compareTo(o.id);
	}

}
