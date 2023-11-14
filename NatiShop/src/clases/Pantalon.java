package clases;

public class Pantalon extends Articulo {

	public Pantalon() {
		super();
	}

	public Pantalon(String id, String nombre, int unidades, double precio, Genero genero, Talla talla, String foto) {
		super(id, nombre, unidades, precio, genero, talla, foto);
	}

	@Override
	public String toString() {
		return "Pantalon [id=" + id + ", nombre=" + nombre + ", unidades=" + unidades + ", precio=" + precio
				+ ", genero=" + genero + ", talla=" + talla + ", foto=" + foto + "]";
	}

	
	
	
	
	
	

}
