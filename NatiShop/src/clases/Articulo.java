package clases;

public class Articulo implements Comparable<Articulo>{
	protected String id;
	protected String nombre;
	protected int unidades;
	protected float precio;
	protected Genero genero;
	protected Talla talla;
	protected String foto;
	protected Categoria categoria;
	private String titulos;
	
	public Articulo() {
		super();
	}
	public Articulo(String id, String nombre, int unidades, float precio, Genero genero, Talla talla, String foto, Categoria categoria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.unidades = unidades;
		this.precio = precio;
		this.genero = genero;
		this.talla= talla;
		this.foto = foto;
		this.categoria = categoria;
	}
	
	public Articulo(String id, String nombre, int unidades, float precio, String genero, String talla, String foto, String categoria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.unidades = unidades;
		this.precio = precio;
		setGeneroStr(genero);
		setTallaStr(talla);
		this.foto = foto;
		setCategoriaStr(categoria);
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
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public Talla getTalla() {
		return talla;
	}
	public void setTalla(Talla talla) {
		this.talla = talla;
	}
	
	public String getTallaStr() {
		return talla.toString(); //Convierte de enum a String
	}
	
	public void setTallaStr(String talla) {
		this.talla = Talla.valueOf(talla); //Convierte de String a enum
	}
	
	
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	public String getGeneroStr() {
		return genero.toString(); //Convierte de enum a String
	}
	
	public void setGeneroStr(String genero) {
		this.genero = Genero.valueOf(genero); //Convierte de String a enum
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	
	public String getCategoriaStr() {
		return categoria.toString(); //Convierte de enum a String
	}
	
	public void setCategoriaStr(String categoria) {
		this.categoria = Categoria.valueOf(categoria); //Convierte de String a enum
	}
	@Override
	public String toString() {
		return "Articulo [id=" + id + ", nombre=" + nombre + ", unidades=" + unidades + ", precio=" + precio
				+ ", genero=" + genero + ", talla=" + talla + ", foto=" + foto + ", categoria=" + categoria + "]";
	}
	@Override
	public int compareTo(Articulo o) {
		
		return this.id.compareTo(o.id);
	}
	
}

