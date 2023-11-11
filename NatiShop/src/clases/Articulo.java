package clases;


public class Articulo implements Comparable<Articulo>{
	protected String id;
	protected String nombre;
	
	protected int unidades;
	protected double precio;
	protected Talla talla;
	protected String foto;
	
	public Articulo() {
		super();
	}
	public Articulo(String id, String nombre, int unidades, double precio, Talla talla, String foto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.unidades = unidades;
		this.precio = precio;
		this.talla= talla;
		this.foto = foto;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	@Override
	public String toString() {
		return "Articulo [id=" + id + ", nombre=" + nombre + ", unidades=" + unidades + ", precio=" + precio
				+ ", talla=" + talla + ", foto=" + foto + "]";
	}
	@Override
	public int compareTo(Articulo o) {
		
		return this.id.compareTo(o.id);
	}

}
